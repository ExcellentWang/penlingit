package com.ontheroad.controller;


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
import com.ontheroad.utils.WebUtil;

@Controller
public class TbNewsinformationController {
	@Autowired TbNewsinformationService tbNewsinformationService;
	
	@ResponseBody
	@RequestMapping("/addTbNewsinformation")
	public String add(TbNewsinformation tbNewsinformation,@RequestParam("file") CommonsMultipartFile file,HttpServletRequest request) throws Exception{
		String str=UploadUtil.save(file, request);
		tbNewsinformation.setAddress(str);
        tbNewsinformationService.addOrUpdate(tbNewsinformation);
		return WebUtil.getSuccessJson();
	}
	
	@ResponseBody
	@RequestMapping("/selectTbNewsinformationExample")
	public String select(TbNewsinformation tbNewsinformation){
		
		return null;
	}
	
	@ResponseBody
	@RequestMapping("/delTbNewsinformation")
	public String del(Integer id){
		tbNewsinformationService.del(id);
		return WebUtil.getSuccessJson();
	}
	
	@ResponseBody
	@RequestMapping("/changeStatus")
	public String changeStatus(TbNewsinformation tbNewsinformation){
		tbNewsinformationService.addOrUpdate(tbNewsinformation);
		return WebUtil.getSuccessJson();
	}
}
