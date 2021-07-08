package scut.healthcode.service.implement;

import org.springframework.stereotype.Service;
import scut.healthcode.entity.UserInfo;
import scut.healthcode.service.UserService;

import java.util.HashMap;

@Service("userService")
public class UserServiceImpl implements UserService {


    @Override
    public HashMap<String, Object> upload(UserInfo userInfo) {


        return null;
    }

    @Override
    public HashMap<String, Object> isHealth(String hashCode) {
        return null;
    }

    @Override
    public HashMap<String, Object> generateHealthcode(String ID) {
        return null;
    }
}
