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
	* @Description: 用户每种消息条数
	 */
	Map<Object, Object> allInformationsNum(Integer user_id);

	/**
	* @Description: 获取某种消息类型最新的消息
	 */
	userInformation getInformationIsNew(@Param(value = "user_id") Integer user_id,@Param(value = "informationType") Integer informationType);
	
	List<userInformation> getInformationByType(@Param(value = "type") Integer type);
}
