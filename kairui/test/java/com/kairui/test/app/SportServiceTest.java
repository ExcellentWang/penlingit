package com.kairui.test.app;

import com.kairui.test.InterfaceBaseTest;
import org.junit.Test;

import static com.kairui.test.utils.URLCOMMON.LOCAL;

/**
 * 运动模块接口测试用例
 */
public class SportServiceTest extends InterfaceBaseTest {

    @Override
    public String getUrl() {
        return LOCAL.URL_HTTPS;
    }

    @Override
    public String getToken() {
        return null;
    }

    @Test
    public void uploadSport() {
        // TODO:  2017/6/28 0028
    }

    @Test
    public void getSportList() {
        // TODO:  2017/6/28 0028
    }

}