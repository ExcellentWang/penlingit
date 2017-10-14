package com.ontheroad.service;

import java.util.List;
import java.util.Map;

import com.ontheroad.entity.equipmentDatatype;
import com.ontheroad.utils.WebUtil;

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
	
	/**
	 * 删除设备类型
	 * @param device
	 */
	Map<Object, Object> delDeviceType(Integer equipmentType);
	
	/**
	 * 设备类型查询单个
	 * @param device
	 */
	equipmentDatatype selectDeviceTypeId(Integer equipmentType);
}
