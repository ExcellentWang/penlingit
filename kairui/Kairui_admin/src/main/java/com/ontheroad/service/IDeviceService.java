package com.ontheroad.service;

import java.util.List;

import com.ontheroad.entity.equipmentDatatype;

public interface IDeviceService {
	/**
	 * 添加设备类型
	 * @param equipmentDatatype
	 */
	void addDeviceType(equipmentDatatype equipmentDatatype);
	
	/**
	 * 设备类型查询
	 * @param device
	 */
	List<equipmentDatatype> selectAllDeviceType();
}
