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
public class PublicTrade extends Contract {
    private static final String BINARY = "0x606060405260405162000fdb38038062000fdb83398101604052808051906020019091908051906020019091908051820191905050828282336000806101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555080600190805190602001906200008f92919062000129565b508260028190555081600381905550600060048190555060006005819055506000600660006101000a81548160ff0219169083151502179055506000600660016101000a81548160ff0219169083151502179055505050506000604051805910620000f75750595b9080825280602002602001820160405250600790805190602001906200011f929190620001b0565b50505050620002ad565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f106200016c57805160ff19168380011785556200019d565b828001600101855582156200019d579182015b828111156200019c5782518255916020019190600101906200017f565b5b509050620001ac91906200023f565b5090565b8280548282559060005260206000209081019282156200022c579160200282015b828111156200022b5782518260006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555091602001919060010190620001d1565b5b5090506200023b919062000267565b5090565b6200026491905b808211156200026057600081600090555060010162000246565b5090565b90565b620002aa91905b80821115620002a657600081816101000a81549073ffffffffffffffffffffffffffffffffffffffff0219169055506001016200026e565b5090565b90565b610d1e80620002bd6000396000f3006060604052600436106100c5576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff1680630c2487b61461018e5780631b9265b8146101b757806331cbf5e3146101d55780633f5cea4914610220578063427c59261461024957806355a3b2c114610267578063679f1003146102b457806383197ef0146102dd578063968afb95146102f25780639977c78a14610310578063c8a9c27614610373578063cabc4c4c1461039c578063cd286240146103c5575b3073ffffffffffffffffffffffffffffffffffffffff166331cbf5e334336000604051602001526040518363ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808381526020018273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200192505050602060405180830381600087803b151561017057600080fd5b6102c65a03f1151561018157600080fd5b5050506040518051905050005b341561019957600080fd5b6101a16103ee565b6040518082815260200191505060405180910390f35b6101bf6103f4565b6040518082815260200191505060405180910390f35b61020a600480803590602001909190803573ffffffffffffffffffffffffffffffffffffffff1690602001909190505061048f565b6040518082815260200191505060405180910390f35b341561022b57600080fd5b610233610567565b6040518082815260200191505060405180910390f35b610251610594565b6040518082815260200191505060405180910390f35b341561027257600080fd5b61029e600480803573ffffffffffffffffffffffffffffffffffffffff16906020019091905050610684565b6040518082815260200191505060405180910390f35b34156102bf57600080fd5b6102c761069c565b6040518082815260200191505060405180910390f35b34156102e857600080fd5b6102f06106c9565b005b6102fa61075e565b6040518082815260200191505060405180910390f35b341561031b57600080fd5b6103316004808035906020019091905050610867565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b341561037e57600080fd5b6103866108a6565b6040518082815260200191505060405180910390f35b34156103a757600080fd5b6103af6108ac565b6040518082815260200191505060405180910390f35b34156103d057600080fd5b6103d86108b2565b6040518082815260200191505060405180910390f35b60025481565b6000600660009054906101000a900460ff16158061041f5750600660019054906101000a900460ff16155b151561042a57600080fd5b610433336108b8565b34600860003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000206000828254019250508190555061048a34336109a7565b905090565b6000600660009054906101000a900460ff1615806104ba5750600660019054906101000a900460ff16155b15156104c557600080fd5b3073ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff161415156104ff57600080fd5b610508826108b8565b82600860008473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000206000828254019250508190555061055f34336109a7565b905092915050565b6000600660019054906101000a900460ff16156105875760009050610591565b6005546003540390505b90565b600080600660009054906101000a900460ff1615156105b257600080fd5b346105bb610567565b111515156105c857600080fd5b6105d1336108b8565b34600860003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060008282540192505081905550610626610567565b340390506000811115610674573373ffffffffffffffffffffffffffffffffffffffff166108fc829081150290604051600060405180830381858888f19350505050151561067357600080fd5b5b61067e34336109a7565b91505090565b60086020528060005260406000206000915090505481565b6000600660009054906101000a900460ff16156106bc57600090506106c6565b6004546002540390505b90565b6000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff1614151561072457600080fd5b6000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16ff5b600080600660009054906101000a900460ff16158061078a5750600660019054906101000a900460ff16155b151561079557600080fd5b3461079e61069c565b111515156107ab57600080fd5b6107b4336108b8565b34600860003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000206000828254019250508190555061080961069c565b340390506000811115610857573373ffffffffffffffffffffffffffffffffffffffff166108fc829081150290604051600060405180830381858888f19350505050151561085657600080fd5b5b61086134336109a7565b91505090565b60078181548110151561087657fe5b90600052602060002090016000915054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b60035481565b60045481565b60055481565b60008060009150600090505b600780549050811015610955578273ffffffffffffffffffffffffffffffffffffffff166007828154811015156108f757fe5b906000526020600020900160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1614156109485760019150610955565b80806001019150506108c4565b8115156109a2576000600860008573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020819055505b505050565b600080600080600080600088955060009450600097506000861180156109da5750600660009054906101000a900460ff16155b15610af5576109e761069c565b93508386116109f657856109f8565b835b925082860395508260045401600481905550600254600454101515610af4576001600660006101000a81548160ff021916908315150217905550600254850194507fecb1bb9e764f768147cfe45a716eb20c5a308b474b6641129aaba11b70ffab2260016040518080602001828103825283818154600181600116156101000203166002900481526020019150805460018160011615610100020316600290048015610ae55780601f10610aba57610100808354040283529160200191610ae5565b820191906000526020600020905b815481529060010190602001808311610ac857829003601f168201915b50509250505060405180910390a15b5b600086118015610b125750600660019054906101000a900460ff16155b15610c2c57610b1f610567565b9150818611610b2e5785610b30565b815b905080860395508060055401600581905550600354600554101515610c2b576001600660016101000a81548160ff021916908315150217905550600354850194507ec62fbd03ec782d2e04d410cfd381487c9a75c374d9349530a98b02b4d80c4f60016040518080602001828103825283818154600181600116156101000203166002900481526020019150805460018160011615610100020316600290048015610c1c5780601f10610bf157610100808354040283529160200191610c1c565b820191906000526020600020905b815481529060010190602001808311610bff57829003601f168201915b50509250505060405180910390a15b5b6000861115610c76573373ffffffffffffffffffffffffffffffffffffffff166108fc879081150290604051600060405180830381858888f193505050501515610c7557600080fd5b5b6000851115610ce1576000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff166108fc869081150290604051600060405180830381858888f193505050501515610ce057600080fd5b5b8589039650505050505050929150505600a165627a7a723058202af9901f20baef9cfb0740e25d4eb78d69b3ed864126b6fbb68cadb2c720ff810029";

    protected static final HashMap<String, String> _addresses;

    static {
        _addresses = new HashMap<>();
    }

    protected PublicTrade(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected PublicTrade(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
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

    public RemoteCall<TransactionReceipt> pay(BigInteger amount, String sender, BigInteger weiValue) {
        Function function = new Function(
                "pay", 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(amount), 
                new org.web3j.abi.datatypes.Address(sender)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
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

    public RemoteCall<BigInteger> amounts(String param0) {
        Function function = new Function("amounts", 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
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

    public RemoteCall<String> senders(BigInteger param0) {
        Function function = new Function("senders", 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
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

    public static RemoteCall<PublicTrade> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, BigInteger initialWeiValue, BigInteger aA, BigInteger rA, String _id) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(aA), 
                new org.web3j.abi.datatypes.generated.Uint256(rA), 
                new org.web3j.abi.datatypes.Utf8String(_id)));
        return deployRemoteCall(PublicTrade.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor, initialWeiValue);
    }

    public static RemoteCall<PublicTrade> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, BigInteger initialWeiValue, BigInteger aA, BigInteger rA, String _id) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(aA), 
                new org.web3j.abi.datatypes.generated.Uint256(rA), 
                new org.web3j.abi.datatypes.Utf8String(_id)));
        return deployRemoteCall(PublicTrade.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor, initialWeiValue);
    }

    public static PublicTrade load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new PublicTrade(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    public static PublicTrade load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new PublicTrade(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
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
