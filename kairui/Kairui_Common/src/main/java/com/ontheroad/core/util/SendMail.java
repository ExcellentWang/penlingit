package com.ontheroad.core.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import javax.mail.Session;
import java.util.HashMap;
import java.util.Properties;

    
public class SendMail {  
	
	private static final Logger logger = LoggerFactory.getLogger(SendMail.class);
	//发送报警通知
	public static void sendAlarmNotify(){
		
		 // 发送器  
		  JavaMailSenderImpl sender = new JavaMailSenderImpl();  
		  sender.setHost("smtp.ym.163.com");  
		 // sender.setPort(465); // 默认就是25  
		  sender.setUsername("baojing@daboowifi.com");   
		  sender.setPassword("a123456");  
		  // 配置文件对象     
		  Properties props = new Properties();  
		  props.put("mail.smtp.starttls.enable", "true"); 
		  props.put("mail.smtp.auth", "true"); // 是否进行验证  
		  Session session = Session.getInstance(props);  
		  sender.setSession(session); // 为发送器指定会话  
		  SimpleMailMessage mail = new SimpleMailMessage();  
		  String [] receives = ConfigProperty.getProperty("email_to").split(",");
		  mail.setTo(receives);
		  mail.setSubject("大博会员系统提现功能报警"); // 标题  
		  mail.setFrom("baojing@daboowifi.com"); // 来自  
		  // 邮件内容  
		  String content = VelocityUtil.generateSource("template/email.vm", new HashMap<String, Object>());
		  mail.setText(content);
		  sender.send(mail); // 发送  
		 logger.debug("邮件发送成功");
	}
  
 
}  