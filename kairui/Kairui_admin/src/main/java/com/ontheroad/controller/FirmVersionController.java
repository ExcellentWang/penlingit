package com.ontheroad.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.ontheroad.core.util.UploadUtil;
import com.ontheroad.entity.FirmVersion;
import com.ontheroad.service.FirmVersionService;
import com.ontheroad.utils.WebUtil;

@Controller
public class FirmVersionController {
	@Autowired FirmVersionService firmVersionService;
	
	@ResponseBody
	@RequestMapping("/addFirmVersion")
	public String add(FirmVersion firmVersion,@RequestParam("file") CommonsMultipartFile file,HttpServletRequest request) throws Exception{
        String path=UploadUtil.save(file, request);
        firmVersion.setFirmUrl(path);
        firmVersionService.add(firmVersion);
		return WebUtil.getSuccessJson();
	}
	
	@ResponseBody
	@RequestMapping("/selectFirmVersionExample")
	public String select(FirmVersion firmVersion){
		List<FirmVersion> ls=firmVersionService.selectByExample(firmVersion);
		return WebUtil.getSuccessJson("", ls, ls.size());
	}
	
	@ResponseBody
	@RequestMapping("/delFirmVersionExample")
	public String del(Long id){
		firmVersionService.del(id);
		return WebUtil.getSuccessJson();
	}
	
}
