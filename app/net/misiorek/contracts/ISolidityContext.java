package net.misiorek.contracts;

import java.math.BigInteger;

import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;

public interface ISolidityContext {
	
	public Web3j getWeb3j();
	
	public Credentials getCredentials();
	
	public BigInteger getGasPrice();
	
	public BigInteger getGasLimit();
	
}
