package scut.healthcode.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import scut.healthcode.entity.NucleicAcidInfo;
import scut.healthcode.util.JSONHelper;
import scut.healthcode.service.HospitalService;
import scut.healthcode.service.implement.TestServiceImpl;

import java.util.HashMap;


@CrossOrigin
@RestController
@RequestMapping("/hospital")
public class HospitalController {
    @Autowired
    private HospitalService hospitalService;

    private static final Logger logger = LoggerFactory.getLogger(TestServiceImpl.class);

    /**
     * Hospital upload Nucleic Acid Information
     *
     * @param nucleicAcidInfo The Nucleic Acid Information
     * @author Dejian
     * @method POST
     * @url /hospial/upload
     * @return whether the information is inserted
     */

    @PostMapping("/upload")
    @ResponseBody
    public HashMap upload(NucleicAcidInfo nucleicAcidInfo){
        try {
            logger.info("accept nucleic Acid Info");
            
            return hospitalService.upload(nucleicAcidInfo);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
