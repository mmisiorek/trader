package modules;

import com.google.inject.AbstractModule;

import actors.AfterContractDeploymentPersisterActor;
import actors.ContractDeploymentActor;
import dao.ITradeDao;
import dao.TradeDaoImpl;
import net.misiorek.contracts.ISolidityContext;
import net.misiorek.contracts.SolidityContextImpl;
import play.libs.akka.AkkaGuiceSupport;

public class Module extends AbstractModule implements AkkaGuiceSupport {

	@Override
	protected void configure() {
		bind(ITradeDao.class).to(TradeDaoImpl.class);
		bind(ISolidityContext.class).to(SolidityContextImpl.class); 
		
		bindActor(ContractDeploymentActor.class, "contract-deployment");
		bindActor(AfterContractDeploymentPersisterActor.class, "after-contract-deployment-persister");
	}
	
}
