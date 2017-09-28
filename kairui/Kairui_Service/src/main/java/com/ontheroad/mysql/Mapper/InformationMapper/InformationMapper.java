package com.ontheroad.mysql.Mapper.InformationMapper;

import com.ontheroad.pojo.Information.*;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface InformationMapper {

	List<userInformation> getInformationTypeList();

	void updateInformationStatus(Information info);

	List<NewsInformation> getNewsInformationList();

	List<Deviceinformation> getDeviceInformationList();

	List<Information> getInformationList(Map<String, Object> condition);

	List<Slideshow> getSlides();

	NewsInformation getNews(Integer news_ids);
	/**
	 * 
	* 
	* @Description: 清空消息
	* @param user_id
	* @param information_id  
	* @return void   
	* @throws 
	* @author 胡俊
	* @email 510830970@qq.com
	 */
	void clearInformations(@Param(value = "user_id") Integer user_id,@Param(value = "information_id") Integer information_id,@Param(value = "informationType") Integer informationType);
	
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
	* @Description: 获取某种消息类型最新的消息
	* @param user_id
	* @return  
	* @return Map<Object,Object>   
	* @throws 
	* @author 胡俊
	* @email 510830970@qq.com
	 */
	userInformation getInformationIsNew(@Param(value = "user_id") Integer user_id,@Param(value = "informationType") Integer informationType);
	
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
	void setInformationIsRead(@Param(value = "user_id") Integer user_id,@Param(value = "information_id")  Integer informationId);
}
