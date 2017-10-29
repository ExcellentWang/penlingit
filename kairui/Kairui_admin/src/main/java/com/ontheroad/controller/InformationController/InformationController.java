package com.ontheroad.controller.InformationController;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ontheroad.core.annotation.Token;
import com.ontheroad.pojo.Constant.BaseConstant;
import com.ontheroad.service.InformationService.InformationService;

@RestController
@RequestMapping("/information")
public class InformationController {

	@Autowired
	private InformationService informationService;
	
	
	/*
	 * 用户消息类型分类列表，以及最新消息
	 * 
	 */
	@RequestMapping(value = "/getInformationTypeList")
    public Map<Object,Object> getInformationTypeList(Integer user_id) {
		//返回前端map
	    Map<Object,Object> map = new HashMap<Object,Object>(); 
        try {	        	
        	return informationService.getInformationTypeList(user_id);      	        	        	
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", BaseConstant.appUserErrorStatus);
    		map.put("msg", "服务器异常");
    		map.put("extra",null);
    		map.put("resultMap", null);
            return map;
        }
    }

	/**
	 * 
	 * 
	 * @Description:根据用户和类型查询消息，用户可以为空
	 * @param user_id
	 * @param informationType
	 * @return
	 * @return Map<Object,Object>
	 * @throws 
	 * @author 胡俊
	 * @email 510830970@qq.com
	 */
    @RequestMapping(value = "/getInfoList")
    public Map<Object,Object> getInfoList(Integer user_id, Integer informationType) {
        //返回前端map
        Map<Object,Object> map = new HashMap<Object,Object>();
        try {
            return informationService.getInformationList(user_id, informationType);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", BaseConstant.appUserErrorStatus);
            map.put("msg", "服务器异常");
            map.put("extra",null);
            map.put("resultMap", null);
            return map;
        }
    }

	/*
	 *获取资讯消息列表 
	 * 
	 */
    @Token(needToken = false)
	@RequestMapping(value = "/getNewsList")
    public Map<Object,Object> getNewsList() {
		//返回前端map
	    Map<Object,Object> map = new HashMap<Object,Object>(); 
        try {	        	
        	return informationService.getNewsInformationList();
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", BaseConstant.appUserErrorStatus);
    		map.put("msg", "服务器异常");
    		map.put("extra",null);
    		map.put("resultMap", null);
            return map;
        }
    }
	
	
	/*
	 *获取设备通知 
	 * 
	 */
	@RequestMapping(value = "/getDeviceInformationList", method = RequestMethod.POST)
    public Map<Object,Object> getDeviceInformationList() {
		//返回前端map
	    Map<Object,Object> map = new HashMap<Object,Object>(); 
        try {	        	
        	return informationService.getDeviceInformationList();      	        	        	
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", BaseConstant.appUserErrorStatus);
    		map.put("msg", "服务器异常");
    		map.put("extra",null);
    		map.put("resultMap", null);
            return map;
        }
    }
	
	
	/**
	 * 
	* 
	* @Description: 清空消息
	* @param user_id
	* @return  
	* Map<Object,Object>   
	* @throws
	 */
	@RequestMapping(value = "/clearInformations")
    public Map<Object,Object> clearInformations(Integer user_id,Integer informationId,Integer informationType) {
		//返回前端map
	    Map<Object,Object> map = new HashMap<Object,Object>(); 
        try {	        	
        	return informationService.clearInformations( user_id,informationId,informationType);      	        	        	
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", BaseConstant.appUserErrorStatus);
    		map.put("msg", "服务器异常");
    		map.put("extra",null);
    		map.put("resultMap", null);
            return map;
        }
    }
	
	/**
	 * 
	* 
	* @Description:设置某种类型消息，单条为已读
	* @param user_id
	* @return  
	* @return Map<Object,Object>   
	* @throws 
	* @author 胡俊
	* @email 510830970@qq.com
	 */
	@RequestMapping(value = "/setInformationIsRead")
    public Map<Object,Object> setInformationIsRead(Integer user_id,Integer informationId) {
		//返回前端map
	    Map<Object,Object> map = new HashMap<Object,Object>(); 
        try {	        	
        	return informationService.setInformationIsRead( user_id,informationId);      	        	        	
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
