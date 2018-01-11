pragma solidity ^0.4.0;

/* 
 
 This is the main contract

 */
contract Trade {
    
    address private owner;
    // accept payments from sub-accounts only which were created by this contract; it needs to be done
    address[] private buyerStorages;
    string private id;
    
    uint public advanceAmount;
    uint public realizationAmount;
    
    uint public advancePaid;
    uint public realizationPaid;
    
    bool isAdvancedPaid;
    bool isRealizationPaid;
    
    event AdvanceHasBeenPaid(string _id);
    event RealizationHasBeenPaid(string _id); 
    
    modifier ifAdvanceIsPaid {
        require(isAdvancedPaid);
        _;
    }
    
    // _id unique id for the contract 
    //TODO owner's account must be takes as an argument here - sender is not the right person
    function Trade(uint aA, uint rA, string _id) payable {
        owner = msg.sender;
        buyerStorages = new address[](0);
        id = _id; 
        advanceAmount = aA;
        realizationAmount = rA;
        
        advancePaid = 0;
        realizationPaid = 0;
        
        isAdvancedPaid = false;
        isRealizationPaid = false;
    }
    
    /** 
     * returns how many weis were accepted to fulfull the contract
     */
    function pay() payable returns(uint256) {
        uint remainingValue = msg.value;
        uint valueToOwner = 0; 
        
        if(remainingValue > 0 && !isAdvancedPaid) {
            uint remainingAdvance = advanceAmount-advancePaid;
            uint smallerAValue = remainingValue > remainingAdvance ? remainingAdvance : remainingValue;
            
            remainingValue = remainingValue-smallerAValue;
            advancePaid = advancePaid+smallerAValue;
            
            if(advancePaid >= advanceAmount) {
                isAdvancedPaid = true;
                
                valueToOwner = valueToOwner+advanceAmount;
                AdvanceHasBeenPaid(id);
            }
        }
        
        if(remainingValue > 0 && !isRealizationPaid) {
            uint remainingRealization = realizationAmount-realizationPaid;
            uint smallerRValue = remainingValue > remainingRealization ? remainingRealization : remainingValue;
            
            remainingValue = remainingValue-smallerRValue;
            realizationPaid = realizationPaid+smallerRValue;
            
            if(realizationPaid >= realizationAmount) {
                isRealizationPaid = true;
                
                valueToOwner = valueToOwner+realizationAmount;
                RealizationHasBeenPaid(id);
            }
        }
        
        // return your money when you paid too much
        if(remainingValue > 0) {
            msg.sender.transfer(remainingValue); 
        }
        
        // transfer money to owner's account when 
        if(valueToOwner > 0) {
            owner.transfer(valueToOwner);
        }
        
        // destroy the contract when both are paid
        if(isAdvancedPaid && isRealizationPaid) {
            selfdestruct(owner); 
        }
        
        return msg.value-remainingValue;
    }
    
    /**
     * returns how many weis were accepted by the contract 
     */
    function payAdvance() payable returns (uint256) {
        uint toPay = advanceAmount-advancePaid;
        
        if(isAdvancedPaid || msg.value < toPay) {
            msg.sender.transfer(msg.value);
            return 0;
        }
        
        uint minVal = toPay < msg.value ? toPay : msg.value;
        advancePaid = advancePaid+minVal;
        
        if(msg.value-minVal > 0) {
            msg.sender.transfer(msg.value-minVal);
        }
        
        isAdvancedPaid = true;
        owner.transfer(advanceAmount);
        AdvanceHasBeenPaid(id);
        
        return minVal;
    }
    
    function payRealization() payable ifAdvanceIsPaid returns(uint256) {
        uint toPay = realizationAmount-realizationPaid;
        
        if(isRealizationPaid || toPay > msg.value) {
            msg.sender.transfer(msg.value);
            return 0;
        }
        
        uint minVal = toPay < msg.value ? toPay : msg.value;
        realizationPaid = realizationPaid+minVal;
        
        if(msg.value-minVal > 0) {
            msg.sender.transfer(msg.value-minVal);
        }
        
        owner.transfer(realizationAmount);
        RealizationHasBeenPaid(id); 
        selfdestruct(owner);
        
        return minVal;
    }
    
    function addBuyerStorage() payable returns(address) {
        address newContract = new TradeBuyerStorage();
        buyerStorages.push(newContract);
        return newContract;
    }
    
    function currentBalance() view returns (uint256) {
        return this.balance;
    }
}

/* 
 Sub-account for the buyer. It needs to be finished.
 */
contract TradeBuyerStorage {
    
    address owner;
    bool hasOwner;
    address tradeContractOwner;
    
    modifier onlyOwnerIfExists {
        require(!hasOwner || msg.sender == owner);
        _;
    }
    
    modifier onlyOwner {
        require(hasOwner && msg.sender == owner);
        _; 
    }
    
    modifier onlyTradeContract {
        require(msg.sender == tradeContractOwner);
        _;
    }
    
    function TradeBuyerStorage() payable {
        tradeContractOwner = msg.sender;
        hasOwner = false;
    }
    
    function() payable onlyOwnerIfExists {
        owner = msg.sender;
    }
    
}