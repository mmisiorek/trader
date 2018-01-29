package contracts;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.EventValues;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import rx.Observable;
import rx.functions.Func1;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 3.2.0.
 */
public class SubaccountsOnlyTrade extends Contract {
    private static final String BINARY = "0x6060604052604051620017273803806200172783398101604052808051906020019091908051906020019091908051820191905050828282336000806101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555080600190805190602001906200008f92919062000129565b508260028190555081600381905550600060048190555060006005819055506000600660006101000a81548160ff0219169083151502179055506000600660016101000a81548160ff0219169083151502179055505050506000604051805910620000f75750595b9080825280602002602001820160405250600790805190602001906200011f929190620001b0565b50505050620002ad565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f106200016c57805160ff19168380011785556200019d565b828001600101855582156200019d579182015b828111156200019c5782518255916020019190600101906200017f565b5b509050620001ac91906200023f565b5090565b8280548282559060005260206000209081019282156200022c579160200282015b828111156200022b5782518260006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555091602001919060010190620001d1565b5b5090506200023b919062000267565b5090565b6200026491905b808211156200026057600081600090555060010162000246565b5090565b90565b620002aa91905b80821115620002a657600081816101000a81549073ffffffffffffffffffffffffffffffffffffffff0219169055506001016200026e565b5090565b90565b61146a80620002bd6000396000f3006060604052600436106100ba576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff1680630c2487b6146100fc5780632070e0f3146101255780633f5cea4914610188578063427c5926146101b1578063679f1003146101cf57806383197ef0146101f8578063968afb951461020d578063c290d6911461022b578063c820d66314610257578063c8a9c276146102a1578063cabc4c4c146102ca578063cd286240146102f3575b3373ffffffffffffffffffffffffffffffffffffffff166108fc349081150290604051600060405180830381858888f1935050505015156100fa57600080fd5b005b341561010757600080fd5b61010f61031c565b6040518082815260200191505060405180910390f35b341561013057600080fd5b6101466004808035906020019091905050610322565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b341561019357600080fd5b61019b610361565b6040518082815260200191505060405180910390f35b6101b961038e565b6040518082815260200191505060405180910390f35b34156101da57600080fd5b6101e2610456565b6040518082815260200191505060405180910390f35b341561020357600080fd5b61020b610483565b005b610215610518565b6040518082815260200191505060405180910390f35b61024160048080359060200190919050506105f9565b6040518082815260200191505060405180910390f35b61025f6106d0565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b34156102ac57600080fd5b6102b461075d565b6040518082815260200191505060405180910390f35b34156102d557600080fd5b6102dd610763565b6040518082815260200191505060405180910390f35b34156102fe57600080fd5b610306610769565b6040518082815260200191505060405180910390f35b60025481565b60078181548110151561033157fe5b90600052602060002090016000915054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b6000600660019054906101000a900460ff1615610381576000905061038b565b6005546003540390505b90565b600080600660009054906101000a900460ff1615156103ac57600080fd5b60008090505b600780549050811015610451573373ffffffffffffffffffffffffffffffffffffffff166007828154811015156103e557fe5b906000526020600020900160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16141561044457610435610361565b9150610441823361076f565b92505b80806001019150506103b2565b505090565b6000600660009054906101000a900460ff16156104765760009050610480565b6004546002540390505b90565b6000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff161415156104de57600080fd5b6000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16ff5b600080600660009054906101000a900460ff1615806105445750600660019054906101000a900460ff16155b151561054f57600080fd5b60008090505b6007805490508110156105f4573373ffffffffffffffffffffffffffffffffffffffff1660078281548110151561058857fe5b906000526020600020900160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1614156105e7576105d8610456565b91506105e4823361076f565b92505b8080600101915050610555565b505090565b6000600660009054906101000a900460ff1615806106245750600660019054906101000a900460ff16155b151561062f57600080fd5b60008090505b6007805490508110156106ca573373ffffffffffffffffffffffffffffffffffffffff1660078281548110151561066857fe5b906000526020600020900160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1614156106bd576106ba833361076f565b91505b8080600101915050610635565b50919050565b6000806106db610aba565b604051809103906000f08015156106f157600080fd5b9050600780548060010182816107079190610aca565b9160005260206000209001600083909190916101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550508091505090565b60035481565b60045481565b60055481565b600080600080600080600088955060009450600097506000861180156107a25750600660009054906101000a900460ff16155b156108bd576107af610456565b93508386116107be57856107c0565b835b9250828603955082600454016004819055506002546004541015156108bc576001600660006101000a81548160ff021916908315150217905550600254850194507fecb1bb9e764f768147cfe45a716eb20c5a308b474b6641129aaba11b70ffab22600160405180806020018281038252838181546001816001161561010002031660029004815260200191508054600181600116156101000203166002900480156108ad5780601f10610882576101008083540402835291602001916108ad565b820191906000526020600020905b81548152906001019060200180831161089057829003601f168201915b50509250505060405180910390a15b5b6000861180156108da5750600660019054906101000a900460ff16155b156109f4576108e7610361565b91508186116108f657856108f8565b815b9050808603955080600554016005819055506003546005541015156109f3576001600660016101000a81548160ff021916908315150217905550600354850194507ec62fbd03ec782d2e04d410cfd381487c9a75c374d9349530a98b02b4d80c4f600160405180806020018281038252838181546001816001161561010002031660029004815260200191508054600181600116156101000203166002900480156109e45780601f106109b9576101008083540402835291602001916109e4565b820191906000526020600020905b8154815290600101906020018083116109c757829003601f168201915b50509250505060405180910390a15b5b6000861115610a3e573373ffffffffffffffffffffffffffffffffffffffff166108fc879081150290604051600060405180830381858888f193505050501515610a3d57600080fd5b5b6000851115610aa9576000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff166108fc869081150290604051600060405180830381858888f193505050501515610aa857600080fd5b5b858903965050505050505092915050565b60405161092380610b1c83390190565b815481835581811511610af157818360005260206000209182019101610af09190610af6565b5b505050565b610b1891905b80821115610b14576000816000905550600101610afc565b5090565b905600606060405233600160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555060008060146101000a81548160ff0219169083151502179055506108b58061006e6000396000f30060606040526004361061006d576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff1680631b9265b81461013d578063427c59261461015b5780638784ea3714610179578063968afb9514610197578063ce845d1d146101b5575b600060149054906101000a900460ff1615806100d557506000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff16145b15156100e057600080fd5b336000806101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055506001600060146101000a81548160ff021916908315150217905550005b6101456101de565b6040518082815260200191505060405180910390f35b610163610388565b6040518082815260200191505060405180910390f35b610181610527565b6040518082815260200191505060405180910390f35b61019f6106cb565b6040518082815260200191505060405180910390f35b34156101c057600080fd5b6101c861086a565b6040518082815260200191505060405180910390f35b600080600060149054906101000a900460ff16158061024957506000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff16145b151561025457600080fd5b600060149054906101000a900460ff16156102cb576000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff166108fc349081150290604051600060405180830381858888f1935050505015156102ca57600080fd5b5b600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1690508073ffffffffffffffffffffffffffffffffffffffff1663c290d691346000604051602001526040518263ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180828152602001915050602060405180830381600087803b151561036757600080fd5b6102c65a03f1151561037857600080fd5b5050506040518051905091505090565b600080600060149054906101000a900460ff1615806103f357506000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff16145b15156103fe57600080fd5b600060149054906101000a900460ff1615610475576000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff166108fc349081150290604051600060405180830381858888f19350505050151561047457600080fd5b5b600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1690508073ffffffffffffffffffffffffffffffffffffffff1663427c59266000604051602001526040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b151561050657600080fd5b6102c65a03f1151561051757600080fd5b5050506040518051905091505090565b600080600060149054906101000a900460ff16158061059257506000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff16145b151561059d57600080fd5b600060149054906101000a900460ff1615610614576000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff166108fc349081150290604051600060405180830381858888f19350505050151561061357600080fd5b5b3073ffffffffffffffffffffffffffffffffffffffff1631340190506000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff166108fc829081150290604051600060405180830381858888f19350505050151561069157600080fd5b6000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16ff5b600080600060149054906101000a900460ff16158061073657506000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff16145b151561074157600080fd5b600060149054906101000a900460ff16156107b8576000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff166108fc349081150290604051600060405180830381858888f1935050505015156107b757600080fd5b5b600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1690508073ffffffffffffffffffffffffffffffffffffffff1663968afb956000604051602001526040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b151561084957600080fd5b6102c65a03f1151561085a57600080fd5b5050506040518051905091505090565b60003073ffffffffffffffffffffffffffffffffffffffff16319050905600a165627a7a7230582008968a46b70a3531507d32d367989663595bf2f16dda363cab02b215f05940ff0029a165627a7a723058208e0448ea7b50f508166381262d6fa155104821c4c865f8dc70be459acc88d5cd0029";

    protected static final HashMap<String, String> _addresses;

    static {
        _addresses = new HashMap<>();
    }

    protected SubaccountsOnlyTrade(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected SubaccountsOnlyTrade(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public List<AdvanceHasBeenPaidEventResponse> getAdvanceHasBeenPaidEvents(TransactionReceipt transactionReceipt) {
        final Event event = new Event("AdvanceHasBeenPaid", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        List<EventValues> valueList = extractEventParameters(event, transactionReceipt);
        ArrayList<AdvanceHasBeenPaidEventResponse> responses = new ArrayList<AdvanceHasBeenPaidEventResponse>(valueList.size());
        for (EventValues eventValues : valueList) {
            AdvanceHasBeenPaidEventResponse typedResponse = new AdvanceHasBeenPaidEventResponse();
            typedResponse._id = (String) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<AdvanceHasBeenPaidEventResponse> advanceHasBeenPaidEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        final Event event = new Event("AdvanceHasBeenPaid", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(event));
        return web3j.ethLogObservable(filter).map(new Func1<Log, AdvanceHasBeenPaidEventResponse>() {
            @Override
            public AdvanceHasBeenPaidEventResponse call(Log log) {
                EventValues eventValues = extractEventParameters(event, log);
                AdvanceHasBeenPaidEventResponse typedResponse = new AdvanceHasBeenPaidEventResponse();
                typedResponse._id = (String) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public List<RealizationHasBeenPaidEventResponse> getRealizationHasBeenPaidEvents(TransactionReceipt transactionReceipt) {
        final Event event = new Event("RealizationHasBeenPaid", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        List<EventValues> valueList = extractEventParameters(event, transactionReceipt);
        ArrayList<RealizationHasBeenPaidEventResponse> responses = new ArrayList<RealizationHasBeenPaidEventResponse>(valueList.size());
        for (EventValues eventValues : valueList) {
            RealizationHasBeenPaidEventResponse typedResponse = new RealizationHasBeenPaidEventResponse();
            typedResponse._id = (String) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<RealizationHasBeenPaidEventResponse> realizationHasBeenPaidEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        final Event event = new Event("RealizationHasBeenPaid", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(event));
        return web3j.ethLogObservable(filter).map(new Func1<Log, RealizationHasBeenPaidEventResponse>() {
            @Override
            public RealizationHasBeenPaidEventResponse call(Log log) {
                EventValues eventValues = extractEventParameters(event, log);
                RealizationHasBeenPaidEventResponse typedResponse = new RealizationHasBeenPaidEventResponse();
                typedResponse._id = (String) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public RemoteCall<BigInteger> advanceAmount() {
        Function function = new Function("advanceAmount", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<String> buyerStorages(BigInteger param0) {
        Function function = new Function("buyerStorages", 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<BigInteger> getRemainingRealization() {
        Function function = new Function("getRemainingRealization", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<TransactionReceipt> payRealization(BigInteger weiValue) {
        Function function = new Function(
                "payRealization", 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
    }

    public RemoteCall<BigInteger> getRemainingAdvance() {
        Function function = new Function("getRemainingAdvance", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<TransactionReceipt> destroy() {
        Function function = new Function(
                "destroy", 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> payAdvance(BigInteger weiValue) {
        Function function = new Function(
                "payAdvance", 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
    }

    public RemoteCall<TransactionReceipt> pay(BigInteger amount, BigInteger weiValue) {
        Function function = new Function(
                "pay", 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(amount)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
    }

    public RemoteCall<TransactionReceipt> addSubaccountsOnlyTradeSubaccount(BigInteger weiValue) {
        Function function = new Function(
                "addSubaccountsOnlyTradeSubaccount", 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
    }

    public RemoteCall<BigInteger> realizationAmount() {
        Function function = new Function("realizationAmount", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<BigInteger> advancePaid() {
        Function function = new Function("advancePaid", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<BigInteger> realizationPaid() {
        Function function = new Function("realizationPaid", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public static RemoteCall<SubaccountsOnlyTrade> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, BigInteger initialWeiValue, BigInteger aA, BigInteger rA, String _id) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(aA), 
                new org.web3j.abi.datatypes.generated.Uint256(rA), 
                new org.web3j.abi.datatypes.Utf8String(_id)));
        return deployRemoteCall(SubaccountsOnlyTrade.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor, initialWeiValue);
    }

    public static RemoteCall<SubaccountsOnlyTrade> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, BigInteger initialWeiValue, BigInteger aA, BigInteger rA, String _id) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(aA), 
                new org.web3j.abi.datatypes.generated.Uint256(rA), 
                new org.web3j.abi.datatypes.Utf8String(_id)));
        return deployRemoteCall(SubaccountsOnlyTrade.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor, initialWeiValue);
    }

    public static SubaccountsOnlyTrade load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new SubaccountsOnlyTrade(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    public static SubaccountsOnlyTrade load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new SubaccountsOnlyTrade(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected String getStaticDeployedAddress(String networkId) {
        return _addresses.get(networkId);
    }

    public static String getPreviouslyDeployedAddress(String networkId) {
        return _addresses.get(networkId);
    }

    public static class AdvanceHasBeenPaidEventResponse {
        public String _id;
    }

    public static class RealizationHasBeenPaidEventResponse {
        public String _id;
    }
}
