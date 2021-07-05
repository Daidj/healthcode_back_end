package scut.healthcode.blockchain.client;

import java.io.*;
import java.math.BigInteger;
import java.util.Properties;
import org.fisco.bcos.sdk.BcosSDK;
import org.fisco.bcos.sdk.crypto.keypair.CryptoKeyPair;
import org.fisco.bcos.sdk.model.TransactionReceipt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import scut.healthcode.blockchain.contract.Healthcode;
import scut.healthcode.blockchain.contract.Hospital;
import scut.healthcode.entity.NucleicAcidInfo;

public class HealthcodeClient {
    private static Logger logger = LoggerFactory.getLogger(HealthcodeClient.class);

    private BcosSDK bcosSDK;
    private org.fisco.bcos.sdk.client.Client client;
    private CryptoKeyPair cryptoKeyPair;


    public static final String HOSPITAL_ADDRESS = "HospitalAddress";
    public static final String HEALTHCODE_ADDRESS = "HealthcodeAddress";

    //单例模式
    private static HealthcodeClient healthcodeClient;
    private HealthcodeClient(){}
    public static HealthcodeClient getHealthcodeClient(){
        if (healthcodeClient == null) {
            healthcodeClient = new HealthcodeClient();
            try {
                healthcodeClient.initialize();
                healthcodeClient.deployAssetAndRecordAddr(null);
            }catch (Exception e){
                e.printStackTrace();
            }

        }
        return healthcodeClient;
    }

    private void initialize() throws Exception {
        @SuppressWarnings("resource")
        ApplicationContext context =
                new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        bcosSDK = context.getBean(BcosSDK.class);
        client = bcosSDK.getClient(1);
        cryptoKeyPair = client.getCryptoSuite().createKeyPair();
        client.getCryptoSuite().setCryptoKeyPair(cryptoKeyPair);
        logger.debug("create client for group1, account address is " + cryptoKeyPair.getAddress());
    }

    public void deployAssetAndRecordAddr(String contractName) {
        try {
            if (contractName == null){
                logger.info("contract name is null");
//                recordAddr(HOSPITAL_ADDRESS, Hospital.deploy(client, cryptoKeyPair).getContractAddress());
//                recordAddr(HEALTHCODE_ADDRESS, Healthcode.deploy(client, cryptoKeyPair).getContractAddress());
            } else {

                switch (contractName) {
                    case HOSPITAL_ADDRESS:
                        recordAddr(HOSPITAL_ADDRESS, Hospital.deploy(client, cryptoKeyPair).getContractAddress());
                        break;
                    case HEALTHCODE_ADDRESS:
                        recordAddr(HEALTHCODE_ADDRESS, Healthcode.deploy(client, cryptoKeyPair).getContractAddress());
                        break;
                    default: {
                        logger.info("no this contract");
                    }
                }
            }
        } catch (Exception e) {
             e.printStackTrace();
            System.out.println(" deploy " + contractName + "contract failed, error message is  " + e.getMessage());
        }
    }

    public void recordAddr(String contractName, String address) throws FileNotFoundException, IOException {
        Properties prop = new Properties();
        final Resource contractResource = new ClassPathResource("contract.properties");
        prop.load(contractResource.getInputStream());
        prop.setProperty(contractName, address);

        FileOutputStream fileOutputStream = new FileOutputStream(contractResource.getFile());
        prop.store(fileOutputStream, "contract address");
//        prop.store(new FileWriter(contractResource.getFile()), "contract address");

        // .properties 不能持久化存储, 使用.txt
//        File contract = new File("contract.txt");
//        FileWriter fw = new FileWriter(contract, false);
//        PrintWriter pw = new PrintWriter(fw);
//        pw.println(address);
//        pw.flush();
//        fw.flush();
//        pw.close();
//        fw.close();

    }

    public String loadAssetAddr(String contractName) throws Exception {
        // load Asset contact address from contract.properties
        Properties prop = new Properties();
        Resource contractResource = new ClassPathResource("contract.properties");
        prop.load(contractResource.getInputStream());

        String contractAddress = prop.getProperty(contractName);

//        if (contractAddress == null || contractAddress.trim().equals("")) {
//            File contract = new File("contract.txt");
//            if (contract.exists()) {
//                Reader reader = new InputStreamReader(new FileInputStream(contract));
//                String res = "";
//                char newchar = (char)reader.read();
//                while (newchar != 0){
//                    res += newchar;
//                    newchar = (char)reader.read();
//                }
//                contractAddress = res;
//            }
//        }
        logger.info(" load " + contractName + "contract at " + contractAddress);
        if (contractAddress == null || contractAddress.trim().equals("")) {
            deployAssetAndRecordAddr(contractName);
            contractResource = new ClassPathResource("contract.properties");
            prop.load(contractResource.getInputStream());

            contractAddress = prop.getProperty(contractName);
            logger.info(" load " + contractName + "contract address failed, deploy it at " + contractAddress);
        }

        return contractAddress;
    }

    public String generateHealthcode(String ID) {
        try {
            String contractAddress = loadAssetAddr(HEALTHCODE_ADDRESS);
            Healthcode healthcode = Healthcode.load(contractAddress, client, cryptoKeyPair);

            TransactionReceipt receipt = healthcode.generate(ID);
            String output = receipt.getOutput();
            return output;
        } catch (Exception e) {
            // TODO Auto-generated catch block
             e.printStackTrace();
        }
        return "generate failed.";
    }

    // 发起上传核酸信息的交易
    public String hospitalUpload(NucleicAcidInfo nucleicAcidInfo ){
        try {
            String contractAddress = loadAssetAddr(HOSPITAL_ADDRESS);
            Hospital hospital = Hospital.load(contractAddress, client, cryptoKeyPair);

            TransactionReceipt receipt = hospital.upload(nucleicAcidInfo.getID(), nucleicAcidInfo.getUserName(), new BigInteger(String.valueOf(nucleicAcidInfo.getResult())), nucleicAcidInfo.getTime());
            String output = receipt.getOutput();
            return output;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "hospitalUpload failed.";
    }

    public String isHealthSelect(String Id) {
        try {
            String contractAddress = loadAssetAddr(HEALTHCODE_ADDRESS);
            Healthcode healthcode = Healthcode.load(contractAddress, client, cryptoKeyPair);

//            Tuple2<BigInteger, BigInteger> result = healthcode.isHealth(Id);
//            return result.getValue1().toString() + ", " + result.getValue2().toString();
            TransactionReceipt receipt = healthcode.isHealth(Id);
            String output = receipt.getOutput();
            return output;

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "health select failed.";
    }



//    public void registerAssetAccount(String assetAccount, BigInteger amount) {
//        try {
//            String contractAddress = loadAssetAddr();
//
//            Asset asset = Asset.load(contractAddress, client, cryptoKeyPair);
//            TransactionReceipt receipt = asset.register(assetAccount, amount);
//            List<Asset.RegisterEventEventResponse> response = asset.getRegisterEventEvents(receipt);
//            if (!response.isEmpty()) {
//                if (response.get(0).ret.compareTo(new BigInteger("0")) == 0) {
//                    System.out.printf(
//                            " register asset account success => asset: %s, value: %s \n", assetAccount, amount);
//                } else {
//                    System.out.printf(
//                            " register asset account failed, ret code is %s \n", response.get(0).ret.toString());
//                }
//            } else {
//                System.out.println(" event log not found, maybe transaction not exec. ");
//            }
//        } catch (Exception e) {
//            // TODO Auto-generated catch block
//            // e.printStackTrace();
//
//            logger.error(" registerAssetAccount exception, error message is {}", e.getMessage());
//            System.out.printf(" register asset account failed, error message is %s\n", e.getMessage());
//        }
//    }
//
}