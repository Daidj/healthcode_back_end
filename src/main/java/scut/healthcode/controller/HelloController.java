package scut.healthcode.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import scut.healthcode.service.TestService;

@RestController
@CrossOrigin
public class HelloController {

    @Autowired
    private TestService testService;

    @GetMapping("/get")
    public String sayHello(@RequestParam("testinput") String testinput){
        return testService.getStr(testinput);
    }
}
