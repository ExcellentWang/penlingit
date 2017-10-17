package com.ontheroad.controller;

import java.io.File;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.ontheroad.entity.Lunbo;
import com.ontheroad.service.LunboService;
import com.ontheroad.utils.MapUtil;
/**
 * 轮播管理
 * @author Administrator
 *
 */
@Controller
public class LunboController {
	@Autowired
	private LunboService lunboService;
	
	@RequestMapping("/addLunbo")
	@ResponseBody
	public  Map<Object, Object> add(Lunbo lunbo,@RequestParam("file") CommonsMultipartFile file,HttpServletRequest request){
		//获取本地文件地址
        String path = request.getSession().getServletContext().getRealPath("upload");  
        String fileName = file.getOriginalFilename();  
        System.out.println(path);  
        File targetFile = new File(path, fileName);  
        if(!targetFile.exists()){  
            targetFile.mkdirs();  
        }  
        //保存  
        try {  
            file.transferTo(targetFile);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        String sImg= "http://"+getRemoteHost(request)+":9023"+"/view/upload/"+fileName;
        lunbo.setsImg(sImg);
		lunboService.addOrUpdate(lunbo);
		return MapUtil.getSuccessJson();
	}
	
	public String getRemoteHost(javax.servlet.http.HttpServletRequest request){
	    String ip = request.getHeader("x-forwarded-for");
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
	        ip = request.getHeader("Proxy-Client-IP");
	    }
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
	        ip = request.getHeader("WL-Proxy-Client-IP");
	    }
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
	        ip = request.getRemoteAddr();
	    }
	    return ip.equals("0:0:0:0:0:0:0:1")?"127.0.0.1":ip;
	}
	
	@RequestMapping("/getLunboList")
	@ResponseBody
	public  Map<Object, Object> getList(){
		return MapUtil.getSuccessJson(lunboService.getList());
	}
	
	@RequestMapping("/delLunbo")
	@ResponseBody
	public  Map<Object, Object> delLunbo(Long id){
		lunboService.del(id);
		return MapUtil.getSuccessJson();
	}
	
	@RequestMapping("/getLunboId")
	@ResponseBody
	public Map<Object, Object> getLunboId(Long id){
		return MapUtil.getSuccessJson(lunboService.getLunboId(id));
	}
}
