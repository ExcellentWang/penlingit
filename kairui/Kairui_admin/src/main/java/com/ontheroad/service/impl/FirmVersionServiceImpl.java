package com.ontheroad.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ontheroad.dao.FirmVersionMapper;
import com.ontheroad.entity.FirmVersion;
import com.ontheroad.entity.FirmVersionExample;
import com.ontheroad.entity.FirmVersionExample.Criteria;
import com.ontheroad.service.FirmVersionService;
@Service
public class FirmVersionServiceImpl implements FirmVersionService {
	@Autowired
	private FirmVersionMapper firmVersionMapper;
	@Override
	public void add(FirmVersion FirmVersion) {
		firmVersionMapper.insertSelective(FirmVersion);
	}

	@Override
	public List<FirmVersion> selectByExample(FirmVersion FirmVersion) {
		FirmVersionExample example=new FirmVersionExample();
		Criteria c=example.createCriteria();
		if(FirmVersion.getProduct()!=null&&FirmVersion.getProduct()!=""){
			c.andProductEqualTo(FirmVersion.getProduct());
		}
		if(FirmVersion.getType()!=null&&FirmVersion.getType()!=""){
			c.andTypeEqualTo(FirmVersion.getType());
		}
		return firmVersionMapper.selectByExample(example);
	}

	@Override
	public void del(Long id) {
		firmVersionMapper.deleteByPrimaryKey(id);
	}

}
