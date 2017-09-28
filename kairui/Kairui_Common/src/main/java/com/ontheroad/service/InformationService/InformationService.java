package com.ontheroad.service.InformationService;

import org.springframework.remoting.service.annotation.RemoteService;

import java.util.Map;


/**
 * 消息相关
 * @author 胡俊
 *
 */
@RemoteService
public interface InformationService {

	Map<Object, Object> getInformationTypeList(Integer user_id);

	Map<Object, Object> getNewsInformationList();

	Map<Object, Object> getDeviceInformationList();
	/**
	 * 
	* 
	* @Description: 获取用户消息
	* @param user_id
	* @param informationType
	* @return  
	* Map<Object,Object>   
	* @throws
	 */
	Map<Object, Object> getInformationList(Integer user_id, Integer informationType);
	/**
	 * 
	* 
	* @Description: 清空消息
	* @param user_id
	* @return  
	* Map<Object,Object>   
	* @throws
	 */
	Map<Object, Object> clearInformations(Integer user_id,Integer information_id,Integer informationType);
	
	/**
	 * 
	* 
	* @Description: 用户每种消息条数
	* @param user_id
	* @return  
	* @return Map<Object,Object>   
	* @throws 
	* @author 胡俊
	* @email 510830970@qq.com
	 */
	Map<Object, Object> allInformationsNum(Integer user_id);
	
	/**
	 * 
	* 
	* @Description: 设置某种类型消息，单条为已读
	* @param user_id
	* @return  
	* @return Map<Object,Object>   
	* @throws 
	* @author 胡俊
	* @email 510830970@qq.com
	 */
	Map<Object, Object> setInformationIsRead(Integer user_id,Integer informationId);

}
