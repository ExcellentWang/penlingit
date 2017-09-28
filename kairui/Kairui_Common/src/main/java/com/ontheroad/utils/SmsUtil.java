/*package com.ontheroad.utils;

import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.MessageSource;


public class SmsUtil {
    private final static Log log = LogFactory.getLog(SmsUtil.class);
    *//**
     * 配置文件
     *//*
    private static MessageSource messageSource;

    *//**
     * @param ms
     * @Description: 初始化方法
     *//*
    public static void init(MessageSource ms) {
        messageSource = ms;
    }

    public static boolean send(String phone, String content) {
        String smsParam = String.format("{code:'%s'}", content);
        return sendByTaobao(phone, smsParam);
    }

    *//**
     * @param phone           电话  				示例：15971474578
     * @param smsParam        示例：{"code":"1234"}
     * @return true/false(成功/失败)
     * @Description: 阿里大鱼发送短信
     *//*
    public static boolean sendByTaobao(String phone, String smsParam) {
        boolean result = true;
        try {
            boolean test = false;
            if (test) {
                return result;
            }
            String appkey = getText("alidayu_app_key");
            String secret = getText("alidayu_app_secret");
            String url = getText("alidayu_url");
            String template = getText("alidayu_templateCode");
            String SignName = getText("alidayu_SmsFreeSignName");
            TaobaoClient client = new DefaultTaobaoClient(url, appkey, secret);
            AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
            req.setSmsType("normal");
            req.setSmsFreeSignName(SignName);
            //参数
            req.setSmsParamString(smsParam);
            req.setRecNum(phone);
            req.setSmsTemplateCode(template);
            AlibabaAliqinFcSmsNumSendResponse rsp = client.execute(req, secret);

            if (rsp != null && StringUtils.isNotBlank(rsp.getBody())) {
                //返回错误信息
                if (rsp.getBody().indexOf("error_response") != -1) {
                    log.error(rsp.getBody());
                    result = false;
                }
            } else {
                result = true;
            }

            return result;
        } catch (Exception e) {
            e.printStackTrace();
            log.error("阿里大鱼发送短信异常", e);
            return false;
        }
    }


    *//**
     * @param key
     * @return
     * @Description: 获取资源配置
     *//*
    private static String getText(String key) {
        return messageSource.getMessage(key, null, null);
    }

}
*/