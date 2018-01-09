package controllers;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;

import dao.ITradeDao;
import models.Trade;
import net.misiorek.contracts.Trade_sol_Trade;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;
import transformers.TradeToTradeContractTransformer;
import transformers.TransformNotPossibleException;

public class TradeController extends Controller {
	
	private ITradeDao tradeDao;
	
	private TradeToTradeContractTransformer tradeTypesTransformer;
	
	@Inject
	public TradeController(ITradeDao tradeDao, TradeToTradeContractTransformer tradeTypesTransformer) {
		this.tradeDao = tradeDao;
		this.tradeTypesTransformer = tradeTypesTransformer; 
	}
	
	@Transactional(readOnly=false)
	public Result add() {
		Trade t = tradeDao.createNew(); 
		tradeDao.getJPAApi().em().persist(t);
		tradeDao.getJPAApi().em().flush();
		
		return redirect("/trade/"+t.getUniqueHash()); 
	}
	
	@Transactional(readOnly=true)
	public CompletionStage<Result> view(String uniqueHash) {
		try {
			Trade t = tradeDao.findByUniqueHash(uniqueHash);
			CompletionStage<Trade_sol_Trade> contractFuture = tradeTypesTransformer.transform(t); 
			CompletableFuture<Result> resultFuture = new CompletableFuture<>();
			
			contractFuture.thenApply((Trade_sol_Trade tradeContract) -> {
				resultFuture.complete(ok("Success")); 
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
