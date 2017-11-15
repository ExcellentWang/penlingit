package com.ontheroad.core.util;

import com.danga.MemCached.MemCachedClient;
import com.ontheroad.system.entity.ApiMethodModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kedong on 2017/6/23 0023.
 */
public class ApiInit {
    private Logger log = LoggerFactory.getLogger(getClass());

    private static ApiInit apiInit;
    private MemCachedClient memCachedClient;

    public static ApiInit newInstance() {
        if (apiInit == null) {
            apiInit = new ApiInit();
        }
        return apiInit;
    }

    private ApiInit() {
        memCachedClient = (MemCachedClient) SpringUtils.getBean("memCachedClient");
    }

    /**
     * 将接口配置信息放入缓存中
     */
    public void init() {
        List<ApiMethodModel> list = getApiMethod();
        if (list != null && list.size() > 0) {
            for (ApiMethodModel apiMethodModel : list) {
                memCachedClient.set(apiMethodModel.getName(), apiMethodModel);
            }
        }
    }

    private List<ApiMethodModel> getApiMethod() {
        Connection conn = getConn();

        StringBuilder sql = new StringBuilder("SELECT name, bean_name, parmeters_name, method_name, need_token,is_https, ver ");
        sql.append(" FROM t_s_api_method");
        sql.append(" WHERE is_enabled = ?");
        PreparedStatement pstmt = null;
        ResultSet result = null;
        List<ApiMethodModel> list = null;
        try {
            pstmt = conn.prepareStatement(sql.toString());
            pstmt.setBoolean(1, true);
            result = pstmt.executeQuery();

            list = new ArrayList<ApiMethodModel>();
            while (result.next()) {
                ApiMethodModel apiMethodModel = new ApiMethodModel();

                String name = result.getString("name");
                String bean = result.getString("bean_name");
                String parmeters = result.getString("parmeters_name");
                String method = result.getString("method_name");
                Boolean needToken = result.getBoolean("need_token");
                Boolean is_https = result.getBoolean("is_https");
                String ver = result.getString("ver");

                apiMethodModel.setName(name);
                apiMethodModel.setBeanName(bean);
                apiMethodModel.setMethodName(method);
                apiMethodModel.setParmetersName(parmeters);
                apiMethodModel.setNeedToken(needToken);
                apiMethodModel.setVer(ver);
                apiMethodModel.setIsHttps(is_https);

                list.add(apiMethodModel);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            log.error(e.getMessage());
        } finally {
            try {
                if (result != null) {
                    result.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return list;
    }

    private Connection getConn() {

        String driver = ConfigProperty.getProperty("jdbc.driverClassName");
        String url = ConfigProperty.getProperty("jdbc.url");
        String username = ConfigProperty.getProperty("jdbc.username");
        String password = ConfigProperty.getProperty("jdbc.password");
        Connection conn = null;
        try {
            Class.forName(driver); //classLoader,加载对应驱动
            conn = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
        return conn;
    }

    private void loadProperties() {
        Resource resource = new ClassPathResource("config.properties");
        try {

            Config config = new Config(resource.getInputStream());

        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }


}
