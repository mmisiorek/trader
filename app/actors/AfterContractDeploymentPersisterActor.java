package actors;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import akka.actor.AbstractActor;
import akka.japi.pf.FI.UnitApply;
import dao.ITradeDao;
import models.Trade;
import net.misiorek.contracts.Trade_sol_Trade;
import play.db.jpa.JPAApi;
import play.db.jpa.Transactional;

public class AfterContractDeploymentPersisterActor extends AbstractActor {
	
	private ITradeDao tradeDao;

	public static final class PersistAfterTradeDeployment {
		String uniqueHash;
		String requestId;
		Trade_sol_Trade contract;
		
		public PersistAfterTradeDeployment(String uniqueHash, String requestId, Trade_sol_Trade contract) {
			this.uniqueHash = uniqueHash;
			this.requestId = requestId;
			this.contract = contract; 
		}
	}
	
	public static final class PersistDone {
		String requestId;
		Object request;
		
		public PersistDone(String requestId, Object request) {
			this.requestId = requestId;
			this.request = request;
		}
		
	}
	
	@Inject
	public AfterContractDeploymentPersisterActor(ITradeDao tradeDao) {
		this.tradeDao = tradeDao; 
	}
	
	@Override
	public Receive createReceive() {
		return receiveBuilder()
				.match(PersistAfterTradeDeployment.class, getPersistAfterTradeDeploymentUnit())
				.build();
	}
	
	protected UnitApply<PersistAfterTradeDeployment> getPersistAfterTradeDeploymentUnit() {
		return new UnitApply<PersistAfterTradeDeployment>() {
			
			@Override
			public void apply(PersistAfterTradeDeployment message) {
				tradeDao.getJPAApi().withTransaction((EntityManager em) -> {
					Trade trade = tradeDao.findByUniqueHash(message.uniqueHash); 
					trade.setContractAddress(message.contract.getContractAddress());
					em.flush();
					getSender().tell(new PersistDone(message.requestId, message), self());
					
					return "";
				});
			}
			
		};
		
	}
	
}
