package com.ontheroad.controller.GuaranteeController;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ontheroad.entity.GuaranteeType;
import com.ontheroad.service.GuaranteeService;
import com.ontheroad.utils.MapUtil;

@Controller
public class GuaranteeController {
	@Autowired
	private GuaranteeService guaranteeService;
	
	@RequestMapping("/selectByExampleGuaranteeType")
	@ResponseBody
	public Map<Object, Object> selectByExample(GuaranteeType guaranteeType){
		return MapUtil.getSuccessJson(guaranteeService.getTypeList(guaranteeType));
	}
	
	@RequestMapping("/addOrUpdateGuaranteeType")
	@ResponseBody
	public Map<Object, Object> addOrUpdate(GuaranteeType guaranteeType){
		guaranteeService.addOrUpdateType(guaranteeType);
		return MapUtil.getSuccessJson();
	}
	
	@RequestMapping("/delGuaranteeType")
	@ResponseBody
	public Map<Object, Object> del(Long id){
		guaranteeService.delType(id);
		return MapUtil.getSuccessJson();
	}
	
	@RequestMapping("/selGuaranteeTypeById")
	@ResponseBody
	public Map<Object, Object> selGuaranteeTypeById(Long id){
		return MapUtil.getSuccessJson(guaranteeService.selType(id));
	}
}
