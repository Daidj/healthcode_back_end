package scut.healthcode.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import scut.healthcode.entity.NucleicAcidInfo;
import scut.healthcode.service.HospitalService;
import scut.healthcode.service.TestService;
import scut.healthcode.service.implement.TestServiceImpl;




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
    public boolean upload(NucleicAcidInfo nucleicAcidInfo){
        logger.info("accept nucleic Acid Info");
        return hospitalService.upload(nucleicAcidInfo);
    }
}
