package com.ontheroad.controller;


import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ontheroad.core.util.PushUtil;
import com.ontheroad.core.util.UploadUtil;
import com.ontheroad.entity.TbNewsinformation;
import com.ontheroad.service.TbNewsinformationService;
import com.ontheroad.utils.MapUtil;
import com.ontheroad.utils.WebUtil;

@Controller
public class TbNewsinformationController {
	@Autowired TbNewsinformationService tbNewsinformationService;
	@Autowired
	private PushUtil pushUtil;
	@ResponseBody
	@RequestMapping("/addTbNewsinformation")
	public Map<Object, Object> add(TbNewsinformation tbNewsinformation,@RequestParam("file") CommonsMultipartFile file,HttpServletRequest request) throws Exception{
		String str=UploadUtil.save(file, request);
		tbNewsinformation.setPicture(str);
		
        tbNewsinformationService.addOrUpdate(tbNewsinformation);
		return MapUtil.getSuccessJson();
	}
	
	@ResponseBody
	@RequestMapping("/selectTbNewsinformationExample")
	public  Map<Object, Object> select(TbNewsinformation tbNewsinformation){
		List<TbNewsinformation> ls=tbNewsinformationService.selectByExample(tbNewsinformation);
		return MapUtil.getSuccessJson(ls,ls.size());
	}
	
	@ResponseBody
	@RequestMapping("/delTbNewsinformation")
	public Map<Object, Object> del(Integer id){
		tbNewsinformationService.del(id);
		return MapUtil.getSuccessJson();
	}
	
	@ResponseBody
	@RequestMapping("/changeStatus")
	public Map<Object, Object> changeStatus(TbNewsinformation tbNewsinformation){
		tbNewsinformationService.addOrUpdate(tbNewsinformation);
		return MapUtil.getSuccessJson();
	}
	
	@ResponseBody
	@RequestMapping("/getTbNewsinformationId")
	public Map<Object, Object> selectTbNewsinformationId(Integer id){
		return MapUtil.getSuccessJson(tbNewsinformationService.getTbNewsinformationId(id));
	}
	/**
	 * 推送消息
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/sendToApp")
	public Map<Object, Object> sendToApp(Integer id){
		TbNewsinformation info=tbNewsinformationService.getTbNewsinformationId(id);
		info.setStatus(5);
		tbNewsinformationService.addOrUpdate(info);
		info.setContent(null);
		JSONObject json=new JSONObject();
		json.put("pdata", info);
		pushUtil.push(json);
		return MapUtil.getSuccessJson();
	}
    public static Map<String, Object> objectToMap(Object obj) throws Exception {    
        if(obj == null){    
            return null;    
        }   
  
        Map<String, Object> map = new HashMap<String, Object>();    
  
        Field[] declaredFields = obj.getClass().getDeclaredFields();    
        for (Field field : declaredFields) {    
            field.setAccessible(true);  
            map.put(field.getName(), field.get(obj));  
        }    
  
        return map;  
    } 
}
