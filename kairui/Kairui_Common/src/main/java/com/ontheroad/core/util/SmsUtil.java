/*package com.ontheroad.core.util;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;

*//**
 * 
  * @ClassName: SmsUtil
  * @Description: 短信工具类
 *//*
public class SmsUtil {
	private final static Log log = LogFactory.getLog(SmsUtil.class);

	*//**
	  * @Description: 阿里大鱼发送短信
	  * @param phone  电话  				示例：15871383472
	  * @param smsTemplateCode			示例：SMS_3685120
	  * @param smsParam					示例：{\"code\":\"1234\"}
	  * @param smsSign					示例：身份验证
	  * @return  true/false(成功/失败)
	 *//*
	public static boolean sendByAlidayu(String phone, String smsTemplateCode, String smsParam, String smsSign){
		boolean result = true;
		try {
			String appkey = getText("sms.aliyun.appkey");
			String secret = getText("sms.aliyun.secret");
			String addr = getText("sms.aliyun.addr");
			TaobaoClient client = new DefaultTaobaoClient(addr, appkey, secret);
			AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
			req.setSmsType("normal");
			req.setSmsFreeSignName(smsSign);
			//参数
			req.setSmsParamString(smsParam);
			req.setRecNum(phone);
			req.setSmsTemplateCode(smsTemplateCode);
			AlibabaAliqinFcSmsNumSendResponse rsp = client.execute(req, secret);
			
			if(rsp != null && StringUtils.isNotBlank(rsp.getBody())){
				//返回错误信息
				if(rsp.getBody().indexOf("error_response") != -1){
					log.error(rsp.getBody());
					result = false;
				}
			}else{
				result = true;
			}
			
			return result;
		} catch (Exception e) {
			 log.error("阿里大鱼发送短信异常",e);
			 throw new RuntimeException("阿里大鱼发送短信异常");
		}
	}
		
	*//**
	 * 
	  * @Description: 获取资源配置
	  * @param key
	  * @return
	  * @author 马正正
	  * @date 2015年11月9日
	 *//*
	private static String getText(String key){
		return ConfigProperty.getProperty(key);
	}
	
}
*/