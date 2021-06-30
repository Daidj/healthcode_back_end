package scut.healthcode.service.implement;
import org.springframework.stereotype.Service;
import scut.healthcode.service.TestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service("testService")
public class TestServiceImpl implements TestService {

    private static final Logger logger = LoggerFactory.getLogger(TestServiceImpl.class);

    @Override
    public String getStr(String input) {
        logger.info(input);
        return "wahaha";
    }
}
