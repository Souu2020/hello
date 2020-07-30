package com.definesys.springboot.mpaas;

import com.definesys.mpaas.common.adapter.IMpaasSSOAuthentication;
import com.definesys.mpaas.common.adapter.UserProfile;
import com.definesys.mpaas.common.exception.MpaasBusinessException;
import org.springframework.stereotype.Component;

import java.util.Map;
@Component
public class Verifytoken implements IMpaasSSOAuthentication {

    @Override
    public UserProfile ssoAuth(Map<String, String> header, Map<String, String> cookies) throws MpaasBusinessException {
        UserProfile user = new UserProfile();
        String token = header.get("token");
        if (!"definesys".equals(token)) {
            throw new MpaasBusinessException("auth failed");
        }
        return user;
    }
}
