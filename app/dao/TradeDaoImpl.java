package dao;

import java.math.BigInteger;
import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.hibernate.jpa.internal.QueryImpl.JpaPositionalParameterRegistrationImpl;

import com.google.inject.Singleton;

import models.Trade;
import net.sf.ehcache.hibernate.HibernateUtil;
import play.db.jpa.JPAApi;

@Singleton
public class TradeDaoImpl implements ITradeDao {
	
	private JPAApi jpaApi;
	
	@Inject
	public TradeDaoImpl(JPAApi jpaApi) {
		this.jpaApi = jpaApi; 
	}

	@Override
	public List<Trade> findAll() {
		CriteriaQuery<Trade> criteria = getCriteriaBuilder().createQuery(Trade.class);
		
		Root<Trade> trade = criteria.from(Trade.class);
		criteria.select(trade);
		
		return jpaApi.em().createQuery(criteria).getResultList(); 
	}
	
	@Override
	public List<Trade> findAllNotCompleted() {
		return jpaApi.em().createQuery("SELECT t FROM models.Trade AS t WHERE t.isResolved = ?1")
					.setParameter(1, false).getResultList();
	}
	
	@Override
	public Trade findByContractAddress(BigInteger contractAddress) throws EntityNotFoundException {
		List<Trade> list = (List<Trade>)jpaApi.em().createQuery("SELECT t FROM models.Trade AS t WHERE t.contractAddress = ?1")
												.setParameter(1, contractAddress.toString()).getResultList(); 
		
		if(list.size() == 0) {
			throw new EntityNotFoundException();
		} else {
			return list.get(0);
		}
	}

	@Override
	public Trade find(int id) throws EntityNotFoundException {
		// TODO Auto-generated method stub
		return jpaApi.em().find(Trade.class, id);
	}
	
	@Override
	public Trade createNew() {
		Trade t = new Trade();
		
		t.setUniqueHash(findUniqueHash());
		t.setAdvanceAmount(BigInteger.ZERO);
		t.setRealizationAmount(BigInteger.ZERO);
		t.setIsAdvancePaid(Boolean.FALSE);
		t.setIsRealizationPaid(Boolean.FALSE);
		
		return t;
	}
	
	@Override 
	public Trade findByUniqueHash(String uniqueHash) throws EntityNotFoundException {
		List<Trade> list = jpaApi.em().createQuery("SELECT t FROM models.Trade AS t WHERE t.uniqueHash = ?1")
										.setParameter(1, uniqueHash).getResultList();
		
		if(list.size() == 0) {
			throw new EntityNotFoundException();
		}
		
		return list.get(0); 
	}
	
	@Override
	public JPAApi getJPAApi() {
		return jpaApi; 
	}
	
	private CriteriaBuilder getCriteriaBuilder() {
		return jpaApi.em().getCriteriaBuilder(); 
	}
	
	private String findUniqueHash() {
		String candidate = null;
		List<?> list = null;
		
		do {
			candidate = Trade.createRandomHash(); 
			list = jpaApi.em().createQuery("SELECT t FROM models.Trade AS t WHERE t.uniqueHash = ?1")
						.setParameter(1, candidate).getResultList();
			
		} while(list.size() > 0);
		
		return candidate;
	}

}
