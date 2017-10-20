package com.ontheroad.controller.DeviceController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ontheroad.entity.equipmentDatatype;
import com.ontheroad.mysql.entity.DeviceWater;
import com.ontheroad.pojo.Constant.BaseConstant;
import com.ontheroad.pojo.TerminalDevice.DeviceAppointment;
import com.ontheroad.pojo.TerminalDevice.DeviceError;
import com.ontheroad.pojo.TerminalDevice.DeviceRemind;
import com.ontheroad.pojo.TerminalDevice.DeviceShare;
import com.ontheroad.pojo.TerminalDevice.DeviceVo;
import com.ontheroad.pojo.TerminalDevice.TerminalDevice;
import com.ontheroad.pojo.TerminalDevice.TerminalDeviceVo;
import com.ontheroad.service.IDeviceService;
import com.ontheroad.service.DeviceService.DeviceService;
import com.ontheroad.utils.MapUtil;

@RestController
@RequestMapping("/device")
public class DeviceController extends BaseConstant{
	
	@Autowired
    private DeviceService deviceService;
	
	@Autowired
	private IDeviceService iDeviceService;
	/**
	 * 
	 * 获取用户设备列表  2017.7.19
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/deviceList")
    public Map<Object,Object> regist(TerminalDeviceVo vo) {
		//返回前端map
	    Map<Object,Object> map = new HashMap<Object,Object>(); 
        try {	 
        	return deviceService.getDevicesByExample( vo);
        } catch (Exception e) {	
            e.printStackTrace();
            map.put("code", BaseConstant.appUserErrorStatus);
    		map.put("msg", "服务器异常");
    		map.put("totalItem",null);
    		map.put("data", null);
            return map;
        }
    }
	/**
	 * 
	 * 设备类型查询
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/deviceTypeList")
    public Map<Object,Object> deviceTypeList() {
		//返回前端map
	    Map<Object,Object> map = new HashMap<Object,Object>(); 
        try {	 
        	List<equipmentDatatype> ls=iDeviceService.selectAllDeviceType();
        	map.put("code", BaseConstant.appUserSuccessStatus);
     		map.put("msg", "获取成功");
     		map.put("totalItem",ls.size());
     		map.put("data", ls);
        	return map;
        } catch (Exception e) {	
            e.printStackTrace();
            map.put("code", BaseConstant.appUserErrorStatus);
    		map.put("msg", "服务器异常");
    		map.put("totalItem",null);
    		map.put("data", null);
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
	@RequestMapping(value = "/deviceAppointment")
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
	
	@RequestMapping(value = "/deviceSendInstruction")
    public Map<Object,Object> deviceSendInstruction(String instructions) {
		//返回前端map
	    Map<Object,Object> map = new HashMap<Object,Object>(); 
        try {	      
        	//指令的校验位处理
        	return deviceService.deviceSendInstruction(getValidate(instructions));      	        	        	
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
	* @Description:  获取校验位
	* @param str
	* @return   
	* String  
	* @throws 
	* @author
	* @data 2017年10月13日 下午5:14:09
	 */
	public String getValidate(String str) {
		String old = str;
		str=str+",";
		char[] chars = str.toCharArray();
		int sub = 0;
		for (int i = 0; i < chars.length; i++) {
			sub += (int) chars[i];
		}
		str = Integer.toHexString(sub).toUpperCase();
		str = str.substring(str.length() - 2);
		String result="<"+old+","+str+">";
		//计算命令长度
		//两位
		result=result.replace("len","0"+String.valueOf(result.length()));
		return result;
	}
	public static void main(String[] args) {
		System.out.println(new DeviceController().getValidate("LDCT01201704230001:xtds,len,2017,10,14,10,45,56"));
//		System.out.println("<LDCT01201704230001:wcal,058,1,1,1,1,1,4,3950,E0>".substring(26, 28));
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


	@RequestMapping(value = "/getDeviceError")
	public Map<Object, Object> getDeviceError(DeviceVo vo) {
		//返回前端map
		Map<Object,Object> map = new HashMap<Object,Object>();
		try {
			List<DeviceVo> ls=deviceService.getDeviceErrorList(vo);
			for (DeviceVo deviceVo : ls) {				
				deviceVo.setErrorEventV();
			}
			map.put("code", BaseConstant.appUserErrorStatus);
			map.put("msg", "成功");
			map.put("totalItem",ls.size());
			map.put("data",ls);
			return map;
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
	
	/**
	 * 
	* 
	* @Description: 添加设备类型
	* @param terminalDevice
	* @return  
	* Map<Object,Object>   
	* @throws
	 */
	@RequestMapping(value = "/addDeviceType")
	public Map<Object, Object> addDeviceType(equipmentDatatype equipmentDatatype) {
		//返回前端map
		Map<Object,Object> map = new HashMap<Object,Object>();
		try {
			iDeviceService.addDeviceType(equipmentDatatype);
			map.put("resultMap", null);
			map.put("code", BaseConstant.appUserSuccessStatus);
			map.put("msg", "添加成功");
			map.put("extra", null);
			return map;
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
	* @Description: 删除设备类型
	* @param terminalDevice
	* @return  
	* Map<Object,Object>   
	* @throws
	 */
	@RequestMapping(value = "/delDeviceType")
	public Map<Object, Object> delDeviceType(Integer equipmentType) {
		//返回前端map
		return iDeviceService.delDeviceType(equipmentType);
	}
	
	/**
	 * 
	* 
	* @Description: 查询设备类型单个
	* @param terminalDevice
	* @return  
	* Map<Object,Object>   
	* @throws
	 */
	@RequestMapping(value = "/selectDeviceTypeId")
	public Map<Object, Object> selectDeviceTypeId(Integer equipmentType) {
		//返回前端map
		return MapUtil.getSuccessJson(iDeviceService.selectDeviceTypeId(equipmentType));
	}
	/**
	 * 设备对时
	 * @param instructions
	 * @return
	 */
	@RequestMapping(value = "/deviceDuiShi")
    public Map<Object,Object> deviceDuiShi(String equipmentNum) {
		//返回前端map
	    Map<Object,Object> map = new HashMap<Object,Object>(); 
        try {
        	
        	//<LDCT01201704230001:xtds,052,2017,05,02,20,12,38,OR>
        	String instructions=equipmentNum+":xtds,len,"+
        	new SimpleDateFormat("yyyy,MM,dd,HH,mm,ss").format(new Date());
        	//指令的校验位处理
        	System.out.println(getValidate(instructions));
        	return deviceService.deviceSendInstruction(getValidate(instructions));         	
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
	* @Description: 添加设备
	* @param terminalDevice
	* @return  
	* Map<Object,Object>   
	* @throws
	 */
	@RequestMapping(value = "/addDevice")
	public Map<Object, Object> addDevice(TerminalDevice device) {
		//返回前端map
		Map<Object,Object> map = new HashMap<Object,Object>();
		try {
			//验证
			TerminalDeviceVo vo=new TerminalDeviceVo();
			vo.setEquipmentNum(device.getEquipmentNum());
			map=deviceService.getDevicesByExample(vo);
			List<TerminalDeviceVo> vos=(List<TerminalDeviceVo>)map.get("data");
			if(vos.size()>0){
				map.put("code", 10001);
				map.put("msg", "已添加");
				map.put("extra",null);
				map.put("resultMap", null);
				return map;
			}
			device.setType(device.getEquipmentNum().substring(4,6));
			device.setCreated_at(new Date());
			deviceService.insert(device);
			map.put("resultMap", null);
			map.put("code", BaseConstant.appUserSuccessStatus);
			map.put("msg", "添加成功");
			map.put("extra", null);
			return map;
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
	* @Description:
	* @param terminalDevice 通过设备id获取设备用水量节水量
	* @return  
	* Map<Object,Object>   
	* @throws
	 */
	@RequestMapping(value = "/getUseWaterByDeviceId")
	public Map<Object, Object> getUseWaterByDeviceId(Long deviceId) {
		List<DeviceWater> ls=deviceService.getDeviceWaterByDeviceId(deviceId);
		return MapUtil.getSuccessJson(ls);
	}
}
