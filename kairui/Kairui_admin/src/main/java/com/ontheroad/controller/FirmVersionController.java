package com.ontheroad.controller;

import java.io.File;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.ontheroad.entity.FirmVersion;
import com.ontheroad.service.FirmVersionService;
import com.ontheroad.utils.WebUtil;

@Controller
public class FirmVersionController {
	@Autowired FirmVersionService firmVersionService;
	
	@ResponseBody
	@RequestMapping("/addFirmVersion")
	public String add(FirmVersion firmVersion,@RequestParam("file") CommonsMultipartFile file) throws Exception{
        String path="d:/"+new Date().getTime()+file.getOriginalFilename();
        File newFile=new File(path);
        //通过CommonsMultipartFile的方法直接写文件（注意这个时候）
        file.transferTo(newFile);
        firmVersion.setFirmUrl(path);
        firmVersionService.add(firmVersion);
		return WebUtil.getSuccessJson();
	}
	
	@ResponseBody
	@RequestMapping("/selectFirmVersionExample")
	public String select(FirmVersion firmVersion){
		List<FirmVersion> ls=firmVersionService.selectByExample(firmVersion);
		return WebUtil.getSuccessJson(ls);
	}
}
