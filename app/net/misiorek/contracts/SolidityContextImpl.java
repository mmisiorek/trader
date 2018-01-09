package net.misiorek.contracts;

import java.math.BigInteger;

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
		this.web3j = Web3j.build(new HttpService("http://localhost:7545"));
		this.credentials = Credentials.create("659cbb0e2411a44db63778987b1e22153c086a95eb6b18bdf89de078917abc63");
		this.gasPrice = BigInteger.valueOf(10);
		this.gasLimit = BigInteger.valueOf(1000000); 
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
