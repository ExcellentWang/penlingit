package com.ontheroad.service;

import java.util.List;
import java.util.Map;

import com.ontheroad.entity.EquipmentDatatype;

public interface IDeviceService {
	/**
	 * 添加设备类型
	 * @param equipmentDatatype
	 */
	void addDeviceType(EquipmentDatatype equipmentDatatype);
	
	/**
	 * 设备类型查询
	 * @param device
	 */
	List<EquipmentDatatype> selectAllDeviceType();
	
	/**
	 * 删除设备类型
	 * @param device
	 */
	Map<Object, Object> delDeviceType(Integer equipmentType);
	
	/**
	 * 设备类型查询单个
	 * @param device
	 */
	EquipmentDatatype selectDeviceTypeId(Integer equipmentType);
	
	/**
	 * 设备总数
	 * @param device
	 */
	Integer  allSize();
	
}
