package controllers;

import java.math.BigInteger;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import javax.inject.Inject;

import org.web3j.crypto.Credentials;
import org.web3j.crypto.ECKeyPair;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.Contract;

import dao.ITradeDao;
import dao.TradeDaoImpl;
import models.Trade;
import net.misiorek.contracts.Trade_sol_Trade;
import play.db.jpa.JPAApi;
import play.db.jpa.Transactional;
import play.mvc.*;
import rx.Observable;
import rx.Subscriber;

/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class HomeController extends Controller {
	
	private ITradeDao tradeDao;
	
	@Inject
	public HomeController(ITradeDao tradeDao) {
		this.tradeDao = tradeDao; 
	}

    /**
     * An action that renders an HTML page with a welcome message.
     * The configuration in the <code>routes</code> file means that
     * this method will be called when the application receives a
     * <code>GET</code> request with a path of <code>/</code>.
     */
    public Result index() {
        return ok(views.html.index.render(0));
    }
    
//    public Result add() {
//    		Integer i = Integer.valueOf(10); 
//    		Web3j web3j = Web3j.build(new HttpService());
//    		
//    		web3j.web3ClientVersion().observable().subscribe(x -> {
//    			String clientVersion = x.getWeb3ClientVersion(); 
//    			
//    			
//    		});
//    	
//    		return ok(views.html.index.render(2)); 
//    }
//    
//    public CompletionStage<Result> add2() {
//    		Web3j web3 = Web3j.build(new HttpService("http://localhost:7545"));
//    		
//    		Observable<Result> resultObservable = web3.web3ClientVersion().observable().map((Web3ClientVersion version) -> {
//    			return ok(views.html.eth_version.render(version.getResult()));
//    		});
//    		
//    		return observableToCompletionStage(resultObservable); 
//    }
//    
//    public CompletionStage<Result> addContract() {
//    		Trade t = new Trade();
//    		Web3j web3 = Web3j.build(new HttpService("http://localhost:7545"));
//    		
//    		Credentials credentials = Credentials.create("659cbb0e2411a44db63778987b1e22153c086a95eb6b18bdf89de078917abc63"); 
//    		RemoteCall<Trade_sol_Trade> contractCall = Trade_sol_Trade.deploy(web3, credentials, BigInteger.valueOf(10), BigInteger.valueOf(1000000), BigInteger.valueOf(0), BigInteger.valueOf(10000), BigInteger.valueOf(20000), "adsd");
//    		
//    		Observable<Result> observable = contractCall.observable().flatMap((arg) -> {
//    			Trade_sol_Trade trade = (Trade_sol_Trade)arg;
//    			
//    			return trade.pay(BigInteger.valueOf(2500)).observable();
//    		}).map((arg) -> {
//    			TransactionReceipt receipt = (TransactionReceipt)arg;
//    			
//    			return ok(views.html.all.render(receipt.getBlockNumber().toString()));
//    		});
//    		
//    		return observableToCompletionStage(observable); 
//    }
//    
//    private <T> CompletionStage<T> observableToCompletionStage(Observable<T> observable) {
//    		CompletableFuture<T> stage = new CompletableFuture<T>();
//    		
//    		observable.subscribe(new Subscriber<T>() {
//    			
//    			public void onNext(T arg) {
//    				stage.complete(arg);
//    			}
//    			
//    			public void onError(Throwable ex) {
//    				stage.completeExceptionally(ex);
//    			}
//    			
//    			public void onCompleted() {
//    			
//    			}
//    			
//		});
//    		return stage; 
//    }

}
