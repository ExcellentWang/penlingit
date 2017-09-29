package com.ontheroad.controller.DeviceController;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ontheroad.pojo.Constant.BaseConstant;
import com.ontheroad.pojo.TerminalDevice.DeviceAppointment;
import com.ontheroad.pojo.TerminalDevice.DeviceRemind;
import com.ontheroad.pojo.TerminalDevice.DeviceShare;
import com.ontheroad.pojo.TerminalDevice.TerminalDevice;
import com.ontheroad.pojo.user.User;
import com.ontheroad.service.DeviceService.DeviceService;

@RestController
@RequestMapping("/device")
public class DeviceController extends BaseConstant{
	
	@Autowired
    private DeviceService deviceService;

	/**
	 * 
	 * 获取用户设备列表  2017.7.19
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/deviceList")
    public Map<Object,Object> regist(User user) {
		//返回前端map
	    Map<Object,Object> map = new HashMap<Object,Object>(); 
        try {	        	
        	return deviceService.getUserDevice(user);      	        	        	
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", BaseConstant.appUserErrorStatus);
    		map.put("msg", "服务器异常");
    		map.put("extra",null);
    		map.put("resultMap", null);
            return map;
        }
    }
	
	/**
	 * 绑定设备 2017. 7.21
	 * 
	 * @param ds
	 * @return
	 */

	@RequestMapping(value = "/bindingDevice", method = RequestMethod.POST)
    public Map<Object,Object> bindingDevice(DeviceShare ds) {
		//返回前端map
	    Map<Object,Object> map = new HashMap<Object,Object>(); 
        try {	        	
        	return deviceService.bindingDevice(ds);      	        	        	
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", BaseConstant.appUserErrorStatus);
    		map.put("msg", "服务器异常");
    		map.put("extra",null);
    		map.put("resultMap", null);
            return map;
        }
    }
	
	/**
	 * 分享设备 2017. 8.2
	 * 
	 * @param ds
	 * @return
	 */

	@RequestMapping(value = "/shareDevice", method = RequestMethod.POST)
    public Map<Object,Object> shareDevice(DeviceShare ds) {
		//返回前端map
	    Map<Object,Object> map = new HashMap<Object,Object>(); 
        try {	        	
        	return deviceService.shareDevice(ds);      	        	        	
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", BaseConstant.appUserErrorStatus);
    		map.put("msg", "服务器异常");
    		map.put("extra",null);
    		map.put("resultMap", null);
            return map;
        }
    }
	
	
	/**
	 * 删除设备
	 */
	@RequestMapping(value = "/deleteDevice", method = RequestMethod.POST)
    public Map<Object,Object> deleteDevice(DeviceShare ds) {
		//返回前端map
	    Map<Object,Object> map = new HashMap<Object,Object>(); 
        try {
        	return deviceService.deleteDevice(ds);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", BaseConstant.appUserErrorStatus);
    		map.put("msg", "服务器异常");
    		map.put("extra",null);
    		map.put("resultMap", null);
            return map;
        }
    }

	/**
	 * 删除设备共享
	 */
	@RequestMapping(value = "/deleteDeviceShare", method = RequestMethod.POST)
	public Map<Object,Object> deleteDeviceShare(DeviceShare ds) {
		//返回前端map
		Map<Object,Object> map = new HashMap<Object,Object>();
		try {
			return deviceService.deleteDeviceShare(ds);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("code", BaseConstant.appUserErrorStatus);
			map.put("msg", "服务器异常");
			map.put("extra",null);
			map.put("resultMap", null);
			return map;
		}
	}
	
	/*
	 * 单个设备详情
	 * 
	 */
	
	@RequestMapping(value = "/getDeviceDetail")
    public Map<Object,Object> getDeviceDetail(TerminalDevice t) {
		//返回前端map
	    Map<Object,Object> map = new HashMap<Object,Object>(); 
        try {	        	
        	return deviceService.getDeviceDetail(t);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", BaseConstant.appUserErrorStatus);
    		map.put("msg", "服务器异常");
    		map.put("extra",null);
    		map.put("resultMap", null);
            return map;
        }
    }
	
	/**
	 * 设备语音播报
	 * 
	 */
	@RequestMapping(value = "/deviceBroadcast", method = RequestMethod.POST)
    public Map<Object,Object> deviceBroadcast(TerminalDevice t,String instructions) {
		//返回前端map
	    Map<Object,Object> map = new HashMap<Object,Object>(); 
        try {	        	
        	return deviceService.deviceBroadcast(t,instructions);      	        	        	
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", BaseConstant.appUserErrorStatus);
    		map.put("msg", "服务器异常");
    		map.put("extra",null);
    		map.put("resultMap", null);
            return map;
        }
    }
	
	/**
	 * 背光值设置
	 * 
	 */
	@RequestMapping(value = "/deviceBacklight", method = RequestMethod.POST)
    public Map<Object,Object> deviceBacklight(TerminalDevice t,String instructions) {
		//返回前端map
	    Map<Object,Object> map = new HashMap<Object,Object>(); 
        try {	        	
        	return deviceService.deviceBacklight(t,instructions);      	        	        	
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", BaseConstant.appUserErrorStatus);
    		map.put("msg", "服务器异常");
    		map.put("extra",null);
    		map.put("resultMap", null);
            return map;
        }
    }

	/**
	 * 设备提醒设置
	 * 
	 */
	@RequestMapping(value = "/deviceRemind", method = RequestMethod.POST)
    public Map<Object,Object> deviceRemind(DeviceRemind d,String instructions) {
		//返回前端map
	    Map<Object,Object> map = new HashMap<Object,Object>(); 
        try {	        	
        	return deviceService.deviceRemind(d,instructions);      	        	        	
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", BaseConstant.appUserErrorStatus);
    		map.put("msg", "服务器异常");
    		map.put("extra",null);
    		map.put("resultMap", null);
            return map;
        }
    }

	/**
	 * 设备提醒读取
	 *
	 */
	@RequestMapping(value = "/findDeviceRemind", method = RequestMethod.POST)
	public Map<Object,Object> findDeviceRemind(TerminalDevice t) {
		//返回前端map
		Map<Object,Object> map = new HashMap<Object,Object>();
		try {
			return deviceService.findDeviceRemind(t);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("code", BaseConstant.appUserErrorStatus);
			map.put("msg", "服务器异常");
			map.put("extra",null);
			map.put("resultMap", null);
			return map;
		}
	}

	/*
	 * 预约设置
	 * 
	 */
	@RequestMapping(value = "/deviceAppointment", method = RequestMethod.POST)
    public Map<Object,Object> deviceAppointment(DeviceAppointment d) {
		//返回前端map
	    Map<Object,Object> map = new HashMap<Object,Object>(); 
        try {	        	
        	return deviceService.deviceAppointment(d);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", BaseConstant.appUserErrorStatus);
    		map.put("msg", "服务器异常");
    		map.put("extra",null);
    		map.put("resultMap", null);
            return map;
        }
    }
	
	
	/*
	 * 设备下发指令通用接口
	 * 
	 * 
	 */
	
	@RequestMapping(value = "/deviceSendInstruction", method = RequestMethod.POST)
    public Map<Object,Object> deviceSendInstruction(String instructions) {
		//返回前端map
	    Map<Object,Object> map = new HashMap<Object,Object>(); 
        try {	        	
        	return deviceService.deviceSendInstruction(instructions);      	        	        	
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", BaseConstant.appUserErrorStatus);
    		map.put("msg", "服务器异常");
    		map.put("extra",null);
    		map.put("resultMap", null);
            return map;
        }
    }

	/**
	 * 获取分享列表
	 */
	@RequestMapping(value = "/findDeviceShares", method = RequestMethod.POST)
	public Map<Object, Object> findDeviceShares(int user_id) {
		//返回前端map
		Map<Object,Object> map = new HashMap<Object,Object>();
		try {
			return deviceService.findDeviceShares(user_id);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("code", BaseConstant.appUserErrorStatus);
			map.put("msg", "服务器异常");
			map.put("extra",null);
			map.put("resultMap", null);
			return map;
		}
	}

	@RequestMapping(value = "/getRepairTypes", method = RequestMethod.POST)
	public Map<Object, Object> getRepairTypes() {
		//返回前端map
		Map<Object,Object> map = new HashMap<Object,Object>();
		try {
			return deviceService.getRepairTypes();
		} catch (Exception e) {
			e.printStackTrace();
			map.put("code", BaseConstant.appUserErrorStatus);
			map.put("msg", "服务器异常");
			map.put("extra",null);
			map.put("resultMap", null);
			return map;
		}
	}


	@RequestMapping(value = "/getDeviceError", method = RequestMethod.POST)
	public Map<Object, Object> getDeviceError(Integer equipment_id) {
		//返回前端map
		Map<Object,Object> map = new HashMap<Object,Object>();
		try {
			return deviceService.getDeviceError(equipment_id);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("code", BaseConstant.appUserErrorStatus);
			map.put("msg", "服务器异常");
			map.put("extra",null);
			map.put("resultMap", null);
			return map;
		}
	}

	@RequestMapping(value = "/updateDeviceName", method = RequestMethod.POST)
	public Map<Object, Object> updateDeviceName(TerminalDevice terminalDevice) {
		//返回前端map
		Map<Object,Object> map = new HashMap<Object,Object>();
		try {
			return deviceService.updateDeviceName(terminalDevice);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("code", BaseConstant.appUserErrorStatus);
			map.put("msg", "服务器异常");
			map.put("extra",null);
			map.put("resultMap", null);
			return map;
		}
	}
	/**
	 * 
	* 
	* @Description: 维修状态
	* @param terminalDevice
	* @return  
	* Map<Object,Object>   
	* @throws
	 */
	@Deprecated
	@RequestMapping(value = "/repairStatus")
	public Map<Object, Object> repairStatus(TerminalDevice terminalDevice) {
		//返回前端map
		Map<Object,Object> map = new HashMap<Object,Object>();
		try {
			return deviceService.repairStatus(terminalDevice.getEquipment_id());
		} catch (Exception e) {
			e.printStackTrace();
			map.put("code", BaseConstant.appUserErrorStatus);
			map.put("msg", "服务器异常");
			map.put("extra",null);
			map.put("resultMap", null);
			return map;
		}
	}
}
