package com.ontheroad.core.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.util.HashMap;
import java.util.Map;


public class SysInitConfig {
    protected static final Logger logger = LoggerFactory.getLogger(SysInitConfig.class);

    private static SysInitConfig instance = new SysInitConfig();

    private SysInitConfig() {
    }

    public static SysInitConfig getInstance() {
        return instance;
    }

    public void loadConfigXML() {
        // 获取配置文件
        Resource resource = new ClassPathResource("service_config.xml");
        try {
            //
            Config config = new Config(resource.getInputStream(), "//service_config/");
            fillPropMap(config);
            logger.debug("display the Globals Parameter {}", propMap);
            logger.debug("*************Getting  {}  is ok!*************",
                    resource.getFilename());
        } catch (Exception e) {
            logger.error(e.getMessage());
            logger.error("*************Getting {} is err!*************",
                    resource.getFilename());
        }
    }

    private void fillPropMap(Config config) {
        propMap.put(CfgProp.DABOO_REMOTESERVERURL, config.get("path/remoteServerUrl", ""));
        propMap.put(CfgProp.DABOO_DEFAULTPASSWORD, config.get("passwrod/defaultPassword", ""));
        propMap.put(CfgProp.DABOO_VERSION, config.get("version", ""));
        propMap.put(CfgProp.DABOO_HTTPCLIENTPOLICY_CONNECTIONTIMEOUT, config.get("webservice/httpClientPolicy/ConnectionTimeout", ""));
        propMap.put(CfgProp.DABOO_HTTPCLIENTPOLICY_RECEIVETIMEOUT, config.get("webservice/httpClientPolicy/ReceiveTimeout", ""));
        propMap.put(CfgProp.DABOO_HTTPCLIENTPOLICY_ALLOWCHUNKING, config.get("webservice/httpClientPolicy/AllowChunking", ""));
    }


    public String get(CfgProp prop) {
        return propMap.get(prop);
    }

    public static enum CfgProp {
        DABOO_REMOTESERVERURL,
        DABOO_DEFAULTPASSWORD,
        DABOO_VERSION,
        DABOO_HTTPCLIENTPOLICY_CONNECTIONTIMEOUT,
        DABOO_HTTPCLIENTPOLICY_RECEIVETIMEOUT,
        DABOO_HTTPCLIENTPOLICY_ALLOWCHUNKING
    }


    private Map<CfgProp, String> propMap = new HashMap<CfgProp, String>();
}