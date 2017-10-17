package com.ontheroad.controller;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.ontheroad.core.util.UploadUtil;
import com.ontheroad.entity.TbNewsinformation;
import com.ontheroad.service.TbNewsinformationService;
import com.ontheroad.utils.MapUtil;
import com.ontheroad.utils.WebUtil;

@Controller
public class TbNewsinformationController {
	@Autowired TbNewsinformationService tbNewsinformationService;
	
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
}
