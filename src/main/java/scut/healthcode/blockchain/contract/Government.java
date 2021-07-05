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
import org.fisco.bcos.sdk.abi.datatypes.generated.Uint256;
import org.fisco.bcos.sdk.abi.datatypes.generated.tuples.generated.Tuple1;
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
public class Government extends Contract {
    public static final String[] BINARY_ARRAY = {"608060405234801561001057600080fd5b5061002861002d640100000000026401000000009004565b610185565b600061100190508073ffffffffffffffffffffffffffffffffffffffff166356004b6a6040518163ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180806020018060200180602001848103845260088152602001807f745f726567696f6e0000000000000000000000000000000000000000000000008152506020018481038352600a8152602001807f726567696f6e4e616d65000000000000000000000000000000000000000000008152506020018481038252600b8152602001807f697344616e6765726f75730000000000000000000000000000000000000000008152506020019350505050602060405180830381600087803b15801561014657600080fd5b505af115801561015a573d6000803e3d6000fd5b505050506040513d602081101561017057600080fd5b81019080805190602001909291905050505050565b61101c806101946000396000f30060806040526004361061004c576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff1680635aaecbc914610051578063fcd7e3c1146100d8575b600080fd5b34801561005d57600080fd5b506100c2600480360381019080803590602001908201803590602001908080601f01602080910402602001604051908101604052809392919081815260200183838082843782019150505050505091929192908035906020019092919050505061015c565b6040518082815260200191505060405180910390f35b3480156100e457600080fd5b5061013f600480360381019080803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509192919290505050610a91565b604051808381526020018281526020019250505060405180910390f35b6000806000806000806000806000806000985060009750600096506101808c610a91565b8098508199505050600088141561067357610199610f01565b95508573ffffffffffffffffffffffffffffffffffffffff166313db93466040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b1580156101ff57600080fd5b505af1158015610213573d6000803e3d6000fd5b505050506040513d602081101561022957600080fd5b810190808051906020019092919050505094508473ffffffffffffffffffffffffffffffffffffffff1663e942b5168d6040518263ffffffff167c01000000000000000000000000000000000000000000000000000000000281526004018080602001806020018381038352600a8152602001807f726567696f6e4e616d6500000000000000000000000000000000000000000000815250602001838103825284818151815260200191508051906020019080838360005b838110156102fc5780820151818401526020810190506102e1565b50505050905090810190601f1680156103295780820380516001836020036101000a031916815260200191505b509350505050600060405180830381600087803b15801561034957600080fd5b505af115801561035d573d6000803e3d6000fd5b505050508473ffffffffffffffffffffffffffffffffffffffff16638a42ebe98c6040518263ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180806020018381526020018281038252600b8152602001807f697344616e6765726f757300000000000000000000000000000000000000000081525060200192505050600060405180830381600087803b15801561040957600080fd5b505af115801561041d573d6000803e3d6000fd5b505050508573ffffffffffffffffffffffffffffffffffffffff1663bf2b70a18d878973ffffffffffffffffffffffffffffffffffffffff16637857d7c96040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b1580156104a357600080fd5b505af11580156104b7573d6000803e3d6000fd5b505050506040513d60208110156104cd57600080fd5b81019080805190602001909291905050506040518463ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180806020018473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001828103825285818151815260200191508051906020019080838360005b838110156105ad578082015181840152602081019050610592565b50505050905090810190601f1680156105da5780820380516001836020036101000a031916815260200191505b50945050505050602060405180830381600087803b1580156105fb57600080fd5b505af115801561060f573d6000803e3d6000fd5b505050506040513d602081101561062557600080fd5b81019080805190602001909291905050509350600184141561064a576000985061066e565b7fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff98505b610a7f565b61067b610f01565b92508573ffffffffffffffffffffffffffffffffffffffff166313db93466040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b1580156106e157600080fd5b505af11580156106f5573d6000803e3d6000fd5b505050506040513d602081101561070b57600080fd5b810190808051906020019092919050505091508473ffffffffffffffffffffffffffffffffffffffff1663e942b5168d6040518263ffffffff167c01000000000000000000000000000000000000000000000000000000000281526004018080602001806020018381038352600a8152602001807f726567696f6e4e616d6500000000000000000000000000000000000000000000815250602001838103825284818151815260200191508051906020019080838360005b838110156107de5780820151818401526020810190506107c3565b50505050905090810190601f16801561080b5780820380516001836020036101000a031916815260200191505b509350505050600060405180830381600087803b15801561082b57600080fd5b505af115801561083f573d6000803e3d6000fd5b505050508473ffffffffffffffffffffffffffffffffffffffff16638a42ebe98c6040518263ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180806020018381526020018281038252600b8152602001807f697344616e6765726f757300000000000000000000000000000000000000000081525060200192505050600060405180830381600087803b1580156108eb57600080fd5b505af11580156108ff573d6000803e3d6000fd5b505050508573ffffffffffffffffffffffffffffffffffffffff166331afac368d876040518363ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180806020018373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001828103825284818151815260200191508051906020019080838360005b838110156109be5780820151818401526020810190506109a3565b50505050905090810190601f1680156109eb5780820380516001836020036101000a031916815260200191505b509350505050602060405180830381600087803b158015610a0b57600080fd5b505af1158015610a1f573d6000803e3d6000fd5b505050506040513d6020811015610a3557600080fd5b810190808051906020019092919050505090506001811415610a5a5760009850610a7e565b7fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff98505b5b88995050505050505050505092915050565b6000806000806000806000610aa4610f01565b94508473ffffffffffffffffffffffffffffffffffffffff1663e8434e39898773ffffffffffffffffffffffffffffffffffffffff16637857d7c96040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b158015610b2757600080fd5b505af1158015610b3b573d6000803e3d6000fd5b505050506040513d6020811015610b5157600080fd5b81019080805190602001909291905050506040518363ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180806020018373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001828103825284818151815260200191508051906020019080838360005b83811015610bff578082015181840152602081019050610be4565b50505050905090810190601f168015610c2c5780820380516001836020036101000a031916815260200191505b509350505050602060405180830381600087803b158015610c4c57600080fd5b505af1158015610c60573d6000803e3d6000fd5b505050506040513d6020811015610c7657600080fd5b81019080805190602001909291905050509350600092508373ffffffffffffffffffffffffffffffffffffffff1663949d225d6040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b158015610cf157600080fd5b505af1158015610d05573d6000803e3d6000fd5b505050506040513d6020811015610d1b57600080fd5b810190808051906020019092919050505060001415610d62577fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff8381915096509650610ef7565b8373ffffffffffffffffffffffffffffffffffffffff1663846719e060006040518263ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180828152602001915050602060405180830381600087803b158015610dd257600080fd5b505af1158015610de6573d6000803e3d6000fd5b505050506040513d6020811015610dfc57600080fd5b810190808051906020019092919050505091508173ffffffffffffffffffffffffffffffffffffffff1663fda69fae6040518163ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180806020018281038252600b815260200180","7f697344616e6765726f7573000000000000000000000000000000000000000000815250602001915050602060405180830381600087803b158015610eaf57600080fd5b505af1158015610ec3573d6000803e3d6000fd5b505050506040513d6020811015610ed957600080fd5b81019080805190602001909291905050509050600081819150965096505b5050505050915091565b600080600061100191508173ffffffffffffffffffffffffffffffffffffffff1663f23f63c96040518163ffffffff167c01000000000000000000000000000000000000000000000000000000000281526004018080602001828103825260088152602001807f745f726567696f6e000000000000000000000000000000000000000000000000815250602001915050602060405180830381600087803b158015610fab57600080fd5b505af1158015610fbf573d6000803e3d6000fd5b505050506040513d6020811015610fd557600080fd5b810190808051906020019092919050505090508092505050905600a165627a7a72305820fcae114dd2d8f1f2b466ae612ba1f1cefc88c9ee3f8bd58d3da26c72676f2c6d0029"};

    public static final String BINARY = org.fisco.bcos.sdk.utils.StringUtils.joinAll("", BINARY_ARRAY);

    public static final String[] SM_BINARY_ARRAY = {"608060405234801561001057600080fd5b5061002861002d640100000000026401000000009004565b610185565b600061100190508073ffffffffffffffffffffffffffffffffffffffff1663c92a78016040518163ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180806020018060200180602001848103845260088152602001807f745f726567696f6e0000000000000000000000000000000000000000000000008152506020018481038352600a8152602001807f726567696f6e4e616d65000000000000000000000000000000000000000000008152506020018481038252600b8152602001807f697344616e6765726f75730000000000000000000000000000000000000000008152506020019350505050602060405180830381600087803b15801561014657600080fd5b505af115801561015a573d6000803e3d6000fd5b505050506040513d602081101561017057600080fd5b81019080805190602001909291905050505050565b61101c806101946000396000f30060806040526004361061004c576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff1680635b325d78146100515780638ab86fbc146100d5575b600080fd5b34801561005d57600080fd5b506100b8600480360381019080803590602001908201803590602001908080601f016020809104026020016040519081016040528093929190818152602001838380828437820191505050505050919291929050505061015c565b604051808381526020018281526020019250505060405180910390f35b3480156100e157600080fd5b50610146600480360381019080803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509192919290803590602001909291905050506105cc565b6040518082815260200191505060405180910390f35b600080600080600080600061016f610f01565b94508473ffffffffffffffffffffffffffffffffffffffff1663d8ac5957898773ffffffffffffffffffffffffffffffffffffffff1663c74f8caf6040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b1580156101f257600080fd5b505af1158015610206573d6000803e3d6000fd5b505050506040513d602081101561021c57600080fd5b81019080805190602001909291905050506040518363ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180806020018373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001828103825284818151815260200191508051906020019080838360005b838110156102ca5780820151818401526020810190506102af565b50505050905090810190601f1680156102f75780820380516001836020036101000a031916815260200191505b509350505050602060405180830381600087803b15801561031757600080fd5b505af115801561032b573d6000803e3d6000fd5b505050506040513d602081101561034157600080fd5b81019080805190602001909291905050509350600092508373ffffffffffffffffffffffffffffffffffffffff1663d3e9af5a6040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b1580156103bc57600080fd5b505af11580156103d0573d6000803e3d6000fd5b505050506040513d60208110156103e657600080fd5b81019080805190602001909291905050506000141561042d577fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff83819150965096506105c2565b8373ffffffffffffffffffffffffffffffffffffffff16633dd2b61460006040518263ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180828152602001915050602060405180830381600087803b15801561049d57600080fd5b505af11580156104b1573d6000803e3d6000fd5b505050506040513d60208110156104c757600080fd5b810190808051906020019092919050505091508173ffffffffffffffffffffffffffffffffffffffff16634900862e6040518163ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180806020018281038252600b8152602001807f697344616e6765726f7573000000000000000000000000000000000000000000815250602001915050602060405180830381600087803b15801561057a57600080fd5b505af115801561058e573d6000803e3d6000fd5b505050506040513d60208110156105a457600080fd5b81019080805190602001909291905050509050600081819150965096505b5050505050915091565b6000806000806000806000806000806000985060009750600096506105f08c61015c565b80985081995050506000881415610ae357610609610f01565b95508573ffffffffffffffffffffffffffffffffffffffff16635887ab246040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b15801561066f57600080fd5b505af1158015610683573d6000803e3d6000fd5b505050506040513d602081101561069957600080fd5b810190808051906020019092919050505094508473ffffffffffffffffffffffffffffffffffffffff16631a391cb48d6040518263ffffffff167c01000000000000000000000000000000000000000000000000000000000281526004018080602001806020018381038352600a8152602001807f726567696f6e4e616d6500000000000000000000000000000000000000000000815250602001838103825284818151815260200191508051906020019080838360005b8381101561076c578082015181840152602081019050610751565b50505050905090810190601f1680156107995780820380516001836020036101000a031916815260200191505b509350505050600060405180830381600087803b1580156107b957600080fd5b505af11580156107cd573d6000803e3d6000fd5b505050508473ffffffffffffffffffffffffffffffffffffffff1663f2f4ee6d8c6040518263ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180806020018381526020018281038252600b8152602001807f697344616e6765726f757300000000000000000000000000000000000000000081525060200192505050600060405180830381600087803b15801561087957600080fd5b505af115801561088d573d6000803e3d6000fd5b505050508573ffffffffffffffffffffffffffffffffffffffff1663664b37d68d878973ffffffffffffffffffffffffffffffffffffffff1663c74f8caf6040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b15801561091357600080fd5b505af1158015610927573d6000803e3d6000fd5b505050506040513d602081101561093d57600080fd5b81019080805190602001909291905050506040518463ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180806020018473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001828103825285818151815260200191508051906020019080838360005b83811015610a1d578082015181840152602081019050610a02565b50505050905090810190601f168015610a4a5780820380516001836020036101000a031916815260200191505b50945050505050602060405180830381600087803b158015610a6b57600080fd5b505af1158015610a7f573d6000803e3d6000fd5b505050506040513d6020811015610a9557600080fd5b810190808051906020019092919050505093506001841415610aba5760009850610ade565b7fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff98505b610eef565b610aeb610f01565b92508573ffffffffffffffffffffffffffffffffffffffff16635887ab246040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b158015610b5157600080fd5b505af1158015610b65573d6000803e3d6000fd5b505050506040513d6020811015610b7b57600080fd5b810190808051906020019092919050505091508473ffffffffffffffffffffffffffffffffffffffff16631a391cb48d6040518263ffffffff167c01000000000000000000000000000000000000000000000000000000000281526004018080602001806020018381038352600a8152602001807f726567696f6e4e616d6500000000000000000000000000000000000000000000815250602001838103825284818151815260200191508051906020019080838360005b83811015610c4e578082015181840152602081019050610c33565b50505050905090810190601f168015610c7b5780820380516001836020036101000a031916815260200191505b509350505050600060405180830381600087803b158015610c9b57600080fd5b505af1158015610caf573d6000803e3d6000fd5b505050508473ffffffffffffffffffffffffffffffffffffffff1663f2f4ee6d8c6040518263ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180806020018381526020018281038252600b8152602001807f697344616e6765726f757300000000000000000000000000000000000000000081525060200192505050600060405180830381600087803b158015610d5b57600080fd5b505af1158015610d6f573d6000803e3d6000fd5b505050508573ffffffffffffffffffffffffffffffffffffffff16634c6f30c08d876040518363ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180806020018373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001828103825284818151815260200191508051906020019080838360005b83811015610e2e578082015181840152602081019050610e13565b50505050905090810190601f168015610e5b5780820380516001836020036101000a031916815260200191505b50935050505060206040518083038160","0087803b158015610e7b57600080fd5b505af1158015610e8f573d6000803e3d6000fd5b505050506040513d6020811015610ea557600080fd5b810190808051906020019092919050505090506001811415610eca5760009850610eee565b7fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff98505b5b88995050505050505050505092915050565b600080600061100191508173ffffffffffffffffffffffffffffffffffffffff166359a48b656040518163ffffffff167c01000000000000000000000000000000000000000000000000000000000281526004018080602001828103825260088152602001807f745f726567696f6e000000000000000000000000000000000000000000000000815250602001915050602060405180830381600087803b158015610fab57600080fd5b505af1158015610fbf573d6000803e3d6000fd5b505050506040513d6020811015610fd557600080fd5b810190808051906020019092919050505090508092505050905600a165627a7a723058203bcf12a8ce25c997c433e0fb8623e3af23c0fe086bdf03e2cda66fe9a6ff37b50029"};

    public static final String SM_BINARY = org.fisco.bcos.sdk.utils.StringUtils.joinAll("", SM_BINARY_ARRAY);

    public static final String[] ABI_ARRAY = {"[{\"constant\":false,\"inputs\":[{\"name\":\"regionName\",\"type\":\"string\"},{\"name\":\"isDangerous\",\"type\":\"uint256\"}],\"name\":\"upload_region_info\",\"outputs\":[{\"name\":\"\",\"type\":\"int256\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"regionName\",\"type\":\"string\"}],\"name\":\"select\",\"outputs\":[{\"name\":\"\",\"type\":\"int256\"},{\"name\":\"\",\"type\":\"uint256\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"inputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"constructor\"}]"};

    public static final String ABI = org.fisco.bcos.sdk.utils.StringUtils.joinAll("", ABI_ARRAY);

    public static final String FUNC_UPLOAD_REGION_INFO = "upload_region_info";

    public static final String FUNC_SELECT = "select";

    protected Government(String contractAddress, Client client, CryptoKeyPair credential) {
        super(getBinary(client.getCryptoSuite()), contractAddress, client, credential);
    }

    public static String getBinary(CryptoSuite cryptoSuite) {
        return (cryptoSuite.getCryptoTypeConfig() == CryptoType.ECDSA_TYPE ? BINARY : SM_BINARY);
    }

    public TransactionReceipt upload_region_info(String regionName, BigInteger isDangerous) {
        final Function function = new Function(
                FUNC_UPLOAD_REGION_INFO, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.abi.datatypes.Utf8String(regionName), 
                new org.fisco.bcos.sdk.abi.datatypes.generated.Uint256(isDangerous)), 
                Collections.<TypeReference<?>>emptyList());
        return executeTransaction(function);
    }

    public void upload_region_info(String regionName, BigInteger isDangerous, TransactionCallback callback) {
        final Function function = new Function(
                FUNC_UPLOAD_REGION_INFO, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.abi.datatypes.Utf8String(regionName), 
                new org.fisco.bcos.sdk.abi.datatypes.generated.Uint256(isDangerous)), 
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String getSignedTransactionForUpload_region_info(String regionName, BigInteger isDangerous) {
        final Function function = new Function(
                FUNC_UPLOAD_REGION_INFO, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.abi.datatypes.Utf8String(regionName), 
                new org.fisco.bcos.sdk.abi.datatypes.generated.Uint256(isDangerous)), 
                Collections.<TypeReference<?>>emptyList());
        return createSignedTransaction(function);
    }

    public Tuple2<String, BigInteger> getUpload_region_infoInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_UPLOAD_REGION_INFO, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Uint256>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple2<String, BigInteger>(

                (String) results.get(0).getValue(), 
                (BigInteger) results.get(1).getValue()
                );
    }

    public Tuple1<BigInteger> getUpload_region_infoOutput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getOutput();
        final Function function = new Function(FUNC_UPLOAD_REGION_INFO, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Int256>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple1<BigInteger>(

                (BigInteger) results.get(0).getValue()
                );
    }

    public Tuple2<BigInteger, BigInteger> select(String regionName) throws ContractException {
        final Function function = new Function(FUNC_SELECT, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.abi.datatypes.Utf8String(regionName)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Int256>() {}, new TypeReference<Uint256>() {}));
        List<Type> results = executeCallWithMultipleValueReturn(function);
        return new Tuple2<BigInteger, BigInteger>(
                (BigInteger) results.get(0).getValue(), 
                (BigInteger) results.get(1).getValue());
    }

    public static Government load(String contractAddress, Client client, CryptoKeyPair credential) {
        return new Government(contractAddress, client, credential);
    }

    public static Government deploy(Client client, CryptoKeyPair credential) throws ContractException {
        return deploy(Government.class, client, credential, getBinary(client.getCryptoSuite()), "");
    }
}