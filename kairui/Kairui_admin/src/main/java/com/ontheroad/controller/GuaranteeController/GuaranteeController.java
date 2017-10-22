package com.ontheroad.controller.GuaranteeController;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ontheroad.entity.GuaranteeType;
import com.ontheroad.entity.Staff;
import com.ontheroad.service.GuaranteeService;
import com.ontheroad.utils.MapUtil;

@Controller
public class GuaranteeController {
	@Autowired
	private GuaranteeService guaranteeService;
	
	@RequestMapping("/selectByExampleGuaranteeType")
	@ResponseBody
	public Map<Object, Object> selectByExample(GuaranteeType guaranteeType){
		return MapUtil.getSuccessJson(guaranteeService.getTypeList(guaranteeType),guaranteeService.getTypeList(guaranteeType).size());
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
	
	@RequestMapping("/selectByExampleStaff")
	@ResponseBody
	public Map<Object, Object> selectByExample(Staff staff){
		return MapUtil.getSuccessJson(guaranteeService.getStaffList(staff),guaranteeService.getStaffList(staff).size());
	}
	
	@RequestMapping("/addOrUpdateStaff")
	@ResponseBody
	public Map<Object, Object> addOrUpdate(Staff staff){
		guaranteeService.addOrUpdateStaff(staff);
		return MapUtil.getSuccessJson();
	}
	
	@RequestMapping("/delStaff")
	@ResponseBody
	public Map<Object, Object> delStaff(Long id){
		guaranteeService.delStaff(id);
		return MapUtil.getSuccessJson();
	}
	
	@RequestMapping("/selStaffById")
	@ResponseBody
	public Map<Object, Object> selStaffById(Long id){
		return MapUtil.getSuccessJson(guaranteeService.selStaff(id));
	}
}
