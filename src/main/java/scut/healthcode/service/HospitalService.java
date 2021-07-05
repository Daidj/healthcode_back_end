package scut.healthcode.service;

import org.springframework.stereotype.Service;
import scut.healthcode.entity.NucleicAcidInfo;



@Service
public interface HospitalService {

    /**
     * Upload Nucleic Acid Information to block chain
     *
     * @param nucleicAcidInfo The Nucleic Acid Information
     * @author Dejian
     * @return whether the information is inserted
     */

    boolean upload(NucleicAcidInfo nucleicAcidInfo);
}
