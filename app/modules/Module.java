package modules;

import com.google.inject.AbstractModule;

import dao.ITradeDao;
import dao.TradeDaoImpl;
import net.misiorek.contracts.ISolidityContext;
import net.misiorek.contracts.SolidityContextImpl;

public class Module extends AbstractModule {

	protected void configure() {
		bind(ITradeDao.class).to(TradeDaoImpl.class);
		bind(ISolidityContext.class).to(SolidityContextImpl.class); 
	}
	
}
