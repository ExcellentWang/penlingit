package com.ontheroad.system.web;


import com.ontheroad.core.util.JSONUtils;
import com.ontheroad.core.annotation.OpLog;
import com.ontheroad.core.web.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

@Controller
@Scope("session")
@RequestMapping(value = "/img")
@OpLog(logDesc="图片上传")
public class ImgController extends BaseController {
	Logger log = LoggerFactory.getLogger(ImgController.class);

	@RequestMapping(value = "init")
	public String upload(HttpServletRequest request){
		return "img/upload";
	}
	@RequestMapping(value = "initSelect")
	public String select(HttpServletRequest request){
		
		return "img/select";
	}
	@RequestMapping(value = "uploadDo")
	public void uploadDo(HttpServletRequest request,HttpServletResponse response){
		String url = System.getProperty("user.dir")+"/src/main/webapp/static/img";
		MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
		MultipartFile multipartFile = multiRequest.getFile("file");
		String fileName = multipartFile.getOriginalFilename();
		File targetFile = new File(url, fileName);
		try {
			multipartFile.transferTo(targetFile);
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String fileUrl = fileName;
		writeToPage(JSONUtils.toJson(fileUrl), response);
	}
}
