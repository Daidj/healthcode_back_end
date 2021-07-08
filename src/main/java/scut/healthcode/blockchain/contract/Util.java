package scut.healthcode.blockchain.contract;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.fisco.bcos.sdk.abi.FunctionReturnDecoder;
import org.fisco.bcos.sdk.abi.TypeReference;
import org.fisco.bcos.sdk.abi.datatypes.Address;
import org.fisco.bcos.sdk.abi.datatypes.Function;
import org.fisco.bcos.sdk.abi.datatypes.Type;
import org.fisco.bcos.sdk.abi.datatypes.Utf8String;
import org.fisco.bcos.sdk.abi.datatypes.generated.Int256;
import org.fisco.bcos.sdk.abi.datatypes.generated.tuples.generated.Tuple2;
import org.fisco.bcos.sdk.client.Client;
import org.fisco.bcos.sdk.contract.Contract;
import org.fisco.bcos.sdk.crypto.CryptoSuite;
import org.fisco.bcos.sdk.crypto.keypair.CryptoKeyPair;
import org.fisco.bcos.sdk.model.CryptoType;
import org.fisco.bcos.sdk.model.TransactionReceipt;
import org.fisco.bcos.sdk.model.callback.TransactionCallback;
import org.fisco.bcos.sdk.transaction.model.exception.ContractException;

@SuppressWarnings("unchecked")
public class Util extends Contract {
    public static final String[] BINARY_ARRAY = {"608060405234801561001057600080fd5b506106a1806100206000396000f300608060405260043610610041576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff16806381ade30714610046575b600080fd5b34801561005257600080fd5b506100f3600480360381019080803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509192919290803590602001908201803590602001908080601f016020809104026020016040519081016040528093929190818152602001838380828437820191505050505050919291929050505061013c565b604051808381526020018273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019250505060405180910390f35b60008060008060008061100193508373ffffffffffffffffffffffffffffffffffffffff1663f23f63c9896040518263ffffffff167c01000000000000000000000000000000000000000000000000000000000281526004018080602001828103825283818151815260200191508051906020019080838360005b838110156101d25780820151818401526020810190506101b7565b50505050905090810190601f1680156101ff5780820380516001836020036101000a031916815260200191505b5092505050602060405180830381600087803b15801561021e57600080fd5b505af1158015610232573d6000803e3d6000fd5b505050506040513d602081101561024857600080fd5b810190808051906020019092919050505092508273ffffffffffffffffffffffffffffffffffffffff1663e8434e39888573ffffffffffffffffffffffffffffffffffffffff16637857d7c96040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b1580156102dc57600080fd5b505af11580156102f0573d6000803e3d6000fd5b505050506040513d602081101561030657600080fd5b81019080805190602001909291905050506040518363ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180806020018373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001828103825284818151815260200191508051906020019080838360005b838110156103b4578082015181840152602081019050610399565b50505050905090810190601f1680156103e15780820380516001836020036101000a031916815260200191505b509350505050602060405180830381600087803b15801561040157600080fd5b505af1158015610415573d6000803e3d6000fd5b505050506040513d602081101561042b57600080fd5b81019080805190602001909291905050509150600090508173ffffffffffffffffffffffffffffffffffffffff1663949d225d6040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b1580156104a657600080fd5b505af11580156104ba573d6000803e3d6000fd5b505050506040513d60208110156104d057600080fd5b8101908080519060200190929190505050600014156105b5577fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff8373ffffffffffffffffffffffffffffffffffffffff166313db93466040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b15801561056e57600080fd5b505af1158015610582573d6000803e3d6000fd5b505050506040513d602081101561059857600080fd5b81019080805190602001909291905050508191509550955061066a565b60008273ffffffffffffffffffffffffffffffffffffffff1663846719e060006040518263ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180828152602001915050602060405180830381600087803b15801561062757600080fd5b505af115801561063b573d6000803e3d6000fd5b505050506040513d602081101561065157600080fd5b8101908080519060200190929190505050819150955095505b5050505092509290505600a165627a7a723058204595b696b09033aab10d0d4a20494eec21158f6b0e266c9cd77e8faaa17aed970029"};

    public static final String BINARY = org.fisco.bcos.sdk.utils.StringUtils.joinAll("", BINARY_ARRAY);

    public static final String[] SM_BINARY_ARRAY = {"608060405234801561001057600080fd5b506106a1806100206000396000f300608060405260043610610041576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff168063f2048c9814610046575b600080fd5b34801561005257600080fd5b506100f3600480360381019080803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509192919290803590602001908201803590602001908080601f016020809104026020016040519081016040528093929190818152602001838380828437820191505050505050919291929050505061013c565b604051808381526020018273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019250505060405180910390f35b60008060008060008061100193508373ffffffffffffffffffffffffffffffffffffffff166359a48b65896040518263ffffffff167c01000000000000000000000000000000000000000000000000000000000281526004018080602001828103825283818151815260200191508051906020019080838360005b838110156101d25780820151818401526020810190506101b7565b50505050905090810190601f1680156101ff5780820380516001836020036101000a031916815260200191505b5092505050602060405180830381600087803b15801561021e57600080fd5b505af1158015610232573d6000803e3d6000fd5b505050506040513d602081101561024857600080fd5b810190808051906020019092919050505092508273ffffffffffffffffffffffffffffffffffffffff1663d8ac5957888573ffffffffffffffffffffffffffffffffffffffff1663c74f8caf6040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b1580156102dc57600080fd5b505af11580156102f0573d6000803e3d6000fd5b505050506040513d602081101561030657600080fd5b81019080805190602001909291905050506040518363ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180806020018373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001828103825284818151815260200191508051906020019080838360005b838110156103b4578082015181840152602081019050610399565b50505050905090810190601f1680156103e15780820380516001836020036101000a031916815260200191505b509350505050602060405180830381600087803b15801561040157600080fd5b505af1158015610415573d6000803e3d6000fd5b505050506040513d602081101561042b57600080fd5b81019080805190602001909291905050509150600090508173ffffffffffffffffffffffffffffffffffffffff1663d3e9af5a6040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b1580156104a657600080fd5b505af11580156104ba573d6000803e3d6000fd5b505050506040513d60208110156104d057600080fd5b8101908080519060200190929190505050600014156105b5577fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff8373ffffffffffffffffffffffffffffffffffffffff16635887ab246040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b15801561056e57600080fd5b505af1158015610582573d6000803e3d6000fd5b505050506040513d602081101561059857600080fd5b81019080805190602001909291905050508191509550955061066a565b60008273ffffffffffffffffffffffffffffffffffffffff16633dd2b61460006040518263ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180828152602001915050602060405180830381600087803b15801561062757600080fd5b505af115801561063b573d6000803e3d6000fd5b505050506040513d602081101561065157600080fd5b8101908080519060200190929190505050819150955095505b5050505092509290505600a165627a7a72305820bda106acccad0af4345709cf7706920de78c4a65c90900cdb51d876dfcdfa2320029"};

    public static final String SM_BINARY = org.fisco.bcos.sdk.utils.StringUtils.joinAll("", SM_BINARY_ARRAY);

    public static final String[] ABI_ARRAY = {"[{\"constant\":false,\"inputs\":[{\"name\":\"table_name\",\"type\":\"string\"},{\"name\":\"primary_key\",\"type\":\"string\"}],\"name\":\"query\",\"outputs\":[{\"name\":\"\",\"type\":\"int256\"},{\"name\":\"\",\"type\":\"address\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"inputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"constructor\"}]"};

    public static final String ABI = org.fisco.bcos.sdk.utils.StringUtils.joinAll("", ABI_ARRAY);

    public static final String FUNC_QUERY = "query";

    protected Util(String contractAddress, Client client, CryptoKeyPair credential) {
        super(getBinary(client.getCryptoSuite()), contractAddress, client, credential);
    }

    public static String getBinary(CryptoSuite cryptoSuite) {
        return (cryptoSuite.getCryptoTypeConfig() == CryptoType.ECDSA_TYPE ? BINARY : SM_BINARY);
    }

    public TransactionReceipt query(String table_name, String primary_key) {
        final Function function = new Function(
                FUNC_QUERY, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.abi.datatypes.Utf8String(table_name), 
                new org.fisco.bcos.sdk.abi.datatypes.Utf8String(primary_key)), 
                Collections.<TypeReference<?>>emptyList());
        return executeTransaction(function);
    }

    public void query(String table_name, String primary_key, TransactionCallback callback) {
        final Function function = new Function(
                FUNC_QUERY, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.abi.datatypes.Utf8String(table_name), 
                new org.fisco.bcos.sdk.abi.datatypes.Utf8String(primary_key)), 
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String getSignedTransactionForQuery(String table_name, String primary_key) {
        final Function function = new Function(
                FUNC_QUERY, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.abi.datatypes.Utf8String(table_name), 
                new org.fisco.bcos.sdk.abi.datatypes.Utf8String(primary_key)), 
                Collections.<TypeReference<?>>emptyList());
        return createSignedTransaction(function);
    }

    public Tuple2<String, String> getQueryInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_QUERY, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple2<String, String>(

                (String) results.get(0).getValue(), 
                (String) results.get(1).getValue()
                );
    }

    public Tuple2<BigInteger, String> getQueryOutput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getOutput();
        final Function function = new Function(FUNC_QUERY, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Int256>() {}, new TypeReference<Address>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple2<BigInteger, String>(

                (BigInteger) results.get(0).getValue(), 
                (String) results.get(1).getValue()
                );
    }

    public static Util load(String contractAddress, Client client, CryptoKeyPair credential) {
        return new Util(contractAddress, client, credential);
    }

    public static Util deploy(Client client, CryptoKeyPair credential) throws ContractException {
        return deploy(Util.class, client, credential, getBinary(client.getCryptoSuite()), "");
    }
}
