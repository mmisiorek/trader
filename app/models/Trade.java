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
		return toBigInteger(contractAddress);
	}
	
	public void setContractAddress(BigInteger contractAddress) {
		this.contractAddress = toAddress(contractAddress); 
	}
	
	public void setContractAddress(String address) {
		this.contractAddress = address;
	}
	
	public BigInteger getOwnerAddress() {
		return toBigInteger(ownerAddress);
	}
	
	public void setOwnerAddress(BigInteger ownerAddress) {
		this.ownerAddress = toAddress(ownerAddress);
	}
	
	public void setOwnerAddress(String address) {
		this.ownerAddress = address;
	}
	
	public BigInteger getAdvanceAmount() {
		return toBigInteger(advanceAmount);
	}
	
	public void setAdvanceAmount(BigInteger advanceAmount) {
		this.advanceAmount = toString(advanceAmount); 
	}
	
	public void setAdvanceAmount(String amount) {
		this.advanceAmount = amount;
	}
	
	public BigInteger getRealizationAmount() {
		return toBigInteger(realizationAmount); 
	}
	
	public void setRealizationAmount(BigInteger realizationAmount) {
		this.realizationAmount = toString(realizationAmount); 
	}
	
	public void setRealizationAmount(String amount) {
		this.realizationAmount = amount;
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
	
	public static String toAddress(BigInteger integer) {
		if(integer == null) {
			return null; 
		}
		
		return "0x"+integer.toString(16);
	}
	
	private static String toString(BigInteger integer) {
		if(integer == null) {
			return null;
		}
		
		return integer.toString();
	}
	
	private static BigInteger toBigInteger(String value) {
		if(value == null) {
			return null;
		}
		
		try {			
			return new BigInteger(value);
		} catch(NumberFormatException e) {
			value = value.replace("0x", "");
			return new BigInteger(value, 16); 
		}
		
	}

}
