package scut.healthcode.blockchain.client;

import java.io.*;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import com.google.gson.JsonObject;
import org.fisco.bcos.sdk.BcosSDK;
import org.fisco.bcos.sdk.abi.datatypes.generated.tuples.generated.Tuple1;
import org.fisco.bcos.sdk.abi.datatypes.generated.tuples.generated.Tuple2;
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
import scut.healthcode.blockchain.contract.User;
import scut.healthcode.entity.NucleicAcidInfo;
import scut.healthcode.entity.UserInfo;

public class HealthcodeClient {
    private static final Logger logger = LoggerFactory.getLogger(HealthcodeClient.class);

    private BcosSDK bcosSDK;
    private org.fisco.bcos.sdk.client.Client client;
    private CryptoKeyPair cryptoKeyPair;


    public static final String HOSPITAL_ADDRESS = "HospitalAddress";
    public static final String USER_ADDRESS = "UserAddress";
//    public static final String HEALTHCODE_ADDRESS = "HealthcodeAddress";

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

    /***
     * 初始化，仅生成healthcodeClient时调用一次
     * @throws Exception
     */
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

    /**
     * 部署合约，并持久化记录该合约地址
     * @param contractName 合约名
     */
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
//                    case HEALTHCODE_ADDRESS:
//                        recordAddr(HEALTHCODE_ADDRESS, Healthcode.deploy(client, cryptoKeyPair).getContractAddress());
//                        break;
                    case USER_ADDRESS:
                        recordAddr(USER_ADDRESS, User.deploy(client, cryptoKeyPair).getContractAddress());
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

    /**
     * 持久化存储合约信息在属性文件contract.properties中
     * @param contractName 合约名
     * @param address 合约地址
     * @throws FileNotFoundException
     * @throws IOException
     */
    public void recordAddr(String contractName, String address) throws FileNotFoundException, IOException {
        Properties prop = new Properties();
        final Resource contractResource = new ClassPathResource("contract.properties");
        prop.load(contractResource.getInputStream());
        prop.setProperty(contractName, address);

        FileOutputStream fileOutputStream = new FileOutputStream(contractResource.getFile());
        prop.store(fileOutputStream, "contract address");
    }

    /**
     * 从属性文件contract.properties中读取合约地址，不存在则新建
     * @param contractName 合约名
     * @return 合约地址
     * @throws Exception
     */
    public String loadAssetAddr(String contractName) throws Exception {
        // load Asset contact address from contract.properties
        Properties prop = new Properties();
        Resource contractResource = new ClassPathResource("contract.properties");
        prop.load(contractResource.getInputStream());

        String contractAddress = prop.getProperty(contractName);

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

    ///////////////////////////////////用户\商场模块//////////////////////////////////////////
    /**
     * 获取ID对应用户的健康状况，并返回健康哈希值
     * @param ID 身份证号
     * @return 健康哈希值
     */
    public String generateHealthcode(String ID) {
        try {
            String contractAddress = loadAssetAddr(USER_ADDRESS);
            User user = User.load(contractAddress, client, cryptoKeyPair);

            TransactionReceipt receipt = user.generate(ID);
            System.out.println("##########################################");
            System.out.println(receipt.getOutput());
            System.out.println(receipt.getOutput().getClass().getSimpleName());
            System.out.println("##########################################");
            return receipt.getOutput();
        } catch (Exception e) {
            // TODO Auto-generated catch block
             e.printStackTrace();
        }
        return "generate failed.";
    }

    /**
     * 根据健康哈希值，返回用户是否健康
     * @param hashcode 健康哈希值
     * @return 是否健康
     */
    public boolean isHealth(String hashcode) {
        try {
            String contractAddress = loadAssetAddr(USER_ADDRESS);
            User user = User.load(contractAddress, client, cryptoKeyPair);

            Tuple2<BigInteger, BigInteger> result = user.isHealthy(hashcode);
            int is_valid = result.getValue1().intValue();
            int is_health = result.getValue2().intValue();
            switch (is_valid) {
                case 0:
                    logger.info("Valid HealthCode.");
                    if (is_health == 0) {
                        return true;
                    } else if (is_health == -1) {
                        logger.info("The health state of User is Unknown.");
                    }
                    break;
                case 1:
                    logger.info("HealthCode doesn't exist.");
                    break;
                case 2:
                    logger.info("HealthCode out of date");
                    break;
                default:
                    logger.info("Unexpected valid code with " + is_valid);
            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            logger.info("health select failed.");
        }
        return false;
    }

    /**
     * 上传用户个人信息
     * @param userInfo 用户个人信息
     */
    public String userUpload(UserInfo userInfo) {
        try {
            String contractAddress = loadAssetAddr(USER_ADDRESS);
            User user = User.load(contractAddress, client, cryptoKeyPair);

            TransactionReceipt receipt = user.upload(userInfo.getID(), userInfo.getName(),
                    userInfo.getResidence());
            return receipt.getOutput();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "hospitalUpload failed.";
    }


    ///////////////////////////////////////医院模块//////////////////////////////////////////
    /**
     * 发起上传核酸信息的交易
     * @param nucleicAcidInfo 核酸检测信息
     * @return 状态码
     */
    public int hospitalUpload(NucleicAcidInfo nucleicAcidInfo) {
        try {
            // 重新部署一次合约，测试用，待删除
            deployAssetAndRecordAddr(HOSPITAL_ADDRESS);
            // FISCO BCOS 的now获取的并非unix时间戳，都不知道是什么东西, 智能合约无法正确处理
            // 在这里进行加工, FISCO BCOS也太垃圾了吧
            String contractAddress = loadAssetAddr(HOSPITAL_ADDRESS);
            Hospital hospital = Hospital.load(contractAddress, client, cryptoKeyPair);

            SimpleDateFormat format = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
//            String time="1970-01-06 11:45:55";
            Date date = format.parse(nucleicAcidInfo.getTime().trim());
//            System.out.print("********************Format To times:"+date.getTime()/1000);
            if (new Date().getTime() < date.getTime()) {
                System.out.println("当前时间戳: " + new Date().getTime());
                System.out.println("检测时间: " + date.getTime());
                return -1;
            }

            if (nucleicAcidInfo.getResult() < 0 || nucleicAcidInfo.getResult() > 1) {
                return -3;
            }

            TransactionReceipt receipt = hospital.upload(
                    nucleicAcidInfo.getId(),
                    nucleicAcidInfo.getUserName(),
                    new BigInteger(String.valueOf(nucleicAcidInfo.getResult())),
                    new BigInteger(String.valueOf(date.getTime()/1000)));

            Tuple1<BigInteger> result = hospital.getUploadOutput(receipt);
            return result.getValue1().intValue();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -100;
        
//        return "hospitalUpload failed.";
    }

}