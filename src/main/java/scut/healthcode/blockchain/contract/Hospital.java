package scut.healthcode.blockchain.contract;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.fisco.bcos.sdk.abi.FunctionReturnDecoder;
import org.fisco.bcos.sdk.abi.TypeReference;
import org.fisco.bcos.sdk.abi.datatypes.Function;
import org.fisco.bcos.sdk.abi.datatypes.Type;
import org.fisco.bcos.sdk.abi.datatypes.Utf8String;
import org.fisco.bcos.sdk.abi.datatypes.generated.Int256;
import org.fisco.bcos.sdk.abi.datatypes.generated.tuples.generated.Tuple1;
import org.fisco.bcos.sdk.abi.datatypes.generated.tuples.generated.Tuple2;
import org.fisco.bcos.sdk.abi.datatypes.generated.tuples.generated.Tuple4;
import org.fisco.bcos.sdk.client.Client;
import org.fisco.bcos.sdk.contract.Contract;
import org.fisco.bcos.sdk.crypto.CryptoSuite;
import org.fisco.bcos.sdk.crypto.keypair.CryptoKeyPair;
import org.fisco.bcos.sdk.model.CryptoType;
import org.fisco.bcos.sdk.model.TransactionReceipt;
import org.fisco.bcos.sdk.model.callback.TransactionCallback;
import org.fisco.bcos.sdk.transaction.model.exception.ContractException;

@SuppressWarnings("unchecked")
public class Hospital extends Contract {
    public static final String[] BINARY_ARRAY = {"608060405234801561001057600080fd5b5061002861002d640100000000026401000000009004565b610185565b600061100190508073ffffffffffffffffffffffffffffffffffffffff166356004b6a6040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808060200180602001806020018481038452600a8152602001807f745f686f73706974616c00000000000000000000000000000000000000000000815250602001848103835260028152602001807f6964000000000000000000000000000000000000000000000000000000000000815250602001848103825260108152602001807f6e616d652c726573756c742c64617465000000000000000000000000000000008152506020019350505050602060405180830381600087803b15801561014657600080fd5b505af115801561015a573d6000803e3d6000fd5b505050506040513d602081101561017057600080fd5b81019080805190602001909291905050505050565b610e2d806101946000396000f30060806040526004361061004c576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff16806318b71bbc14610051578063fcd7e3c114610164575b600080fd5b34801561005d57600080fd5b5061014e600480360381019080803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509192919290803590602001908201803590602001908080601f016020809104026020016040519081016040528093929190818152602001838380828437820191505050505050919291929080359060200190929190803590602001908201803590602001908080601f01602080910402602001604051908101604052809392919081815260200183838082843782019150505050505091929192905050506101e8565b6040518082815260200191505060405180910390f35b34801561017057600080fd5b506101cb600480360381019080803590602001908201803590602001908080601f01602080910402602001604051908101604052809392919081815260200183838082843782019150505050505091929192905050506108a8565b604051808381526020018281526020019250505060405180910390f35b600080600080600080600080955060009450600093506102078b6108a8565b809550819650505060008514151561087357610221610d12565b92508273ffffffffffffffffffffffffffffffffffffffff166313db93466040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b15801561028757600080fd5b505af115801561029b573d6000803e3d6000fd5b505050506040513d60208110156102b157600080fd5b810190808051906020019092919050505091508173ffffffffffffffffffffffffffffffffffffffff1663e942b5168c6040518263ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808060200180602001838103835260028152602001807f6964000000000000000000000000000000000000000000000000000000000000815250602001838103825284818151815260200191508051906020019080838360005b83811015610384578082015181840152602081019050610369565b50505050905090810190601f1680156103b15780820380516001836020036101000a031916815260200191505b509350505050600060405180830381600087803b1580156103d157600080fd5b505af11580156103e5573d6000803e3d6000fd5b505050508173ffffffffffffffffffffffffffffffffffffffff1663e942b5168b6040518263ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808060200180602001838103835260048152602001807f6e616d6500000000000000000000000000000000000000000000000000000000815250602001838103825284818151815260200191508051906020019080838360005b838110156104a957808201518184015260208101905061048e565b50505050905090810190601f1680156104d65780820380516001836020036101000a031916815260200191505b509350505050600060405180830381600087803b1580156104f657600080fd5b505af115801561050a573d6000803e3d6000fd5b505050508173ffffffffffffffffffffffffffffffffffffffff16632ef8ba748a6040518263ffffffff167c01000000000000000000000000000000000000000000000000000000000281526004018080602001838152602001828103825260068152602001807f726573756c74000000000000000000000000000000000000000000000000000081525060200192505050600060405180830381600087803b1580156105b657600080fd5b505af11580156105ca573d6000803e3d6000fd5b505050508173ffffffffffffffffffffffffffffffffffffffff1663e942b516896040518263ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808060200180602001838103835260048152602001807f6461746500000000000000000000000000000000000000000000000000000000815250602001838103825284818151815260200191508051906020019080838360005b8381101561068e578082015181840152602081019050610673565b50505050905090810190601f1680156106bb5780820380516001836020036101000a031916815260200191505b509350505050600060405180830381600087803b1580156106db57600080fd5b505af11580156106ef573d6000803e3d6000fd5b505050508273ffffffffffffffffffffffffffffffffffffffff166331afac368c846040518363ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180806020018373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001828103825284818151815260200191508051906020019080838360005b838110156107ae578082015181840152602081019050610793565b50505050905090810190601f1680156107db5780820380516001836020036101000a031916815260200191505b509350505050602060405180830381600087803b1580156107fb57600080fd5b505af115801561080f573d6000803e3d6000fd5b505050506040513d602081101561082557600080fd5b81019080805190602001909291905050509050600181141561084a576000955061086e565b7ffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffe95505b610897565b7fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff95505b859650505050505050949350505050565b6000806000806000806108b9610d12565b93508373ffffffffffffffffffffffffffffffffffffffff1663e8434e39888673ffffffffffffffffffffffffffffffffffffffff16637857d7c96040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b15801561093c57600080fd5b505af1158015610950573d6000803e3d6000fd5b505050506040513d602081101561096657600080fd5b81019080805190602001909291905050506040518363ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180806020018373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001828103825284818151815260200191508051906020019080838360005b83811015610a145780820151818401526020810190506109f9565b50505050905090810190601f168015610a415780820380516001836020036101000a031916815260200191505b509350505050602060405180830381600087803b158015610a6157600080fd5b505af1158015610a75573d6000803e3d6000fd5b505050506040513d6020811015610a8b57600080fd5b81019080805190602001909291905050509250600091508273ffffffffffffffffffffffffffffffffffffffff1663949d225d6040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b158015610b0657600080fd5b505af1158015610b1a573d6000803e3d6000fd5b505050506040513d6020811015610b3057600080fd5b810190808051906020019092919050505060001415610b77577fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff8281915095509550610d09565b8273ffffffffffffffffffffffffffffffffffffffff1663846719e060006040518263ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180828152602001915050602060405180830381600087803b158015610be757600080fd5b505af1158015610bfb573d6000803e3d6000fd5b505050506040513d6020811015610c1157600080fd5b8101908080519060200190929190505050905060008173ffffffffffffffffffffffffffffffffffffffff1663fda69fae6040518163ffffffff167c01000000000000000000000000000000000000000000000000000000000281526004018080602001828103825260068152602001807f726573756c740000000000000000000000000000000000000000000000000000815250602001915050602060405180830381600087803b158015610cc657600080fd5b505af1158015610cda573d6000803e3d6000fd5b505050506040513d6020811015610cf057600080fd5b8101908080519060200190929190505050819150955095505b50505050915091565b600080600061100191508173ffffffffffffffffffffffffffffffffffffffff1663f23f63c96040518163ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180806020018281038252600a8152602001807f745f686f73706974616c00000000000000000000000000000000000000000000815250602001915050602060405180830381600087803b158015610dbc57600080fd5b505af1158015610dd0573d6000803e3d6000fd5b505050506040513d6020811015610de657600080fd5b810190808051906020019092919050505090508092505050905600a165627a7a723058201d618c0cf3429f7dd11f6c89b66dfb10ebcaf49789ac1c6ca470e4e790feb4880029"};

    public static final String BINARY = org.fisco.bcos.sdk.utils.StringUtils.joinAll("", BINARY_ARRAY);

    public static final String[] SM_BINARY_ARRAY = {"608060405234801561001057600080fd5b5061002861002d640100000000026401000000009004565b610185565b600061100190508073ffffffffffffffffffffffffffffffffffffffff1663c92a78016040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808060200180602001806020018481038452600a8152602001807f745f686f73706974616c00000000000000000000000000000000000000000000815250602001848103835260028152602001807f6964000000000000000000000000000000000000000000000000000000000000815250602001848103825260108152602001807f6e616d652c726573756c742c64617465000000000000000000000000000000008152506020019350505050602060405180830381600087803b15801561014657600080fd5b505af115801561015a573d6000803e3d6000fd5b505050506040513d602081101561017057600080fd5b81019080805190602001909291905050505050565b610e2d806101946000396000f30060806040526004361061004c576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff1680635b325d7814610051578063703c1d79146100d5575b600080fd5b34801561005d57600080fd5b506100b8600480360381019080803590602001908201803590602001908080601f01602080910402602001604051908101604052809392919081815260200183838082843782019150505050505091929192905050506101e8565b604051808381526020018281526020019250505060405180910390f35b3480156100e157600080fd5b506101d2600480360381019080803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509192919290803590602001908201803590602001908080601f016020809104026020016040519081016040528093929190818152602001838380828437820191505050505050919291929080359060200190929190803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509192919290505050610652565b6040518082815260200191505060405180910390f35b6000806000806000806101f9610d12565b93508373ffffffffffffffffffffffffffffffffffffffff1663d8ac5957888673ffffffffffffffffffffffffffffffffffffffff1663c74f8caf6040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b15801561027c57600080fd5b505af1158015610290573d6000803e3d6000fd5b505050506040513d60208110156102a657600080fd5b81019080805190602001909291905050506040518363ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180806020018373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001828103825284818151815260200191508051906020019080838360005b83811015610354578082015181840152602081019050610339565b50505050905090810190601f1680156103815780820380516001836020036101000a031916815260200191505b509350505050602060405180830381600087803b1580156103a157600080fd5b505af11580156103b5573d6000803e3d6000fd5b505050506040513d60208110156103cb57600080fd5b81019080805190602001909291905050509250600091508273ffffffffffffffffffffffffffffffffffffffff1663d3e9af5a6040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b15801561044657600080fd5b505af115801561045a573d6000803e3d6000fd5b505050506040513d602081101561047057600080fd5b8101908080519060200190929190505050600014156104b7577fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff8281915095509550610649565b8273ffffffffffffffffffffffffffffffffffffffff16633dd2b61460006040518263ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180828152602001915050602060405180830381600087803b15801561052757600080fd5b505af115801561053b573d6000803e3d6000fd5b505050506040513d602081101561055157600080fd5b8101908080519060200190929190505050905060008173ffffffffffffffffffffffffffffffffffffffff16634900862e6040518163ffffffff167c01000000000000000000000000000000000000000000000000000000000281526004018080602001828103825260068152602001807f726573756c740000000000000000000000000000000000000000000000000000815250602001915050602060405180830381600087803b15801561060657600080fd5b505af115801561061a573d6000803e3d6000fd5b505050506040513d602081101561063057600080fd5b8101908080519060200190929190505050819150955095505b50505050915091565b600080600080600080600080955060009450600093506106718b6101e8565b8095508196505050600085141515610cdd5761068b610d12565b92508273ffffffffffffffffffffffffffffffffffffffff16635887ab246040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b1580156106f157600080fd5b505af1158015610705573d6000803e3d6000fd5b505050506040513d602081101561071b57600080fd5b810190808051906020019092919050505091508173ffffffffffffffffffffffffffffffffffffffff16631a391cb48c6040518263ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808060200180602001838103835260028152602001807f6964000000000000000000000000000000000000000000000000000000000000815250602001838103825284818151815260200191508051906020019080838360005b838110156107ee5780820151818401526020810190506107d3565b50505050905090810190601f16801561081b5780820380516001836020036101000a031916815260200191505b509350505050600060405180830381600087803b15801561083b57600080fd5b505af115801561084f573d6000803e3d6000fd5b505050508173ffffffffffffffffffffffffffffffffffffffff16631a391cb48b6040518263ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808060200180602001838103835260048152602001807f6e616d6500000000000000000000000000000000000000000000000000000000815250602001838103825284818151815260200191508051906020019080838360005b838110156109135780820151818401526020810190506108f8565b50505050905090810190601f1680156109405780820380516001836020036101000a031916815260200191505b509350505050600060405180830381600087803b15801561096057600080fd5b505af1158015610974573d6000803e3d6000fd5b505050508173ffffffffffffffffffffffffffffffffffffffff1663def426988a6040518263ffffffff167c01000000000000000000000000000000000000000000000000000000000281526004018080602001838152602001828103825260068152602001807f726573756c74000000000000000000000000000000000000000000000000000081525060200192505050600060405180830381600087803b158015610a2057600080fd5b505af1158015610a34573d6000803e3d6000fd5b505050508173ffffffffffffffffffffffffffffffffffffffff16631a391cb4896040518263ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808060200180602001838103835260048152602001807f6461746500000000000000000000000000000000000000000000000000000000815250602001838103825284818151815260200191508051906020019080838360005b83811015610af8578082015181840152602081019050610add565b50505050905090810190601f168015610b255780820380516001836020036101000a031916815260200191505b509350505050600060405180830381600087803b158015610b4557600080fd5b505af1158015610b59573d6000803e3d6000fd5b505050508273ffffffffffffffffffffffffffffffffffffffff16634c6f30c08c846040518363ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180806020018373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001828103825284818151815260200191508051906020019080838360005b83811015610c18578082015181840152602081019050610bfd565b50505050905090810190601f168015610c455780820380516001836020036101000a031916815260200191505b509350505050602060405180830381600087803b158015610c6557600080fd5b505af1158015610c79573d6000803e3d6000fd5b505050506040513d6020811015610c8f57600080fd5b810190808051906020019092919050505090506001811415610cb45760009550610cd8565b7ffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffe95505b610d01565b7fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff95505b859650505050505050949350505050565b600080600061100191508173ffffffffffffffffffffffffffffffffffffffff166359a48b656040518163ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180806020018281038252600a8152602001807f745f686f73706974616c00000000000000000000000000000000000000000000815250602001915050602060405180830381600087803b158015610dbc57600080fd5b505af1158015610dd0573d6000803e3d6000fd5b505050506040513d6020811015610de657600080fd5b810190808051906020019092919050505090508092505050905600a165627a7a72305820019cca75c4f58c49f6ef5ceb8b0c7789cbcb0b23b389b2722663c649be3b02e70029"};

    public static final String SM_BINARY = org.fisco.bcos.sdk.utils.StringUtils.joinAll("", SM_BINARY_ARRAY);

    public static final String[] ABI_ARRAY = {"[{\"constant\":false,\"inputs\":[{\"name\":\"id\",\"type\":\"string\"},{\"name\":\"name\",\"type\":\"string\"},{\"name\":\"result\",\"type\":\"int256\"},{\"name\":\"date\",\"type\":\"string\"}],\"name\":\"upload\",\"outputs\":[{\"name\":\"\",\"type\":\"int256\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"id\",\"type\":\"string\"}],\"name\":\"select\",\"outputs\":[{\"name\":\"\",\"type\":\"int256\"},{\"name\":\"\",\"type\":\"int256\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"inputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"constructor\"}]"};

    public static final String ABI = org.fisco.bcos.sdk.utils.StringUtils.joinAll("", ABI_ARRAY);

    public static final String FUNC_UPLOAD = "upload";

    public static final String FUNC_SELECT = "select";

    protected Hospital(String contractAddress, Client client, CryptoKeyPair credential) {
        super(getBinary(client.getCryptoSuite()), contractAddress, client, credential);
    }

    public static String getBinary(CryptoSuite cryptoSuite) {
        return (cryptoSuite.getCryptoTypeConfig() == CryptoType.ECDSA_TYPE ? BINARY : SM_BINARY);
    }

    public TransactionReceipt upload(String id, String name, BigInteger result, String date) {
        final Function function = new Function(
                FUNC_UPLOAD, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.abi.datatypes.Utf8String(id), 
                new org.fisco.bcos.sdk.abi.datatypes.Utf8String(name), 
                new org.fisco.bcos.sdk.abi.datatypes.generated.Int256(result), 
                new org.fisco.bcos.sdk.abi.datatypes.Utf8String(date)), 
                Collections.<TypeReference<?>>emptyList());
        return executeTransaction(function);
    }

    public void upload(String id, String name, BigInteger result, String date, TransactionCallback callback) {
        final Function function = new Function(
                FUNC_UPLOAD, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.abi.datatypes.Utf8String(id), 
                new org.fisco.bcos.sdk.abi.datatypes.Utf8String(name), 
                new org.fisco.bcos.sdk.abi.datatypes.generated.Int256(result), 
                new org.fisco.bcos.sdk.abi.datatypes.Utf8String(date)), 
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String getSignedTransactionForUpload(String id, String name, BigInteger result, String date) {
        final Function function = new Function(
                FUNC_UPLOAD, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.abi.datatypes.Utf8String(id), 
                new org.fisco.bcos.sdk.abi.datatypes.Utf8String(name), 
                new org.fisco.bcos.sdk.abi.datatypes.generated.Int256(result), 
                new org.fisco.bcos.sdk.abi.datatypes.Utf8String(date)), 
                Collections.<TypeReference<?>>emptyList());
        return createSignedTransaction(function);
    }

    public Tuple4<String, String, BigInteger, String> getUploadInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_UPLOAD, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Int256>() {}, new TypeReference<Utf8String>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple4<String, String, BigInteger, String>(

                (String) results.get(0).getValue(), 
                (String) results.get(1).getValue(), 
                (BigInteger) results.get(2).getValue(), 
                (String) results.get(3).getValue()
                );
    }

    public Tuple1<BigInteger> getUploadOutput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getOutput();
        final Function function = new Function(FUNC_UPLOAD, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Int256>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple1<BigInteger>(

                (BigInteger) results.get(0).getValue()
                );
    }

    public Tuple2<BigInteger, BigInteger> select(String id) throws ContractException {
        final Function function = new Function(FUNC_SELECT, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.abi.datatypes.Utf8String(id)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Int256>() {}, new TypeReference<Int256>() {}));
        List<Type> results = executeCallWithMultipleValueReturn(function);
        return new Tuple2<BigInteger, BigInteger>(
                (BigInteger) results.get(0).getValue(), 
                (BigInteger) results.get(1).getValue());
    }

    public static Hospital load(String contractAddress, Client client, CryptoKeyPair credential) {
        return new Hospital(contractAddress, client, credential);
    }

    public static Hospital deploy(Client client, CryptoKeyPair credential) throws ContractException {
        return deploy(Hospital.class, client, credential, getBinary(client.getCryptoSuite()), "");
    }
}