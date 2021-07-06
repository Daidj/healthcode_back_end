package scut.healthcode.service.implement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import scut.healthcode.blockchain.client.HealthcodeClient;
import scut.healthcode.entity.NucleicAcidInfo;
import scut.healthcode.service.HospitalService;
import scut.healthcode.util.RetMessageFactory;

import java.math.BigInteger;
import java.util.HashMap;

@Service("hospitalService")
public class HospitalServiceImpl implements HospitalService {

    private static final Logger logger = LoggerFactory.getLogger(TestServiceImpl.class);

    @Override
    public HashMap upload(NucleicAcidInfo nucleicAcidInfo) {
        try {
            HealthcodeClient healthcodeClient = HealthcodeClient.getHealthcodeClient();
            String output = healthcodeClient.hospitalUpload(nucleicAcidInfo);
            logger.info("hospitalUpload : " + output);
            if (output.matches("0x0000000000000000000000000000000000000000000000000000000000000000"))
                return RetMessageFactory.newReturnMessage(1);
            else
                return RetMessageFactory.newReturnMessage(2);
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
