package com.ontheroad.mysql.impl.DeviceImpl;

import java.io.File;
import java.net.InetSocketAddress;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.mina.core.future.ReadFuture;
import org.apache.mina.core.future.WriteFuture;
import org.apache.mina.core.session.IoSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.danga.MemCached.MemCachedClient;
import com.ontheroad.mysql.Mapper.AppMapper.AppUserMapper;
import com.ontheroad.mysql.Mapper.AppMapper.CustomerserviceMapper;
import com.ontheroad.mysql.Mapper.AppMapper.GuaranteeMapper;
import com.ontheroad.mysql.Mapper.DeviceMapper.DeviceErrorMapper;
import com.ontheroad.mysql.Mapper.DeviceMapper.DeviceLogMapper;
import com.ontheroad.mysql.Mapper.DeviceMapper.DeviceMapper;
import com.ontheroad.mysql.Mapper.DeviceMapper.DeviceShareMapper;
import com.ontheroad.mysql.dao.DeviceUseLogMapper;
import com.ontheroad.mysql.dao.DeviceWaterMapper;
import com.ontheroad.mysql.dao.GuaranteeTypeMapper;
import com.ontheroad.mysql.entity.DeviceUseLog;
import com.ontheroad.mysql.entity.DeviceUseLogExample;
import com.ontheroad.mysql.entity.DeviceWater;
import com.ontheroad.mysql.entity.DeviceWaterExample;
import com.ontheroad.mysql.entity.GuaranteeType;
import com.ontheroad.mysql.entity.GuaranteeTypeExample;
import com.ontheroad.mysql.socketUtil.DeviceMessage;
import com.ontheroad.mysql.socketUtil.MinaServerHandler;
import com.ontheroad.mysql.ymodem.Modem;
import com.ontheroad.pojo.Constant.BaseConstant;
import com.ontheroad.pojo.TerminalDevice.DeviceAppointment;
import com.ontheroad.pojo.TerminalDevice.DeviceError;
import com.ontheroad.pojo.TerminalDevice.DeviceLog;
import com.ontheroad.pojo.TerminalDevice.DeviceRemind;
import com.ontheroad.pojo.TerminalDevice.DeviceShare;
import com.ontheroad.pojo.TerminalDevice.DeviceVo;
import com.ontheroad.pojo.TerminalDevice.TerminalDevice;
import com.ontheroad.pojo.TerminalDevice.TerminalDeviceVo;
import com.ontheroad.pojo.user.Guarantee;
import com.ontheroad.pojo.user.User;
import com.ontheroad.service.DeviceService.DeviceService;

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
	
	@Autowired
	private DeviceUseLogMapper  deviceUseLogMapper;
	@Autowired
	private DeviceWaterMapper deviceWaterMapper;
	@Autowired
	private GuaranteeTypeMapper guaranteeTypeMapper;
	

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
			for (TerminalDevice t : deviceList) {
				t.setProcessRepair(deviceMapper.repairStatus(t.getEquipment_id())>0?true:false);
			}
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
				//设备改名
				t.setEquipmentName(ds.getEquipmentName());
				deviceMapper.updateDeviceName(t);
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
				ds.setRemark(ds.getRemark());
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
	public Map<Object, Object> getDeviceDetail(TerminalDevice t,Integer user_id) {
		// 返回前端map
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			Map<Object, Object> resultMap = new HashMap<Object, Object>();
			t = deviceMapper.getDeviceDetailById(t.getEquipment_id());
			//更换为字段，查询设备是否有正在进行中的售后
			t.setProcessRepair(deviceMapper.repairStatus(t.getEquipment_id())>0?true:false);
			t.setAppointment(deviceMapper.findAppointment(t));//预约
			Map<Object,Object> fieldMap = new HashMap<Object,Object>();
			fieldMap.put("equipment_id", t.getEquipment_id());
			Guarantee g = guaranteeMapper.getGuaranteeDetail(fieldMap);
			t.setGuarantee(g);
			//查询设备最新的使用记录,暂时不需要
			DeviceUseLogExample example=new DeviceUseLogExample();
			example.createCriteria().andEquipmentIdEqualTo( t.getEquipment_id());
			List<DeviceUseLog> ls=deviceUseLogMapper.selectByExample(example);
			if(ls.size()>0){
				DeviceUseLog deviceUseLog=ls.get(ls.size()-1);
				t.setFlowspeed(deviceUseLog.getFlowspeed());
				t.setCodeWaterTemp(deviceUseLog.getCodeWaterTemp());
				t.setHotWaterTemp(deviceUseLog.getHotWaterTemp());
			}
			//设备分享还是绑定状态
			DeviceShare ds=new DeviceShare();
			ds.setEquipment_id(t.getEquipment_id());
			ds.setShare_user_id(user_id);
			ds=deviceShareMapper.findUserDeviceStatus(ds);
			t.setStatus(String.valueOf(ds.getStatus()));
			t.setRemark(ds.getRemark());
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
		try {
			deviceSendInstruction(d.getInstructions());//发送指令
			d.setValid(true);
			deviceMapper.deviceAppointment(d);//存参数
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
					session.getConfig().setUseReadOperation(true); 
					session.getConfig().setReaderIdleTime(2000);
					WriteFuture writeFuture=session.write(msg.toString());//发指令
					
					//2.同步返回
					writeFuture.awaitUninterruptibly();
					//判断消息是否发送完成
					if(writeFuture.isWritten()){
						ReadFuture readFuture = session.read();
						//等待消息响应
						logger.info("mina开始等待同步消息---");
						readFuture.awaitUninterruptibly();
						logger.info("mina等待消息结束---");
						//是否响应成功
						if(readFuture.isRead()){
							//获取消息
							Object message = readFuture.getMessage();
							logger.info("mina同步消息成功---"+message);
							resultMap.put("instructions", message.toString());
						}else {
							map.put("code", BaseConstant.appUserErrorStatus);
							map.put("msg", "指令发送失败！");
						}
					}
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
		//获取维修类型
		GuaranteeTypeExample example=new GuaranteeTypeExample();
		List<GuaranteeType> ls=guaranteeTypeMapper.selectByExample(example);
		for (GuaranteeType g : ls) {
			Map<Object, Object> r = new HashMap<>();
			r.put("id", g.getId());
			r.put("name", g.getTypeName());
			repairTypes.add(r);
		}
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

	@Override
	public List<DeviceWater> getDeviceWaterByDeviceId(Long deviceId) {
		DeviceWaterExample example=new DeviceWaterExample();
		example.createCriteria().andDeviceIdEqualTo(deviceId);
		return deviceWaterMapper.selectByExample(example);
	}

	@Override
	public TerminalDevice getDeviceDetail(Integer equipment_id) {
		return deviceMapper.getDeviceDetailById(equipment_id);
	}

	@Override
	public List<Map<String, String>> getzaixianlv() {
		return deviceMapper.getzaixianlv();
	}

	@Override
	public Integer OnlineSize(String province) {
		return deviceMapper.OnlineSize(province);
	}

	@Override
	public String getzaixianlvByCity(String province) {
		return deviceMapper.getzaixianlvByCity(province);
	}

	@Override
	public Integer allSize(String province) {
		return deviceMapper.allSize(province);
	}

	@Override
	public Map<Object, Object> updateShareDeviceRemark(DeviceShare ds) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		deviceShareMapper.updateShareDeviceRemark(ds);
		map.put("code", BaseConstant.appUserSuccessStatus);
		map.put("msg", "修改成功");
		return map;
	}

	@Override
	public void uoploadGu(File file, String instructions) throws Exception {
		Modem ymodem=new Modem();
		IoSession se=getSession(instructions);
		if(se==null){
			throw new RuntimeException("设备不在线！");
		}
		ymodem.send(file.toPath(), null, getSession(instructions),instructions);
	}
	/**
	 * 根据指令获取session
	 * @param msg
	 * @return
	 */
	private IoSession getSession(String instructions){
		DeviceMessage msg = new DeviceMessage(instructions);
		if(msg == null || msg.getDeviceID() == null) {
			return null;
		}
		for(IoSession session: MinaServerHandler.sessions) {
			// find device session
			String session_device_id = (String)session.getAttribute("device_id");
			String session_device_type = (String)session.getAttribute("device_type");
			if(StringUtils.isEmpty(session_device_id)) {
				continue;
			}
			if(msg.getDeviceType().equals(session_device_type) && msg.getDeviceID().equals(session_device_id)) {
				return session;
			}
		}
		return null;
	}
}
