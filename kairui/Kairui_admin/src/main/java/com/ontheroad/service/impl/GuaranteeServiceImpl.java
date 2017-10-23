package com.ontheroad.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Strings;
import com.ontheroad.dao.GuaranteeTypeMapper;
import com.ontheroad.dao.StaffMapper;
import com.ontheroad.dao.TbGuaranteeMapper;
import com.ontheroad.dto.TbGuranteeDto;
import com.ontheroad.entity.GuaranteeType;
import com.ontheroad.entity.GuaranteeTypeExample;
import com.ontheroad.entity.GuaranteeTypeExample.Criteria;
import com.ontheroad.entity.Staff;
import com.ontheroad.entity.StaffExample;
import com.ontheroad.entity.TbGuarantee;
import com.ontheroad.entity.TbGuaranteeExample;
import com.ontheroad.service.GuaranteeService;
@Service
public class GuaranteeServiceImpl implements GuaranteeService {
	@Autowired
	private GuaranteeTypeMapper guaranteeTypeMapper;
	@Autowired 
	private StaffMapper staffMapper;
	@Autowired
	private TbGuaranteeMapper tbGuaranteeMapper;
	
	@Override
	public List<GuaranteeType> getTypeList(GuaranteeType t) {
		GuaranteeTypeExample example=new GuaranteeTypeExample();
		Criteria c=example.createCriteria();
		if(!Strings.isNullOrEmpty(t.getDeviceType())){
			c.andDeviceTypeEqualTo(t.getDeviceType());
		}
		return guaranteeTypeMapper.selectByExample(example);
	}

	@Override
	public GuaranteeType addOrUpdateType(GuaranteeType t) {
		if(t.getId()!=null){
			guaranteeTypeMapper.updateByPrimaryKey(t);
		}else{
			guaranteeTypeMapper.insert(t);
		}
		return t;
	}

	@Override
	public void delType(Long id) {
		guaranteeTypeMapper.deleteByPrimaryKey(id);
	}

	@Override
	public GuaranteeType selType(Long id) {
		return guaranteeTypeMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<Staff> getStaffList(Staff t) {
		StaffExample example=new StaffExample();
		com.ontheroad.entity.StaffExample.Criteria c=example.createCriteria();
		if(!Strings.isNullOrEmpty(t.getName())){
			c.andNameLike("%"+t.getName()+"%");
		}
		if(!Strings.isNullOrEmpty(t.getPhone())){
			c.andPhoneLike("%"+t.getPhone()+"%");
		}
		if(!Strings.isNullOrEmpty(t.getStaffNum())){
			c.andStaffNumLike("%"+t.getStaffNum()+"%");
		}
		return staffMapper.selectByExample(example);
	}

	@Override
	public Staff addOrUpdateStaff(Staff t) {
		t.setMotifyTime(new Date());
		if(t.getId()!=null){
			t.setCreateTime(new Date());
			staffMapper.updateByPrimaryKey(t);
		}else{
			staffMapper.insert(t);
		}
		return t;
	}

	@Override
	public void delStaff(Long id) {
		staffMapper.deleteByPrimaryKey(id);
	}

	@Override
	public Staff selStaff(Long id) {
		return staffMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<TbGuranteeDto> getTbGuaranteeList(TbGuranteeDto t) {
		return tbGuaranteeMapper.selectByExample2(t);
	}

	@Override
	public TbGuarantee addOrUpdateTbGuarantee(TbGuarantee t) {
		tbGuaranteeMapper.updateByPrimaryKeySelective(t);
		return t;
	}

	@Override
	public void delTbGuarantee(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public TbGuarantee selTbGuarantee(Integer id) {
		// TODO Auto-generated method stub
		return tbGuaranteeMapper.selectByPrimaryKey(id);
	}

}
