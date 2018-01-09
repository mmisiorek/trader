package dao;

import java.math.BigInteger;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import com.google.inject.ImplementedBy;

import models.*;
import play.db.jpa.JPAApi; 

@ImplementedBy(TradeDaoImpl.class)
public interface ITradeDao {

	public List<Trade> findAll(); 
	public List<Trade> findAllNotCompleted();
	public Trade find(int id) throws EntityNotFoundException; 
	public Trade findByUniqueHash(String uniqueHash) throws EntityNotFoundException; 
	public Trade findByContractAddress(BigInteger contractAddress) throws EntityNotFoundException; 
	public Trade createNew();
	public JPAApi getJPAApi();
	
}
