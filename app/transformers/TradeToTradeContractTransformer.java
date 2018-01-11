package transformers;

import java.math.BigInteger;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import javax.inject.Inject;
import javax.persistence.EntityNotFoundException;

import dao.ITradeDao;
import models.Trade;
import net.misiorek.contracts.ISolidityContext;
import net.misiorek.contracts.Trade_sol_Trade;
import play.db.jpa.Transactional;
import rx.Observable;
import rx.Subscriber;

public class TradeToTradeContractTransformer implements ICompletionStageTransformer<Trade, Trade_sol_Trade> {

	private ITradeDao tradeDao;
	
	private ISolidityContext solidityContext;
	
	@Inject
	public TradeToTradeContractTransformer(ITradeDao tradeDao, ISolidityContext solidityContext) {
		this.tradeDao = tradeDao; 
		this.solidityContext = solidityContext; 
	}
	
	public CompletionStage<Trade_sol_Trade> transform(Trade trade) throws TransformNotPossibleException {
		if(trade.getContractAddress() == null) {
			throw new TransformNotPossibleException(""); 
		}
		
		return createFromAddress(trade.getContractAddress()); 
	}
	
	public CompletionStage<Trade> reverseTransform(Trade_sol_Trade contract) throws TransformNotPossibleException {
		BigInteger contractAddress = BigInteger.valueOf(Long.parseLong(contract.getContractAddress()));
		CompletableFuture<Trade> future = new CompletableFuture<Trade>();

		try {			
			future.complete(tradeDao.findByContractAddress(contractAddress));
			return future;
		} catch(EntityNotFoundException e) {
			throw new TransformNotPossibleException("");
		}
	}
	
	protected CompletionStage<Trade_sol_Trade> createFromAddress(BigInteger contractAddress) throws TransformNotPossibleException {
		CompletableFuture<Trade_sol_Trade> future = new CompletableFuture<>();
		Trade_sol_Trade tradeContract = Trade_sol_Trade.load(Trade.toAddress(contractAddress), solidityContext.getWeb3j(), solidityContext.getCredentials(), solidityContext.getGasPrice(), solidityContext.getGasLimit());
		
		future.complete(tradeContract);
		
		return future;
	}
	
}
