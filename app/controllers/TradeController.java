package controllers;

import java.math.BigInteger;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;

import actors.ContractDeploymentActor;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import dao.ITradeDao;
import models.Trade;
import net.misiorek.contracts.Trade_sol_Trade;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;
import rx.Subscriber;
import scala.compat.java8.FutureConverters;
import transformers.TradeToTradeContractTransformer;
import transformers.TransformNotPossibleException;
import static akka.pattern.Patterns.ask;

public class TradeController extends Controller {
	
	private ITradeDao tradeDao;
	private ActorRef contractDeploymentActor;
	
	private TradeToTradeContractTransformer tradeTypesTransformer;
	
	@Inject
	public TradeController(ITradeDao tradeDao, TradeToTradeContractTransformer tradeTypesTransformer, @Named("contract-deployment") ActorRef actor) {
		this.tradeDao = tradeDao;
		this.tradeTypesTransformer = tradeTypesTransformer;
		this.contractDeploymentActor = actor;
		
		if(actor.isTerminated()) {
			System.out.print("is terminated");
		} else {
			System.out.println("is not terminated");
		}
	}
	
	@Transactional(readOnly=false)
	public Result newEntity() {
		return ok(views.html.tradeNew.render(false, false)); 
	}
	
	@Transactional(readOnly=false)
	public CompletionStage<Result> add() {
		String advanceAmountParam = request().body().asFormUrlEncoded().get("advanceAmount")[0];
		String realizationAmountParam = request().body().asFormUrlEncoded().get("realizationAmount")[0];
		int advanceAmount = 0, realizationAmount = 0;
		boolean advanceInvalid = false;
		boolean realizationInvalid = false;
		
		// not the best but the fastest solution
		try {
			advanceAmount = Integer.parseInt(advanceAmountParam);
			
		} catch(NumberFormatException e) {
			advanceInvalid = true;
		}
		
		try {
			realizationAmount = Integer.parseInt(realizationAmountParam); 
			
		} catch(NumberFormatException e) {
			realizationInvalid = true;
		}
		
		if(advanceInvalid || realizationInvalid) {
			return CompletableFuture.completedFuture(ok(views.html.tradeNew.render(advanceInvalid, realizationInvalid)));
		}
		
		Trade t = tradeDao.createNew(); 
		tradeDao.getJPAApi().em().persist(t);
		tradeDao.getJPAApi().em().flush();
		
		Object message = new ContractDeploymentActor.DeployTrade(BigInteger.valueOf(advanceAmount), BigInteger.valueOf(realizationAmount), t.getUniqueHash(), UUID.randomUUID().toString());
		
		return FutureConverters.toJava(ask(this.contractDeploymentActor, message, 1000)).thenApply(response -> {
			return redirect("/trade/view/"+t.getUniqueHash());
		});
	}
	
	@Transactional(readOnly=true)
	public CompletionStage<Result> view(String uniqueHash) {
		try {
			Trade t = tradeDao.findByUniqueHash(uniqueHash);
			CompletionStage<Trade_sol_Trade> contractFuture = tradeTypesTransformer.transform(t); 
			CompletableFuture<Result> resultFuture = new CompletableFuture<>();
			
			contractFuture.thenApply((Trade_sol_Trade tradeContract) -> {
				resultFuture.complete(ok(views.html.tradeView.render(t))); 
				return null;
			});
			
			return resultFuture; 
			
		} catch(EntityNotFoundException e) {
			return CompletableFuture.completedFuture(notFound()); 
		} catch(TransformNotPossibleException e) {
			return CompletableFuture.completedFuture(internalServerError());
		}
	}
	
	@Transactional(readOnly=true)
	public CompletionStage<Result> viewAdvanceAmount(String uniqueHash) {
		try {
			Trade t = tradeDao.findByUniqueHash(uniqueHash);
			CompletionStage<Trade_sol_Trade> contractFuture = tradeTypesTransformer.transform(t); 
			CompletableFuture<Result> resultFuture = new CompletableFuture<>();
			
			contractFuture.thenApply((Trade_sol_Trade tradeContract) -> {
				tradeContract.advanceAmount().observable().subscribe(new Subscriber<BigInteger>() {
				
					BigInteger lastValue;
					
					@Override
					public void onNext(BigInteger val) {
						lastValue = val;
					}
					
					@Override
					public void onCompleted() {
						resultFuture.complete(ok(views.html.tradeValue.render(lastValue.toString(), "Advance amount")));
					}
					
					@Override
					public void onError(Throwable exception) {
						
					}
				
				});
				
				return null;
			});
			
			return resultFuture;
			
		} catch(EntityNotFoundException e) {
			return CompletableFuture.completedFuture(notFound()); 
			
		} catch(TransformNotPossibleException e) {
			return CompletableFuture.completedFuture(internalServerError());
		}
	}
	
	@Transactional(readOnly=true)
	public CompletionStage<Result> viewRealizationAmount(String uniqueHash) {
		try {
			Trade t = tradeDao.findByUniqueHash(uniqueHash);
			CompletionStage<Trade_sol_Trade> contractFuture = tradeTypesTransformer.transform(t); 
			CompletableFuture<Result> resultFuture = new CompletableFuture<>();
			
			contractFuture.thenApply((Trade_sol_Trade tradeContract) -> {
				tradeContract.realizationAmount().observable().subscribe(new Subscriber<BigInteger>() {
				
					BigInteger lastValue;
					
					@Override
					public void onNext(BigInteger val) {
						lastValue = val;
					}
					
					@Override
					public void onCompleted() {
						resultFuture.complete(ok(views.html.tradeValue.render(lastValue.toString(), "Realization amount")));
					}
					
					@Override
					public void onError(Throwable exception) {
						
					}
				
				});
				
				return null;
			});
			
			return resultFuture;
			
		} catch(EntityNotFoundException e) {
			return CompletableFuture.completedFuture(notFound()); 
			
		} catch(TransformNotPossibleException e) {
			return CompletableFuture.completedFuture(internalServerError());
		}
	}

}
