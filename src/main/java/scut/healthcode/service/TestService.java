package scut.healthcode.service;


import org.springframework.stereotype.Service;

@Service
public interface TestService {

    /**
     * test
     * @param input The test input.
     * @return If success, return "wahaha".
     * @author
     */
    public String getStr(String input);
}
