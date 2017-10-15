package com.ontheroad.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ontheroad.entity.Lunbo;
import com.ontheroad.service.LunboService;
import com.ontheroad.utils.MapUtil;
import com.ontheroad.utils.WebUtil;
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
	public  Map<Object, Object> add(Lunbo lunbo){
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
