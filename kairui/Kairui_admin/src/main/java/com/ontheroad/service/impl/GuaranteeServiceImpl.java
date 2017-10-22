package com.ontheroad.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Strings;
import com.ontheroad.dao.GuaranteeTypeMapper;
import com.ontheroad.entity.GuaranteeType;
import com.ontheroad.entity.GuaranteeTypeExample;
import com.ontheroad.entity.GuaranteeTypeExample.Criteria;
import com.ontheroad.service.GuaranteeService;
@Service
public class GuaranteeServiceImpl implements GuaranteeService {
	@Autowired
	private GuaranteeTypeMapper guaranteeTypeMapper;
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

}
