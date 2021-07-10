package scut.healthcode.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;
import scut.healthcode.entity.UserInfo;
import scut.healthcode.service.UserService;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @PostMapping("/upload")
    public HashMap<String, Object> upload(@RequestBody UserInfo userInfo) {
        try {
            logger.info("accept UserInfo");
            logger.info(userInfo.toString());

            return userService.upload(userInfo);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @PostMapping("/generateHealthcode")
    public HashMap<String, Object> generate(@RequestBody Map params) {
        return userService.generateHealthcode(params.get("id").toString());
    }

    @PostMapping("/checkIsHealthy")
    public HashMap<String, Object> isHealth(@RequestBody String healthCode) {
        return userService.isHealth(healthCode);
    }
}
