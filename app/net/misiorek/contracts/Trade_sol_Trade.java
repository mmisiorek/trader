package net.misiorek.contracts;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.EventValues;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
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
public class Trade_sol_Trade extends Contract {
    private static final String BINARY = "6060604052604051610b75380380610b7583398101604052808051919060200180519190602001805160008054600160a060020a03191633600160a060020a031617815592019190506040518059106100555750595b908082528060200260200182016040525060019080516100799291602001906100b2565b50600281805161008d929160200190610119565b5050600391909155600455600060058190556006556007805461ffff191690556101d4565b828054828255906000526020600020908101928215610109579160200282015b828111156101095782518254600160a060020a031916600160a060020a0391909116178255602092909201916001909101906100d2565b50610115929150610193565b5090565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f1061015a57805160ff1916838001178555610187565b82800160010185558215610187579182015b8281111561018757825182559160200191906001019061016c565b506101159291506101ba565b6101b791905b80821115610115578054600160a060020a0319168155600101610199565b90565b6101b791905b8082111561011557600081556001016101c0565b610992806101e36000396000f3006060604052600436106100985763ffffffff7c01000000000000000000000000000000000000000000000000000000006000350416630c2487b6811461009d5780631b9265b8146100c2578063427c5926146100ca578063968afb95146100d2578063c8a9c276146100da578063cabc4c4c146100ed578063cd28624014610100578063ce845d1d14610113578063f402ec0d14610126575b600080fd5b34156100a857600080fd5b6100b061014a565b60405190815260200160405180910390f35b6100b0610150565b6100b0610421565b6100b06105e8565b34156100e557600080fd5b6100b061078e565b34156100f857600080fd5b6100b0610794565b341561010b57600080fd5b6100b061079a565b341561011e57600080fd5b6100b06107a0565b61012e6107af565b604051600160a060020a03909116815260200160405180910390f35b60035481565b6000348180808080808611801561016a575060075460ff16155b15610261576005546003540393508386116101855785610187565b835b60058054820190819055600354978290039791945010610261576007805460ff1916600117905560035494909401937fecb1bb9e764f768147cfe45a716eb20c5a308b474b6641129aaba11b70ffab226002604051602080825282546002600019610100600184161502019091160490820181905281906040820190849080156102525780601f1061022757610100808354040283529160200191610252565b820191906000526020600020905b81548152906001019060200180831161023557829003601f168201915b50509250505060405180910390a15b6000861180156102795750600754610100900460ff16155b15610371576006546004540391508186116102945785610296565b815b60068054820190819055600454978290039791925010610371576007805461ff00191661010017905560045494909401937ec62fbd03ec782d2e04d410cfd381487c9a75c374d9349530a98b02b4d80c4f6002604051602080825282546002600019610100600184161502019091160490820181905281906040820190849080156103625780601f1061033757610100808354040283529160200191610362565b820191906000526020600020905b81548152906001019060200180831161034557829003601f168201915b50509250505060405180910390a15b60008611156103ab57600160a060020a03331686156108fc0287604051600060405180830381858888f1935050505015156103ab57600080fd5b60008511156103e757600054600160a060020a031685156108fc0286604051600060405180830381858888f1935050505015156103e757600080fd5b60075460ff1680156104005750600754610100900460ff165b1561041357600054600160a060020a0316ff5b853403965050505050505090565b6007546000908190819060ff16151561043957600080fd5b6006546004546007549190039250610100900460ff168061045957503482115b1561049957600160a060020a0333163480156108fc0290604051600060405180830381858888f19350505050151561049057600080fd5b600092506105e3565b3482106104a657346104a8565b815b6006805482019055905060003482900311156104f35733600160a060020a03166108fc8234039081150290604051600060405180830381858888f1935050505015156104f357600080fd5b600054600454600160a060020a039091169080156108fc0290604051600060405180830381858888f19350505050151561052c57600080fd5b7ec62fbd03ec782d2e04d410cfd381487c9a75c374d9349530a98b02b4d80c4f6002604051602080825282546002600019610100600184161502019091160490820181905281906040820190849080156105c75780601f1061059c576101008083540402835291602001916105c7565b820191906000526020600020905b8154815290600101906020018083116105aa57829003601f168201915b50509250505060405180910390a1600054600160a060020a0316ff5b505090565b60055460035460075460009290910390829060ff168061060757508134105b1561063e57600160a060020a0333163480156108fc0290604051600060405180830381858888f19350505050151561049057600080fd5b34821061064b573461064d565b815b6005805482019055905060003482900311156106985733600160a060020a03166108fc8234039081150290604051600060405180830381858888f19350505050151561069857600080fd5b6007805460ff19166001179055600054600354600160a060020a039091169080156108fc0290604051600060405180830381858888f1935050505015156106de57600080fd5b7fecb1bb9e764f768147cfe45a716eb20c5a308b474b6641129aaba11b70ffab2260026040516020808252825460026000196101006001841615020190911604908201819052819060408201908490801561077a5780601f1061074f5761010080835404028352916020019161077a565b820191906000526020600020905b81548152906001019060200180831161075d57829003601f168201915b50509250505060405180910390a192915050565b60045481565b60055481565b60065481565b600160a060020a033016315b90565b6000806107ba610820565b604051809103906000f08015156107d057600080fd5b9050600180548060010182816107e6919061082f565b506000918252602090912001805473ffffffffffffffffffffffffffffffffffffffff1916600160a060020a0383161790559050805b5090565b60405160f48061087383390190565b81548183558181151161085357600083815260209020610853918101908301610858565b505050565b6107ac91905b8082111561081c576000815560010161085e5600606060405260018054600160a060020a033316600160a060020a03199091161790556000805460a060020a60ff021916905560b58061003f6000396000f300606060405260005474010000000000000000000000000000000000000000900460ff161580604857506000543373ffffffffffffffffffffffffffffffffffffffff9081169116145b1515605257600080fd5b6000805473ffffffffffffffffffffffffffffffffffffffff19163373ffffffffffffffffffffffffffffffffffffffff161790550000a165627a7a72305820d28fee2ab0a619267d299bcb4fc27d0707c8f6d6ec845ddd3b40f0369e6915260029a165627a7a72305820f329124f7275497886335a765b6527e6afdefb043ce6382f4a57848ec63fca280029";

    protected Trade_sol_Trade(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected Trade_sol_Trade(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
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

    public RemoteCall<TransactionReceipt> pay(BigInteger weiValue) {
        Function function = new Function(
                "pay", 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
    }

    public RemoteCall<TransactionReceipt> payRealization(BigInteger weiValue) {
        Function function = new Function(
                "payRealization", 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
    }

    public RemoteCall<TransactionReceipt> payAdvance(BigInteger weiValue) {
        Function function = new Function(
                "payAdvance", 
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

    public RemoteCall<BigInteger> currentBalance() {
        Function function = new Function("currentBalance", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<TransactionReceipt> addBuyerStorage(BigInteger weiValue) {
        Function function = new Function(
                "addBuyerStorage", 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
    }

    public static RemoteCall<Trade_sol_Trade> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, BigInteger initialWeiValue, BigInteger aA, BigInteger rA, String _id) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(aA), 
                new org.web3j.abi.datatypes.generated.Uint256(rA), 
                new org.web3j.abi.datatypes.Utf8String(_id)));
        return deployRemoteCall(Trade_sol_Trade.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor, initialWeiValue);
    }

    public static RemoteCall<Trade_sol_Trade> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, BigInteger initialWeiValue, BigInteger aA, BigInteger rA, String _id) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(aA), 
                new org.web3j.abi.datatypes.generated.Uint256(rA), 
                new org.web3j.abi.datatypes.Utf8String(_id)));
        return deployRemoteCall(Trade_sol_Trade.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor, initialWeiValue);
    }

    public static Trade_sol_Trade load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new Trade_sol_Trade(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    public static Trade_sol_Trade load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Trade_sol_Trade(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static class AdvanceHasBeenPaidEventResponse {
        public String _id;
    }

    public static class RealizationHasBeenPaidEventResponse {
        public String _id;
    }
}
