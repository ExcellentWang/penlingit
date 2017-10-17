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

import com.ontheroad.core.util.UploadUtil;
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
        String sImg= UploadUtil.save(file, request);
        lunbo.setsImg(sImg);
		lunboService.addOrUpdate(lunbo);
		return MapUtil.getSuccessJson();
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
