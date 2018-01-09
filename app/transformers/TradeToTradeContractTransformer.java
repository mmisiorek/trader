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
			return deployTrade(trade);
		} else {
			return createFromAddress(trade.getContractAddress()); 
		}
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
		Trade_sol_Trade tradeContract = Trade_sol_Trade.load(contractAddress.toString(), solidityContext.getWeb3j(), solidityContext.getCredentials(), solidityContext.getGasPrice(), solidityContext.getGasLimit());
		
		future.complete(tradeContract);
		
		return future;
	}
	
	protected CompletionStage<Trade_sol_Trade> deployTrade(Trade trade) throws TransformNotPossibleException {
		ObservableToCompletableFutureTransformer<Trade_sol_Trade> transformer = new ObservableToCompletableFutureTransformer<>();
		
		if(trade.getAdvanceAmount() == null) {
			throw new TransformNotPossibleException("The advance amount cannot be null");
		}
		
		if(trade.getRealizationAmount() == null) {
			throw new TransformNotPossibleException("The realization amount cannot be null");
		}
		
		return transformer.transform(Trade_sol_Trade.deploy(solidityContext.getWeb3j(), solidityContext.getCredentials(), solidityContext.getGasPrice(), solidityContext.getGasLimit(), BigInteger.ZERO, trade.getAdvanceAmount(), trade.getRealizationAmount(), trade.getUniqueHash()).observable());
	}
	
}
