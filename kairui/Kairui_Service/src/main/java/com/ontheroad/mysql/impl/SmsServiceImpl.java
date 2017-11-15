package com.ontheroad.mysql.impl;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.ontheroad.pojo.Constant.BaseConstant;
import com.ontheroad.service.SmsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
@Transactional
public class SmsServiceImpl implements SmsService {

    private static final String product = "Dysmsapi";
    private static final String domain = "dysmsapi.aliyuncs.com";

    private static final String accessKeyId = "LTAIRxDkBiHoUeau";
    private static final String accessKeySecret = "6ZtJx8Uf7xNfqPLzZiYlcA77oWP3vr";

    @Override
    public Map<Object, Object> sendVerificationCode(String phone, String code) {
        Map<Object, Object> map = new HashMap<Object, Object>();
        try {
            IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
            DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
            IAcsClient acsClient = new DefaultAcsClient(profile);

            SendSmsRequest request = new SendSmsRequest();
            request.setPhoneNumbers(phone);
            request.setSignName("易寻厂房网");
            request.setTemplateCode("SMS_85495017");
            request.setTemplateParam("{\"code\":\"" + code + "\"}");

            SendSmsResponse sendSmsResponse=null;
			try {
				sendSmsResponse = acsClient.getAcsResponse(request);
			} catch (Exception e) {
			
			}

            Map<Object, Object> introspected = new org.apache.commons.beanutils.BeanMap(sendSmsResponse);
            Map<Object, Object> rMap = new HashMap<>();
            for(Object key: introspected.keySet()) {
                rMap.put(key + "", introspected.get(key));
            }
            map.put("code", BaseConstant.appUserSuccessStatus);
            map.put("msg", "获取成功");
            map.put("extra",null);
            map.put("resultMap", rMap);
            return map;
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", BaseConstant.appUserErrorStatus);
            map.put("msg", "服务器异常");
            map.put("extra",null);
            map.put("resultMap", null);
            return map;
        }
    }

}
