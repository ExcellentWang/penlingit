package com.ontheroad.mysql.impl.DeviceImpl;

import com.danga.MemCached.MemCachedClient;
import com.ontheroad.mysql.Mapper.AppMapper.AppUserMapper;
import com.ontheroad.mysql.Mapper.AppMapper.CustomerserviceMapper;
import com.ontheroad.mysql.Mapper.AppMapper.GuaranteeMapper;
import com.ontheroad.mysql.Mapper.DeviceMapper.DeviceErrorMapper;
import com.ontheroad.mysql.Mapper.DeviceMapper.DeviceLogMapper;
import com.ontheroad.mysql.Mapper.DeviceMapper.DeviceMapper;
import com.ontheroad.mysql.Mapper.DeviceMapper.DeviceShareMapper;
import com.ontheroad.mysql.socketUtil.DeviceMessage;
import com.ontheroad.mysql.socketUtil.MinaServerHandler;
import com.ontheroad.pojo.Constant.BaseConstant;
import com.ontheroad.pojo.TerminalDevice.*;
import com.ontheroad.pojo.user.Guarantee;
import com.ontheroad.pojo.user.User;
import com.ontheroad.service.DeviceService.DeviceService;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.mina.core.session.IoSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.InetSocketAddress;
import java.text.DateFormat;
import java.util.*;

@Service
@Transactional
public class DeviceImpl implements DeviceService {

	@Autowired
	private DeviceMapper deviceMapper;

	@Autowired
	private DeviceShareMapper deviceShareMapper;
	
	@Autowired
	private AppUserMapper appUserMapper;

	@Autowired
	private DeviceLogMapper deviceLogMapper;

	@Autowired
	private CustomerserviceMapper customerserviceMapper;

	@Autowired
	private DeviceErrorMapper deviceErrorMapper;

	@Autowired
	private GuaranteeMapper guaranteeMapper;

	@Autowired
	private MemCachedClient memCachedClient;
	

	private static final Logger logger = Logger.getLogger(DeviceImpl.class);

	@Override
	public Map<Object, Object> getUserDevice(User user) {
		// 返回前端map
		Map<Object, Object> map = new HashMap<Object, Object>();
		// 用户拥有或者共享设备集合
		List<TerminalDevice> deviceList = new ArrayList<TerminalDevice>();
		try {
			int userId = user.getUser_id();
			deviceList = deviceMapper.findUserListByUserId(userId);
			if (deviceList != null && deviceList.size() > 0) {
				map.put("code", BaseConstant.appUserSuccessStatus);
				map.put("msg", "获取设备列表成功");
				map.put("extra", null);
				map.put("resultMap", deviceList);
				return map;
			} else {
				map.put("code", BaseConstant.appUserSuccessStatus);
				map.put("msg", "该用户暂时没有设备");
				map.put("extra", null);
				map.put("resultMap", null);
				return map;
			}

		} catch (Exception e) {
			e.printStackTrace();
			map.put("code", BaseConstant.appUserErrorStatus);
			map.put("msg", "服务器异常");
			map.put("extra", null);
			map.put("resultMap", null);
			return map;
		}
	}

	@Override
	public Map<Object, Object> bindingDevice(DeviceShare ds) {
		// 返回前端map
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			List<DeviceShare> list = new ArrayList<DeviceShare>();
			Map<Object, Object> selectMap = new HashMap<Object, Object>();
			TerminalDevice t = deviceMapper.findDeviceByNum(ds.getEquipmentNum());
			if(t == null) {
				map.put("code", BaseConstant.appUserErrorStatus);
				map.put("msg", "找不到设备");
				map.put("extra", null);
				map.put("resultMap", null);
				return map;
			}
			selectMap.put("equipment_id", t.getEquipment_id());			
			list = deviceShareMapper.findShare(selectMap);
			if (list != null && list.size() > 0) {
				map.put("code", BaseConstant.appUserErrorStatus);
				map.put("msg", "该设备已经被绑定");
				map.put("extra", null);
				map.put("resultMap", null);
				return map;
			} else {
				ds.setEquipment_id(t.getEquipment_id());	
				ds.setStatus(0);
				ds.setAuthority(1);
				ds.setShare_user_id(ds.getUser_id());
				deviceShareMapper.insertDeviceShare(ds);
				map.put("code", BaseConstant.appUserSuccessStatus);
				map.put("msg", "绑定成功");
				map.put("extra", null);
				map.put("resultMap", null);
				return map;

			}
		} catch (Exception e) {
			e.printStackTrace();
			map.put("code", BaseConstant.appUserErrorStatus);
			map.put("msg", "服务器异常");
			map.put("extra", null);
			map.put("resultMap", null);
			return map;
		}
	}

	
	@Override
	public Map<Object, Object> shareDevice(DeviceShare ds) {
		// 返回前端map
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {			
			Map<Object, Object> userMap = new HashMap<Object, Object>();
		    String phone = ds.getPhone();
		    userMap.put("phone", phone);			
			List<User> u = appUserMapper.findUserByPhone(userMap);
			if(u!=null&&u.size()>0){
				User user = u.get(0);
				// 查找设备所属用户
				Integer owner_id = deviceShareMapper.findDeviceOwner(ds.getEquipment_id());
				// 不能分享给本人
				if(owner_id == user.getUser_id()) {
					map.put("code", BaseConstant.appUserFaileStatus);
					map.put("msg", "不能分享给自己");
					map.put("extra", null);
					map.put("resultMap", null);
					return map;
				}

				Map<Object, Object> selectMap = new HashMap<Object, Object>();
				selectMap.put("equipment_id", ds.getEquipment_id());
				selectMap.put("share_user_id", user.getUser_id());
				List<DeviceShare> sharedDevices = deviceShareMapper.findSharedDevices(selectMap);
				if(sharedDevices != null && sharedDevices.size() > 0) {
					map.put("code", BaseConstant.appUserFaileStatus);
					map.put("msg", "已经分享给该用户");
					map.put("extra", null);
					map.put("resultMap", null);
					return map;
				}

				ds.setShare_user_id(user.getUser_id());
				ds.setEquipment_id(ds.getEquipment_id());	
				ds.setStatus(1);
				ds.setAuthority(1);
				deviceShareMapper.insertDeviceShare(ds);
				map.put("code", BaseConstant.appUserSuccessStatus);
				map.put("msg", "分享成功");
				map.put("extra", null);
				map.put("resultMap", null);
				return map;
				
			}else{
				map.put("code", BaseConstant.appUserSuccessStatus);
				map.put("msg", "该手机号未注册");
				map.put("extra", null);
				map.put("resultMap", null);
				return map;
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			map.put("code", BaseConstant.appUserErrorStatus);
			map.put("msg", "服务器异常");
			map.put("extra", null);
			map.put("resultMap", null);
			return map;
		}
	}
	
	
	
	
	@Override
	public Map<Object, Object> deleteDevice(DeviceShare ds) {
		// 返回前端map
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			deviceShareMapper.deleteDevice(ds);
			map.put("code", BaseConstant.appUserSuccessStatus);
			map.put("msg", "删除成功");
			map.put("extra", null);
			map.put("resultMap", null);
			return map;
		} catch (Exception e) {
			e.printStackTrace();
			map.put("code", BaseConstant.appUserErrorStatus);
			map.put("msg", "服务器异常");
			map.put("extra", null);
			map.put("resultMap", null);
			return map;
		}
	}


	/**
	 * 删除设备共享
	 */
	@Override
	public Map<Object, Object> deleteDeviceShare(DeviceShare ds) {
		// 返回前端map
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			deviceShareMapper.deleteDeviceShare(ds);
			map.put("code", BaseConstant.appUserSuccessStatus);
			map.put("msg", "删除设备共享成功");
			map.put("extra", null);
			map.put("resultMap", null);
			return map;
		} catch (Exception e) {
			e.printStackTrace();
			map.put("code", BaseConstant.appUserErrorStatus);
			map.put("msg", "服务器异常");
			map.put("extra", null);
			map.put("resultMap", null);
			return map;
		}
	}

	@Override
	public Map<Object, Object> getDeviceDetail(TerminalDevice t) {
		// 返回前端map
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			Map<Object, Object> resultMap = new HashMap<Object, Object>();
			t = deviceMapper.getDeviceDetailById(t.getEquipment_id());
			//更换为字段，查询设备是否有正在进行中的售后
			t.setProcessRepair(deviceMapper.repairStatus(t.getEquipment_id())>0?false:true);
			t.setAppointment(deviceMapper.findAppointment(t));
			Map<Object,Object> fieldMap = new HashMap<Object,Object>();
			fieldMap.put("equipment_id", t.getEquipment_id());
			Guarantee g = guaranteeMapper.getGuaranteeDetail(fieldMap);
			t.setGuarantee(g);
			
			resultMap.put("deviceDetail", t);
			map.put("code", BaseConstant.appUserSuccessStatus);
			map.put("msg", "获取成功");
			map.put("extra", null);
			map.put("resultMap", resultMap);
			return map;

		} catch (Exception e) {
			e.printStackTrace();
			map.put("code", BaseConstant.appUserErrorStatus);
			map.put("msg", "服务器异常");
			map.put("extra", null);
			map.put("resultMap", null);
			return map;
		}
	}

	@Override
	public Map<Object, Object> findDeviceRemind(TerminalDevice t) {
		// 返回前端map
		Map<Object, Object> map = new HashMap<Object, Object>();
		Map<Object, Object> resultMap = new HashMap<Object, Object>();
		try {
			DeviceRemind r = deviceMapper.findDeviceRemind(t);
			resultMap.put("deviceRemind", r);
			map.put("code", BaseConstant.appUserSuccessStatus);
			map.put("msg", "修改成功");
			map.put("extra", null);
			map.put("resultMap", resultMap);
			return map;
		} catch (Exception e) {
			e.printStackTrace();
			map.put("code", BaseConstant.appUserErrorStatus);
			map.put("msg", "服务器异常");
			map.put("extra", null);
			map.put("resultMap", null);
			return map;
		}
	}

	@Override
	public Map<Object, Object> deviceBroadcast(TerminalDevice t,String instructions) {
		// 返回前端map
	    Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			deviceMapper.deviceBroadcast(t);
			map.put("code", BaseConstant.appUserSuccessStatus);
			map.put("msg", "修改成功");
			map.put("extra", null);
			map.put("resultMap", null);
			return map;
		} catch (Exception e) {
			e.printStackTrace();
			map.put("code", BaseConstant.appUserErrorStatus);
			map.put("msg", "服务器异常");
			map.put("extra", null);
			map.put("resultMap", null);
			return map;
		}
	
	}

	@Override
	public Map<Object, Object> deviceBacklight(TerminalDevice t, String instructions) {
		// 返回前端map
	    Map<Object, Object> map = new HashMap<Object, Object>();
		try {
//			deviceMapper.deviceBacklight(t);

			DeviceMessage msg = new DeviceMessage(t.getEquipmentNum(),"bltr",
					new ArrayList<>(Arrays.asList(t.getBacklight())));

			return deviceSendInstruction(msg.toString());

//			map.put("code", BaseConstant.appUserSuccessStatus);
//			map.put("msg", "修改成功");
//			map.put("extra", null);
//			map.put("resultMap", null);
//			return map;
		} catch (Exception e) {
			e.printStackTrace();
			map.put("code", BaseConstant.appUserErrorStatus);
			map.put("msg", "服务器异常");
			map.put("extra", null);
			map.put("resultMap", null);
			return map;
		}
	}

	@Override
	public Map<Object, Object> deviceRemind(DeviceRemind d, String instructions) {
		// 返回前端map
	    Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			deviceMapper.deviceRemind(d);
			map.put("code", BaseConstant.appUserSuccessStatus);
			map.put("msg", "设置成功");
			map.put("extra", null);
			map.put("resultMap", null);			
			return map;
		} catch (Exception e) {
			e.printStackTrace();
			map.put("code", BaseConstant.appUserErrorStatus);
			map.put("msg", "服务器异常");
			map.put("extra", null);
			map.put("resultMap", null);
			return map;
		}
	}

	
	
	@Override
	public Map<Object, Object> deviceAppointment(DeviceAppointment d) {
		// 返回前端map
	    Map<Object, Object> map = new HashMap<Object, Object>();
		ArrayList<String> args = new ArrayList<>();
		try {
			TerminalDevice device = deviceMapper.getDeviceDetailById(d.getEquipment_id());
			deviceSendInstruction(d.getInstructions());
			d.setValid(true);
			device.setWorkStatus(2);
//			deviceMapper.updateDeviceWorkingStatus(device);
			deviceMapper.deviceAppointment(d);
			map.put("code", BaseConstant.appUserSuccessStatus);
			map.put("msg", "预约成功");
			map.put("extra", null);
			map.put("resultMap", null);	
			
						
			return map;
		} catch (Exception e) {
			e.printStackTrace();
			map.put("code", BaseConstant.appUserErrorStatus);
			map.put("msg", "服务器异常");
			map.put("extra", null);
			map.put("resultMap", null);
			return map;
		}
	}

	@Override
	public Map<Object, Object> deviceSendInstruction(String instructions) {
		// 返回前端map
	    Map<Object, Object> map = new HashMap<Object, Object>();
		Map<Object, Object> resultMap = new HashMap<Object, Object>();
		try {
			DeviceMessage msg = new DeviceMessage(instructions);
			if(msg == null || msg.getDeviceID() == null) {
				map.put("code", BaseConstant.appUserFaileStatus);
				map.put("msg", "指令有误");
				map.put("extra", null);
				map.put("resultMap", null);
				return map;
			}

			for(IoSession session: MinaServerHandler.sessions) {
				// find device session
				String session_device_id = (String)session.getAttribute("device_id");
				String session_device_type = (String)session.getAttribute("device_type");
				logger.info("在线的设备---------------------------"+session_device_id);
				if(StringUtils.isEmpty(session_device_id)) {
					continue;
				}

				if(msg.getDeviceType().equals(session_device_type) && msg.getDeviceID().equals(session_device_id)) {
					session.write(msg.toString());
					resultMap.put("instructions", msg.toString());
					resultMap.put("sentAt", DateFormat.getDateInstance().format(Calendar.getInstance().getTime()));
					InetSocketAddress addr = (InetSocketAddress)session.getRemoteAddress();
					resultMap.put("ip", addr.getAddress().getHostAddress());

					DeviceLog log = new DeviceLog();
					log.setDirection("SEND");
					log.setDeviceID(msg.getDeviceID());
					log.setDeviceType(msg.getDeviceType());
					log.setCommand(msg.getCommandType());
					log.setArgs(org.apache.commons.lang3.StringUtils.join(msg.getArgs(), ","));
					log.setRaw(msg.toString());
					log.setCreatedAt(Calendar.getInstance().getTime());

					deviceLogMapper.insertDeviceLog(log);

					map.put("code", BaseConstant.appUserSuccessStatus);
					map.put("msg", "发送成功");
					map.put("extra", null);
					map.put("resultMap", resultMap);
					return map;
				}
			}
			map.put("code", BaseConstant.appUserFaileStatus);
			map.put("msg", "找不到设备");
			map.put("extra", null);
			map.put("resultMap", null);
			return map;

		} catch (Exception e) {
			e.printStackTrace();
			map.put("code", BaseConstant.appUserErrorStatus);
			map.put("msg", "服务器异常");
			map.put("extra", null);
			map.put("resultMap", null);
			return map;
		}
	}

	@Override
	public Map<Object, Object> findDeviceShares(int share_user_id) {
		// 返回前端map
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			List<TerminalDevice> deviceList = deviceShareMapper.findUserDevices(share_user_id);
			for(TerminalDevice d: deviceList) {
				d.setDeviceShares(deviceShareMapper.findSharesOfDevice(d.getEquipment_id()));
			}
			map.put("code", BaseConstant.appUserSuccessStatus);
			map.put("msg", "发送成功");
			map.put("extra", null);
			map.put("resultMap", deviceList);
			return map;
		} catch (Exception e) {
			e.printStackTrace();
			map.put("code", BaseConstant.appUserErrorStatus);
			map.put("msg", "服务器异常");
			map.put("extra", null);
			map.put("resultMap", null);
			return map;
		}
	}

	@Override
	public Map<Object, Object> getRepairTypes() {
		// 返回前端map
		Map<Object, Object> map = new HashMap<Object, Object>();
		List<Object> repairTypes = new ArrayList<>();
		Map<Object, Object> repairType_1 = new HashMap<>();
		Map<Object, Object> repairType_2 = new HashMap<>();
		Map<Object, Object> repairType_3 = new HashMap<>();
		repairType_1.put("id", "1");
		repairType_1.put("name", "不通电");
		repairType_2.put("id", "1");
		repairType_2.put("name", "不出水");
		repairType_3.put("id", "1");
		repairType_3.put("name", "不加热");

		repairTypes.add(repairType_1);
		repairTypes.add(repairType_2);
		repairTypes.add(repairType_3);

		map.put("resultMap", repairTypes);
		map.put("code", BaseConstant.appUserSuccessStatus);
		map.put("msg", "发送成功");
		map.put("extra", null);
		return map;
	}

	@Override
	public Map<Object, Object> getDeviceLogs() {
		Map<Object, Object> map = new HashMap<Object, Object>();
		List<DeviceLog> deviceLogs = deviceLogMapper.getDeviceLogs();

		for(DeviceLog l: deviceLogs) {
			l.setRaw(StringEscapeUtils.escapeHtml(l.getRaw()));
		}

		map.put("resultMap", deviceLogs);
		map.put("code", BaseConstant.appUserSuccessStatus);
		map.put("msg", "发送成功");
		map.put("extra", null);
		return map;
	}

	@Override
	public Map<Object, Object> insertDeviceLog(DeviceLog log) {
		// 返回前端map
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			deviceLogMapper.insertDeviceLog(log);
			map.put("code", BaseConstant.appUserSuccessStatus);
			map.put("msg", "日志已记录");
			map.put("extra", null);
			map.put("resultMap", null);
			return map;
		} catch (Exception e) {
			e.printStackTrace();
			map.put("code", BaseConstant.appUserErrorStatus);
			map.put("msg", "服务器异常");
			map.put("extra", null);
			map.put("resultMap", null);
			return map;
		}
	}

	@Override
	public Map<Object, Object> getSessions() {
		Map<Object, Object> map = new HashMap<Object, Object>();
		Map<String, Object> resultMap = new HashMap<String, Object>();
		for(IoSession session: MinaServerHandler.sessions) {
			resultMap.put(String.valueOf(session.getId()), session.getAttribute("device_id"));
		}
		map.put("resultMap", resultMap);
		map.put("code", BaseConstant.appUserSuccessStatus);
		map.put("msg", "发送成功");
		map.put("extra", null);
		return map;
	}

	/**
	 * 设备故障
	 */
	@Override
	public Map<Object, Object> getDeviceError(Integer equipment_id) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			DeviceError err = deviceErrorMapper.getDeviceError(equipment_id);
			map.put("resultMap", err);
			map.put("code", BaseConstant.appUserSuccessStatus);
			map.put("msg", "发送成功");
			map.put("extra", null);
			return map;
		} catch (Exception e) {
			e.printStackTrace();
			map.put("code", BaseConstant.appUserErrorStatus);
			map.put("msg", "服务器异常");
			map.put("extra", null);
			map.put("resultMap", null);
			return map;
		}
	}

	/**
	 * 修改设备名
	 */
	@Override
	public Map<Object, Object> updateDeviceName(TerminalDevice terminalDevice) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			deviceMapper.updateDeviceName(terminalDevice);
			map.put("resultMap", null);
			map.put("code", BaseConstant.appUserSuccessStatus);
			map.put("msg", "发送成功");
			map.put("extra", null);
			return map;
		} catch (Exception e) {
			e.printStackTrace();
			map.put("code", BaseConstant.appUserErrorStatus);
			map.put("msg", "服务器异常");
			map.put("extra", null);
			map.put("resultMap", null);
			return map;
		}
	}

	@Override
	public Map<Object, Object> getAllDevices() {
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			map.put("resultMap", deviceMapper.getAllDevices());
			map.put("code", BaseConstant.appUserSuccessStatus);
			map.put("msg", "发送成功");
			map.put("extra", null);
			return map;
		} catch (Exception e) {
			e.printStackTrace();
			map.put("code", BaseConstant.appUserErrorStatus);
			map.put("msg", "服务器异常");
			map.put("extra", null);
			map.put("resultMap", null);
			return map;
		}
	}

	@Override
	public Map<Object, Object> repairStatus(Integer equipment_id) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			map.put("resultMap", deviceMapper.repairStatus(equipment_id)>0?"false":"true");
			map.put("code", BaseConstant.appUserSuccessStatus);
			map.put("msg", "获取成功");
			map.put("extra", null);
			return map;
		} catch (Exception e) {
			e.printStackTrace();
			map.put("code", BaseConstant.appUserErrorStatus);
			map.put("msg", "服务器异常");
			map.put("extra", null);
			map.put("resultMap", null);
			return map;
		}
	}

	@Override
	public Map<Object, Object> getDevicesByExample(TerminalDeviceVo vo) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		List<TerminalDevice> ls=deviceMapper.getDevicesByExample(vo);
		map.put("data", ls);
		map.put("code", BaseConstant.appUserSuccessStatus);
		map.put("msg", "发送成功");
		map.put("totalItem",ls.size() );
		return map;
	}

	@Override
	public List<DeviceVo> getDeviceErrorList(DeviceVo vo) {
		return deviceErrorMapper.getDeviceErrorList(vo);
	}

	@Override
	public void insert(TerminalDevice device) {
		deviceMapper.insert(device);
		
	}

	
}
