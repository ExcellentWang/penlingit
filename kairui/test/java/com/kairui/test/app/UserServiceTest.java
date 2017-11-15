package com.kairui.test.app;

import com.kairui.test.InterfaceBaseTest;
import com.kairui.test.utils.URLCOMMON;
import org.junit.Test;

/**
 * Created by kedong on 2017/6/29 0029.
 */
public class UserServiceTest extends InterfaceBaseTest {
    @Override
    public String getUrl() {
        return URLCOMMON.TEST.URL_HTTPS;
    }

    @Override
    public String getToken() {
        return "3cafc89f886b9cb8cceaddbfee8c340d";
    }

    @Test
    public void register() {
        paramMap.put("method", "user.register");
        paramMap.put("mobile", "13277918801");
        paramMap.put("password", "111111");
        paramMap.put("verification", "123456");
    }

    @Test
    public void login() {
        paramMap.put("method", "user.login");
        paramMap.put("mobile", "13277918809");
        paramMap.put("password", "111112");
        paramMap.put("systemVersion", "1.2");
        paramMap.put("appVersion", "1.0");
        paramMap.put("phoneModel", "h3-u10");
    }


    @Test
    public void resetPassword() {
        paramMap.put("method", "user.resetPassword");
        paramMap.put("password", "111112");
    }

    @Test
    public void forgotPassword() {
        paramMap.put("method", "user.forgotPassword");
        paramMap.put("password", "123456");
        paramMap.put("verification", "123456");
        paramMap.put("mobile", "13277918809");
    }

    @Test
    public void logout() {
        paramMap.put("method", "user.logout");
    }

    @Test
    public void getVerification() {
        paramMap.put("method", "user.verificode");
        paramMap.put("mobile", "13277918809");
    }
}
