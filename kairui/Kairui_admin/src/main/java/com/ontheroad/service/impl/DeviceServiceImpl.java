package com.ontheroad.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ontheroad.dao.equipmentDatatypeMapper;
import com.ontheroad.entity.equipmentDatatype;
import com.ontheroad.service.IDeviceService;

@Service
public class DeviceServiceImpl implements IDeviceService{
	@Autowired
	private equipmentDatatypeMapper equipmentDatatypeMapper;
	@Override
	public void addDeviceType(equipmentDatatype equipmentDatatype) {
		equipmentDatatypeMapper.insertSelective(equipmentDatatype);
	}

}
