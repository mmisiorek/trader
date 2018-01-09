package models;

import java.math.BigInteger;
import java.util.Date;
import java.util.UUID;

import javax.persistence.*;

@Entity
@Table(name="trade")
public class Trade {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="unique_hash")
	private String uniqueHash;
	
	@Column(name="contract_address")
	private String contractAddress;
	
	@Column(name="owner_address")
	private String ownerAddress;
	
	@Column(name="advance_amount")
	private String advanceAmount;
	
	@Column(name="realization_amount")
	private String realizationAmount;
	
	@Column(name="advance_date")
	private Date advanceDate;
	
	@Column(name="realization_date")
	private Date realizationDate;
	
	@Column(name="is_advance_paid")
	private Boolean isAdvancePaid;
	
	@Column(name="is_realization_paid")
	private Boolean isRealizationPaid;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getUniqueHash() {
		return uniqueHash;
	}
	
	public void setUniqueHash(String uniqueHash) {
		this.uniqueHash = uniqueHash; 
	}
	
	public BigInteger getContractAddress() {
		return new BigInteger(contractAddress);
	}
	
	public void setContractAddress(BigInteger contractAddress) {
		this.contractAddress = contractAddress.toString();
	}
	
	public BigInteger getOwnerAddress() {
		return new BigInteger(ownerAddress);
	}
	
	public void setOwnerAddress(BigInteger ownerAddress) {
		this.ownerAddress = ownerAddress.toString();
	}
	
	public BigInteger getAdvanceAmount() {
		return new BigInteger(advanceAmount);
	}
	
	public void setAdvanceAmount(BigInteger advanceAmount) {
		this.advanceAmount = advanceAmount.toString(); 
	}
	
	public BigInteger getRealizationAmount() {
		return new BigInteger(realizationAmount); 
	}
	
	public void setRealizationAmount(BigInteger realizationAmount) {
		this.realizationAmount = realizationAmount.toString(); 
	}
	
	public Date getAdvanceDate() {
		return advanceDate;
	}
	
	public void setAdvanceDate(Date advanceDate) {
		this.advanceDate = advanceDate; 
	}
	
	public Date getRealizationDate() {
		return realizationDate;
	}
	
	public void setRealizationDate(Date realizationDate) {
		this.realizationDate = realizationDate; 
	}
	
	public Boolean getIsAdvancePaid() {
		return isAdvancePaid;
	}
	
	public void setIsAdvancePaid(Boolean isAdvancePaid) {
		this.isAdvancePaid = isAdvancePaid; 
	}
	
	public Boolean getIsRealizationPaid() {
		return isRealizationPaid;
	}
	
	public void setIsRealizationPaid(Boolean isRealizationPaid) {
		this.isRealizationPaid = isRealizationPaid;
	}
	
	public static Trade createNew(BigInteger ownerAddress) {
		Trade t = new Trade();
		
		t.setOwnerAddress(ownerAddress);
		t.setAdvanceAmount(BigInteger.ZERO);
		t.setRealizationAmount(BigInteger.ZERO);
		t.setIsAdvancePaid(Boolean.FALSE);
		t.setIsRealizationPaid(Boolean.FALSE);
		
		return t;
	}
	
	public static String createRandomHash() {
		return UUID.randomUUID().toString();
	}

}
