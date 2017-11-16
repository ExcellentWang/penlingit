package com.ontheroad.mysql.Mapper.DeviceMapper;

import com.ontheroad.pojo.TerminalDevice.DeviceShare;
import com.ontheroad.pojo.TerminalDevice.TerminalDevice;

import java.util.List;
import java.util.Map;

public interface DeviceShareMapper {

	List<DeviceShare> findShare(Map<Object, Object> selectMap);
	List<DeviceShare> findSharedDevices(Map<Object, Object> selectMap);
	TerminalDevice findDeviceShares(Integer equipment_id);

	void insertDeviceShare(DeviceShare ds);

	void deleteDevice(DeviceShare ds);

	void deleteDeviceShare(DeviceShare ds);

	Integer findDeviceOwner(Integer equipment_id);

	List<TerminalDevice> findUserDevices(Integer user_id);

	List<DeviceShare> findSharesOfDevice(Integer equipment_id);
	
	DeviceShare findUserDeviceStatus(DeviceShare ds);
	
	void updateShareDeviceRemark(DeviceShare ds);

}
