package com.kairui.test;

import com.google.gson.Gson;
import com.kairui.test.utils.PostClient;
import com.kairui.test.utils.URLCOMMON;
import org.apache.commons.lang3.StringUtils;
import org.junit.After;
import org.junit.Before;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ontheroad on 6/6/17.
 */
public abstract class InterfaceBaseTest {
    protected Map<String, String> paramMap = new HashMap<String, String>();
    protected SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private String secretKey = "7523753475789174823";

    /**
     * 封装公共参数
     */
    @Before
    public void beforeTest() {
        paramMap.clear();
        Date currentTime = new Date();
        String timestamp = format.format(currentTime);
        paramMap.put("ver", "1.0");
        String token = getToken();
        if (StringUtils.isNotBlank(token)) {
            paramMap.put("token", token);
        }
    }

    public abstract String getUrl();

    public abstract String getToken();

    /**
     * 发起请求测试接口
     */
    @After
    public void afterTest() {
        String url = getUrl();
        if (StringUtils.isBlank(url)) {
            url = URLCOMMON.TEST.URL;
        }
        String response = testApi(url, paramMap, secretKey);
        Map map = new Gson().fromJson(response, Map.class);
        Double code = (Double) map.get("code");
        assert code.intValue() == 0;
    }

    private String testApi(String url, Map<String, String> paramMap, String secretKey) {
        try {

            String data = PostClient.doPost(url, paramMap, null, 3000, 15000, null, secretKey);

            return data;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
