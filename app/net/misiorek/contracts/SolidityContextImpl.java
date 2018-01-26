package net.misiorek.contracts;

import java.math.BigInteger;
import java.util.Map;

import javax.inject.Singleton;

import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;

@Singleton
public class SolidityContextImpl implements ISolidityContext {

	private Web3j web3j;
	
	private Credentials credentials;
	
	private BigInteger gasLimit;
	
	private BigInteger gasPrice;
	
	public SolidityContextImpl() {
		Map<String, String> env = System.getenv();
		String protocol = "http";
		String host = "localhost";
		String port = "8545"; 
		String privateKey = "659cbb0e2411a44db63778987b1e22153c086a95eb6b18bdf89de078917abc63";
		
		String gasPriceStr = "10"; 
		String gasLimitStr = "1000000"; 
		
		if(env.containsKey("ETHEREUM_CLIENT_PROTOCOL")) {
			protocol = env.get("ETHEREUM_CLIENT_PROTOCOL"); 
		}
		
		if(env.containsKey("ETHEREUM_CLIENT_HOSTNAME")) {
			host = env.get("ETHEREUM_CLIENT_HOSTNAME");
		}
		
		if(env.containsKey("ETHEREUM_CLIENT_PORT")) {
			port = env.get("ETHEREUM_CLIENT_PORT"); 
		}
		
		if(env.containsKey("ETHEREUM_PRIVATE_KEY")) {
			privateKey = env.get("ETHEREUM_PRIVATE_KEY"); 
		}
		
		if(env.containsKey("ETHEREUM_GAS_PRICE")) {
			gasPriceStr = env.get("ETHEREUM_GAS_PRICE"); 
		}
		
		if(env.containsKey("ETHEREUM_GAS_LIMIT")) {
			gasLimitStr = env.get("ETHEREUM_GAS_LIMIT"); 
		}
		
		this.web3j = Web3j.build(new HttpService(protocol+"://"+host+":"+port));
		this.credentials = Credentials.create(privateKey);
		this.gasPrice = BigInteger.valueOf(Long.valueOf(gasPriceStr).longValue());
		this.gasLimit = BigInteger.valueOf(Long.valueOf(gasLimitStr).longValue()); 
	}
	
	public Web3j	 getWeb3j() {
		return web3j;
	}
	
	public Credentials getCredentials() {
		return credentials;
	}
	
	public BigInteger getGasLimit() {
		return gasLimit;
	}
	
	public BigInteger getGasPrice() {
		return gasPrice;
	}
	
}
