package com.ontheroad.mysql.Mapper.DeviceMapper;

import com.ontheroad.pojo.TerminalDevice.DeviceAppointment;
import com.ontheroad.pojo.TerminalDevice.DeviceRemind;
import com.ontheroad.pojo.TerminalDevice.DeviceShare;
import com.ontheroad.pojo.TerminalDevice.TerminalDevice;
import com.ontheroad.pojo.TerminalDevice.TerminalDeviceVo;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface DeviceMapper {

	List<TerminalDevice> findUserListByUserId(int userId);

	void deviceBroadcast(TerminalDevice t);

	TerminalDevice getDeviceDetailById(Integer eqiupment_id);
	TerminalDevice getDeviceAppointmentById(Integer eqiupment_id);

	void deviceBacklight(TerminalDevice t);
	/**
	 * 设定提醒方式
	 * @param d
	 */
	void deviceRemind(DeviceRemind d);
	/**
	 * 更新预约表
	 * @param d
	 */
	void deviceAppointment(DeviceAppointment d);
	TerminalDevice findDeviceByNum(String equipmentNum);
	/**
	 * @author wang 2018/1/13 上午10:19
	 * @param
	 * @return
	 * 获取设备预约信息
	**/

	DeviceAppointment findDeviceAppointment(TerminalDevice terminalDevice);

	void updateDevice(TerminalDevice t);

	void updateAppointment(DeviceAppointment deviceAppointment);

	void updateDeviceName(TerminalDevice t);
	/**
	 * 查询预约表
	 * @param t
	 * @return
	 */
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
	List<TerminalDevice> getDevicesByExample(TerminalDeviceVo terminalDeviceVo);
	
	void insert(TerminalDevice device);
	/**
	 * 统计在线率
	 * @return
	 */
	List<Map<String, String>> getzaixianlv();
	/**
	 * 在线设备总数
	 * @return
	 */
	Integer OnlineSize(@Param("province")String province);
	/**
	 * 设备总数
	 * @return
	 */
	Integer allSize(@Param("province")String province);
	
	/**
	 * 通过城市统计在线率
	 * @return
	 */
	String getzaixianlvByCity(@Param("province")String province);
	
	/**
	 * 
	 * @return
	 */
	void updateAppointmenTime(@Param("time")Date time,@Param("equipment_id") Integer eqiupment_id);
	/**
	 * 添加设备预约表
	 * @param device
	 */
	void insertDeviceAppointment(DeviceAppointment device);
	
	/**
	 * 添加设备预约表
	 * @param device
	 */
	void insertEquipmentstatus(@Param("equipment_id")Integer equipment_id,@Param("workStatus")Integer workStatus);
	
	/**
	 * 添加设备预约表
	 * @param device
	 */
	void insertRemind(@Param("equipment_id")Integer equipment_id);
}
