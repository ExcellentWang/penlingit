package com.ontheroad.service.InformationService;

import org.springframework.remoting.service.annotation.RemoteService;

import com.ontheroad.mysql.entity.TbInformation;

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
	* @Description: 获取用户消息
	 */
	Map<Object, Object> getInformationList(Integer user_id, Integer informationType);
	/**
	* @Description: 清空消息
	 */
	Map<Object, Object> clearInformations(Integer user_id,Integer information_id,Integer informationType);
	
	/**
	* @Description: 设置某种类型消息，单条为已读
	 */
	Map<Object, Object> setInformationIsRead(Integer user_id,Integer informationId);
	/**
	 * 添加消息公用表
	 */
	void addOrUpdateTbInformation(TbInformation info);

}
