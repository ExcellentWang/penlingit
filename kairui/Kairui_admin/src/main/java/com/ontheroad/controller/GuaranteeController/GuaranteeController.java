package com.ontheroad.controller.GuaranteeController;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ontheroad.dto.TbCustomerserviceDto;
import com.ontheroad.dto.TbGuranteeDto;
import com.ontheroad.entity.GuaranteeType;
import com.ontheroad.entity.Staff;
import com.ontheroad.entity.TbCustomerservicedetails;
import com.ontheroad.entity.TbGuarantee;
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
	
	/**
	 * 保修卡
	 * @param t
	 * @return
	 */
	@RequestMapping("/selectByExampleGuarantee")
	@ResponseBody
	public Map<Object, Object> selectByExampleGuarantee(TbGuranteeDto t){
		return MapUtil.getSuccessJson(guaranteeService.getTbGuaranteeList(t),guaranteeService.getTbGuaranteeList(t).size());
	}
	
	@RequestMapping("/selGuaranteeId")
	@ResponseBody
	public Map<Object, Object> selGuaranteeId(Integer id){
		return MapUtil.getSuccessJson(guaranteeService.selTbGuarantee(id));
	}
	
	@RequestMapping("/addOrUpdateGuarantee")
	@ResponseBody
	public Map<Object, Object> addOrUpdate(TbGuarantee t){
		guaranteeService.addOrUpdateTbGuarantee(t);
		return MapUtil.getSuccessJson();
	}
	/**
	 * 用户报修
	 */
	@RequestMapping("/selectByExampleGuaranteeCustomer")
	@ResponseBody
	public Map<Object, Object> selectByExampleGuaranteeCustomer(TbCustomerserviceDto t){
		return MapUtil.getSuccessJson(guaranteeService.getCustomerservice(t),guaranteeService.getCustomerservice(t).size());
	}
	
	@RequestMapping("/selGuaranteeCustomerId")
	@ResponseBody
	public Map<Object, Object> selGuaranteeCustomerId(Integer id){
		return MapUtil.getSuccessJson(guaranteeService.getCustomerserviceById(id));
	}
	
	/**
	 * 报修日志
	 */
	@RequestMapping("/selGuaranteeCustomerTail")
	@ResponseBody
	public Map<Object, Object> selGuaranteeCustomerTail(Long customerId){
		return MapUtil.getSuccessJson(guaranteeService.seltail(customerId),guaranteeService.seltail(customerId).size());
	}
	
	@RequestMapping("/saveGuaranteeCustomerTail")
	@ResponseBody
	public Map<Object, Object> saveGuaranteeCustomerTail(TbCustomerservicedetails t,Long id){
		guaranteeService.tail(t,id);
		return MapUtil.getSuccessJson();
	}
}
