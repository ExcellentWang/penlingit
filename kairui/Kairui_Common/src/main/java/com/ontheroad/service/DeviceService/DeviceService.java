package com.ontheroad.service.DeviceService;

import com.ontheroad.pojo.TerminalDevice.*;
import com.ontheroad.pojo.user.User;
import org.springframework.remoting.service.annotation.RemoteService;

import java.util.List;
import java.util.Map;

@RemoteService
public interface DeviceService {

	Map<Object, Object> getUserDevice(User user);

	Map<Object, Object> bindingDevice(DeviceShare ds);

	Map<Object, Object> deleteDevice(DeviceShare ds);

	Map<Object, Object> deleteDeviceShare(DeviceShare ds);

	Map<Object, Object> getDeviceDetail(TerminalDevice t);

	Map<Object, Object> findDeviceRemind(TerminalDevice t);

	Map<Object, Object> deviceBroadcast(TerminalDevice t,String instructions);

	Map<Object, Object> deviceBacklight(TerminalDevice t, String instructions);

	Map<Object, Object> deviceRemind(DeviceRemind d, String instructions);

	Map<Object, Object> deviceAppointment(DeviceAppointment d);
	
	/**
	 * 
	* 
	* @Description: 发送指令
	* @param instructions
	* @return  
	* Map<Object,Object>   
	* @throws
	 */
	Map<Object, Object> deviceSendInstruction(String instructions);

	Map<Object, Object> shareDevice(DeviceShare ds);

	Map<Object, Object> findDeviceShares(int equipment_id);

	Map<Object, Object> getRepairTypes();

	Map<Object, Object> getDeviceLogs();

	Map<Object, Object> insertDeviceLog(DeviceLog log);

	Map<Object, Object> getSessions();

	Map<Object, Object> getDeviceError(Integer equipment_id);

	Map<Object, Object> updateDeviceName(TerminalDevice terminalDevice);

	Map<Object, Object> getAllDevices();
	
	Map<Object, Object> repairStatus(Integer equipment_id);
	
	/**
	 * 通过条件获取设备列表
	 * @param TerminalDevice
	 * @return
	 */
	Map<Object, Object> getDevicesByExample(TerminalDeviceVo TerminalDevice);
	
	/**
	 * 设备异常日志列表
	 * @return
	 */
	List<DeviceVo> getDeviceErrorList(DeviceVo vo);
	
	/**
	 * 添加设备
	 * @param device
	 */
	void insert(TerminalDevice device);
	
	
	
	
}
