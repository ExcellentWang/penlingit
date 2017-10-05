package com.ontheroad.mysql.Mapper.DeviceMapper;

import com.ontheroad.pojo.TerminalDevice.DeviceAppointment;
import com.ontheroad.pojo.TerminalDevice.DeviceRemind;
import com.ontheroad.pojo.TerminalDevice.TerminalDevice;

import java.util.List;
import java.util.Map;

public interface DeviceMapper {

	List<TerminalDevice> findUserListByUserId(int userId);

	void deviceBroadcast(TerminalDevice t);

	TerminalDevice getDeviceDetailById(Integer eqiupment_id);

	void deviceBacklight(TerminalDevice t);

	void deviceRemind(DeviceRemind d);

	void deviceAppointment(DeviceAppointment d);

	TerminalDevice findDeviceByNum(String equipmentNum);

	void updateDevice(TerminalDevice t);

	void updateDeviceName(TerminalDevice t);

	DeviceAppointment findAppointment(TerminalDevice t);
	/**
	 * 
	* 
	* @Description: 更新设备状态
	* @param t  
	* @return void   
	* @throws 
	* @author 胡俊
	* @email 510830970@qq.com
	 */
	void updateDeviceWorkingStatus(TerminalDevice t);

	List<TerminalDevice> getAllDevices();

	DeviceRemind findDeviceRemind(TerminalDevice t);
	
	int repairStatus(Integer eqiupment_id);
	
	/**
	 * 通过条件获取设备列表
	 * @param TerminalDevice
	 * @return
	 */
	List<TerminalDevice> getDevicesByExample(TerminalDevice TerminalDevice);

}
