package actors;

import java.math.BigInteger;
import java.util.UUID;

import javax.inject.Inject;
import javax.inject.Named;

import akka.actor.AbstractActor;
import akka.actor.AbstractActor.Receive;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.japi.pf.FI.UnitApply;
import models.Trade;
import net.misiorek.contracts.ISolidityContext;
import net.misiorek.contracts.Trade_sol_Trade;
import rx.Observable;
import rx.Subscriber;

public class ContractDeploymentActor extends AbstractActor {
	private ISolidityContext solidityContext;
	private ActorRef persisterActor;
	
	@Inject
	public ContractDeploymentActor(ISolidityContext solidityContext,
									@Named("after-contract-deployment-persister") ActorRef persistActor) {
		this.solidityContext = solidityContext; 
		this.persisterActor = persistActor;
	}
	
	public static final class DeployTrade {
		
		BigInteger advanceAmount;
		BigInteger realizationAmount;
		String uniqueHash; 
		String requestId; 
		
		public DeployTrade(BigInteger advanceAmount, BigInteger realizationAmount, String uniqueHash, String requestId) {
			this.advanceAmount = advanceAmount; 
			this.realizationAmount = realizationAmount;
			this.uniqueHash = uniqueHash;
			this.requestId = requestId;
		}
		
		public static DeployTrade fromEntity(Trade trade, String requestId) {
			return new DeployTrade(trade.getAdvanceAmount(), trade.getRealizationAmount(), trade.getUniqueHash(), requestId); 
		}
		
	}
	
	public static final class RespondTradeDeployment {
		
		String requestId;
		Trade_sol_Trade contractTrade;
		
		public RespondTradeDeployment(String requestId, Trade_sol_Trade contractTrade) {
			this.requestId = requestId;
			this.contractTrade = contractTrade; 
		}
		
	}

	@Override
	//TODO add supporting response from the persisting actor and after that respond on DeployTrade message...
	public Receive createReceive() {
		return receiveBuilder()
				.match(DeployTrade.class, getDeployTradeUnit())
				.build();
	}
	
	public UnitApply<DeployTrade> getDeployTradeUnit() {
		return (DeployTrade t) -> {
			Observable<Trade_sol_Trade> observable = Trade_sol_Trade.deploy(solidityContext.getWeb3j(), solidityContext.getCredentials(), solidityContext.getGasPrice(), solidityContext.getGasLimit(), BigInteger.ZERO, t.advanceAmount, t.realizationAmount, t.uniqueHash).observable();
			
			observable.subscribe(new Subscriber<Trade_sol_Trade>() {
				
				private Trade_sol_Trade lastContract;
				
				public void onNext(Trade_sol_Trade contract) {
					this.lastContract = contract;
				}
				
				public void onCompleted() {
					getSender().tell(new RespondTradeDeployment(t.requestId, this.lastContract), self());
					persisterActor.tell(new AfterContractDeploymentPersisterActor.PersistAfterTradeDeployment(t.uniqueHash, UUID.randomUUID().toString(), this.lastContract), self());
				}
				
				public void onError(Throwable exception) {
					
				}
				
			});
		}; 
	}
	
}
