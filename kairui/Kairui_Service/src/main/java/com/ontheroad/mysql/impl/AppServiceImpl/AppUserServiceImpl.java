package com.ontheroad.mysql.impl.AppServiceImpl;

import com.avos.avoscloud.LogUtil.log;
import com.danga.MemCached.MemCachedClient;
import com.ontheroad.core.util.Md5Util;
import com.ontheroad.mysql.Mapper.AppMapper.*;
import com.ontheroad.mysql.Mapper.DeviceMapper.DeviceMapper;
import com.ontheroad.mysql.dao.TbCustomerservicedetailsMapper;
import com.ontheroad.mysql.dao.TbEulaMapper;
import com.ontheroad.mysql.dao.TbGuaranteeMapper;
import com.ontheroad.mysql.dao.TbWcalMapper;
import com.ontheroad.mysql.dao.TsDetailMapper;
import com.ontheroad.mysql.entity.TbCustomerservicedetails;
import com.ontheroad.mysql.entity.TbCustomerservicedetailsExample;
import com.ontheroad.mysql.entity.TbEula;
import com.ontheroad.mysql.entity.TbEulaExample;
import com.ontheroad.mysql.entity.TbGuarantee;
import com.ontheroad.mysql.entity.TbWcal;
import com.ontheroad.mysql.entity.TbWcalExample;
import com.ontheroad.mysql.entity.TsDetail;
import com.ontheroad.mysql.entity.TsDetailExample;
import com.ontheroad.pojo.Constant.BaseConstant;
import com.ontheroad.pojo.user.*;
import com.ontheroad.service.AppService.AppUserService;
import com.ontheroad.service.DeviceService.DeviceService;
import com.ontheroad.service.SmsService;
import com.ontheroad.utils.StringUtilsCommon;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class AppUserServiceImpl implements AppUserService{
	public final static Logger logger=LoggerFactory.getLogger(AppUserServiceImpl.class);

	private boolean DEBUG = false;

	@Autowired
    private AppUserMapper appUserMapper;

	@Autowired
	private GuaranteeMapper guaranteeMapper;
	
	@Autowired
	private CustomerserviceMapper customerserviceMapper;
	
	@Autowired
	private CommonproblemMapper commonproblemMapper;
	
	@Autowired
	private FeedbackMapper feedbackMapper;

	@Autowired
	private EULAMapper eulaMapper;

	@Autowired
	private SmsService smsService;

	@Autowired
	private MemCachedClient memCachedClient;
	
	@Autowired
	private DeviceMapper deviceMapper;
	@Autowired
	private TbGuaranteeMapper tbGuaranteeMapper;
	
	@Autowired
	private TbCustomerservicedetailsMapper tbCustomerservicedetailsMapper;
	@Autowired
	private TbEulaMapper tbEulaMapper;
	@Autowired
	private TsDetailMapper tsDetailMapper;
	@Autowired
	private TbWcalMapper tbWcalMapper;
	@Override
	public Map<Object,Object> findUserByPhone(User user,String appCode) {								
		List<User> list = new ArrayList<>();
		Map<Object, Object> map = new HashMap<Object, Object>();
		 //返回结果内容map
	    Map<Object,Object> resultMap = new HashMap<Object,Object>();
	    try {
			String phone = user.getPhone();
	    	String verificache = (String) memCachedClient.get(phone);
			memCachedClient.delete(phone);
	    	if(!appCode.equals(verificache)){
	    		map.put("code", BaseConstant.appUserFaileStatus);
	    		map.put("msg", "验证码错误");
	    		map.put("extra",null);
	    		map.put("resultMap", null);       		
	            return map;        		
	    	}    	
	        Map<Object, Object> userMap = new HashMap<Object, Object>();
	    	userMap.put("phone", phone);
			list = appUserMapper.findUserByPhone(userMap);
	    	//检测用户是否存在
	    	if(list.size()>0){
	    		resultMap.put("userList", list);      		
	    		map.put("code", BaseConstant.appUserFaileStatus);
	    		map.put("msg", "手机号已经被注册");
	    		map.put("extra",null);
	    		map.put("resultMap", resultMap);       		
	            return map;        		
	    	}else if(list.size()==0){    
	    		//插入新用户
	    		String password = Md5Util.makeMd5Sum(user.getPassword().getBytes());
	    		user.setPassword(password);
	    		user.setRegisterTime(Calendar.getInstance().getTime());
	    		appUserMapper.insertAppUser(user);
	    		map.put("code", BaseConstant.appUserSuccessStatus);
	    		map.put("msg", "注册成功");
	    		map.put("extra",null);
	    		map.put("resultMap", null);       		
	    		return map;
	    	} else{
	    		map.put("code", BaseConstant.appUserErrorStatus);
	    		map.put("msg", "服务器异常");
	    		map.put("extra",null);
	    		map.put("resultMap", null);
	    		return map;
	    	} 
		} catch (Exception e) {
		    e.printStackTrace();
		    map.put("code", BaseConstant.appUserErrorStatus);
    		map.put("msg", "服务器异常");
    		map.put("extra",null);
    		map.put("resultMap", null);
    		return map;
		}	
	}
	
	@Override
	public Map<Object, Object> appUserGetVerificationCode(String phone) {
		//返回前端map
	    Map<Object,Object> map = new HashMap<Object,Object>();
	    Map<Object,Object> resultMap = new HashMap<Object,Object>();
		try {
			String verification = StringUtilsCommon.getRandNum(6);

			Map<Object, Object> sendResult = new HashMap<>();

			if(!DEBUG) {
				sendResult = new HashMap<>(smsService.sendVerificationCode(phone, verification));
			}

			if (DEBUG || sendResult.get("resultMap") != null) {
				// 设置验证码有效时间为10分钟
				Date expires = new Date(System.currentTimeMillis() + 10 * 60 * 1000);
				// 短信发送成功，将验证放入缓存中，以手机号为key
				memCachedClient.set(phone, verification, expires);
				logger.info("存入缓存的验证码----------"+memCachedClient.get(phone).toString());
				map.put("code", BaseConstant.appUserSuccessStatus);
				map.put("msg", "获取验证码成功");
				map.put("verification", verification);
				map.put("extra", null);
				map.put("resultMap", sendResult.get("resultMap"));
				return map;
			}
			else {
				map.put("code", BaseConstant.appUserFaileStatus);
				map.put("msg", "获取验证码失败");
				map.put("extra", null);
				map.put("resultMap", null);
				return map;
			}
		} catch (Exception e) {
			e.printStackTrace();
			map.put("code", BaseConstant.appUserErrorStatus);
    		map.put("msg", "服务器异常");
    		map.put("extra",null);
    		map.put("resultMap", null);
			return map;
		}
	}

	@Override
	public Map<Object, Object> appUserLogin(User user) {
		//返回前端map
	    Map<Object,Object> map = new HashMap<Object,Object>();
	    //返回结果内容map
	    Map<Object,Object> resultMap = new HashMap<Object,Object>();
		try {
			HashMap<Object, Object> userMap = new HashMap<>();
			userMap.put("phone", user.getPhone());
			userMap.put("password", Md5Util.makeMd5Sum(user.getPassword().getBytes()));
			User u = appUserMapper.findUserByPhonePassword(userMap);
			if(u==null){
				map.put("code", BaseConstant.appUserFaileStatus);
        		map.put("msg", "用户名或者密码错误");
        		map.put("extra",null);
        		map.put("resultMap", resultMap);       
				return map;
			}else{
				//更新登录时间
				u.setLoginTime(Calendar.getInstance().getTime());
				appUserMapper.updateLogin(u);
				u.setPassword(null);
				u.setPhone(StringUtilsCommon.changePhone(u.getPhone()));
				resultMap.put("user", u);

				map.put("code", BaseConstant.appUserSuccessStatus);
        		map.put("msg", "登陆成功");
        		map.put("extra",null);
        		map.put("resultMap", resultMap);       
				return map;

			}
		} catch (Exception e) {
			e.printStackTrace();
			map.put("code", BaseConstant.appUserErrorStatus);
    		map.put("msg", "服务器异常");
    		map.put("extra",null);
    		map.put("resultMap", null);
			return map;
		}	
		
	}
	@Override
	public Map<Object, Object> forgetPassword(String phone, String newPassword,String appCode) {
		//返回前端map
	    Map<Object,Object> tomap = new HashMap<Object,Object>();
		try {
			String verificache = (String) memCachedClient.get(phone);
			memCachedClient.delete(phone);
			if(!appCode.equals(verificache)){
				tomap.put("code", BaseConstant.appUserFaileStatus);
				tomap.put("msg", "验证码错误");
				tomap.put("extra",null);
				tomap.put("resultMap", null);       		
	            return tomap;        		
	    	}    	
			
			Map<Object,Object> map = new HashMap<Object,Object>();
			
			String newPass =  Md5Util.makeMd5Sum(newPassword.getBytes());
			
			map.put("phone", phone);
			map.put("password", newPass);				
			appUserMapper.updatePassword(map);
			
			tomap.put("code", BaseConstant.appUserSuccessStatus);
			tomap.put("msg", "修改密码成功");
			tomap.put("extra",null);
			tomap.put("resultMap", null);
			return tomap;
		} catch (Exception e) {
			e.printStackTrace();
			tomap.put("code", BaseConstant.appUserErrorStatus);
			tomap.put("msg", "服务器异常");
			tomap.put("extra",null);
			tomap.put("resultMap", null);
			return tomap;
			
		}
	}

	@Override
	public Map<Object, Object> updatePassword(User user, String newPassword,String oldPassword) {
		//返回前端map
	    Map<Object,Object> tomap = new HashMap<Object,Object>();
		try {
			Map<Object,Object> map = new HashMap<Object,Object>();
			
			String newPass =  Md5Util.makeMd5Sum(newPassword.getBytes());
					
			List<User> list = new ArrayList<User>();
			String oldPass = Md5Util.makeMd5Sum(oldPassword.getBytes());
			map.put("user_id", user.getUser_id());
			map.put("password", oldPass);	
			list = appUserMapper.findUserByIdPassword(map);
			if(list.size()>0){
				map.put("phone", user.getPhone());
				map.put("password", newPass);				
				appUserMapper.updatePassword(map);
				
				
				tomap.put("code", BaseConstant.appUserSuccessStatus);
				tomap.put("msg", "修改密码成功");
				tomap.put("extra",null);
				tomap.put("resultMap", null);
				return tomap;
			}else{
				tomap.put("code", BaseConstant.appUserFaileStatus);
				tomap.put("msg", "原密码输入错误");
				tomap.put("extra",null);
				tomap.put("resultMap", null);
				return tomap;
			}				
		
		} catch (Exception e) {
			e.printStackTrace();
			tomap.put("code", BaseConstant.appUserErrorStatus);
			tomap.put("msg", "服务器异常");
			tomap.put("extra",null);
			tomap.put("resultMap", null);
			return tomap;
			
		}
	}

	@Override
	public Map<Object, Object> updatePhone(User user, String newPhone, String code) {
		//返回前端map
	    Map<Object,Object> map = new HashMap<Object,Object>();	
		try {
			String verificache = (String) memCachedClient.get(newPhone);
			memCachedClient.delete(newPhone);
			//空指针，更改手机号
			if(!code.equals(verificache)){
				map.put("code", BaseConstant.appUserFaileStatus);
				map.put("msg", "验证码错误");
				map.put("extra",null);
				map.put("resultMap", null);
				return map;
			}
			Map<Object,Object> fieldMap = new HashMap<Object,Object>();
			fieldMap.put("userid", user.getUser_id());
			fieldMap.put("phone", newPhone);
			appUserMapper.updatePhone(fieldMap);

			map.put("code", BaseConstant.appUserSuccessStatus);
			map.put("msg", "修改手机号成功");
			map.put("extra",null);
			map.put("resultMap", null);	
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

	@Override
	public Map<Object, Object> appUserUpdateData(User user) {
		//返回前端map
	    Map<Object,Object> map = new HashMap<Object,Object>();	
		try {
			
			appUserMapper.updateData(user);

			map.put("code", BaseConstant.appUserSuccessStatus);
			map.put("msg", "修改用户资料成功");
			map.put("extra",null);
			map.put("resultMap", null);	
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

	
	
	@Override
	public Map<Object, Object> getGuaranteeDetail(Guarantee guarantee) {
		//返回前端map
	    Map<Object,Object> map = new HashMap<Object,Object>();
		try {
			int equipment_id = guarantee.getEquipment_id();
			Map<Object,Object> fieldMap = new HashMap<Object,Object>();
			fieldMap.put("equipment_id", equipment_id);
			Guarantee g = guaranteeMapper.getGuaranteeDetail(fieldMap);
			
			map.put("code", BaseConstant.appUserSuccessStatus);
			map.put("msg", "获取成功");
			map.put("extra",null);
			Map<Object, Object> introspected = new org.apache.commons.beanutils.BeanMap(g);
			Map<Object, Object> gMap = new HashMap<>();
			for(Object key: introspected.keySet()) {
				gMap.put(key + "", introspected.get(key));
			}
			// 保修卡未完善, 不返回联系电话，服务地区 详细地址 购买日期
			String guaranteeStatus = (String)gMap.get("status");
			if(guaranteeStatus == null || guaranteeStatus.equals("0")) {
				gMap.remove("phone");
				gMap.remove("area");
				gMap.remove("address");
				gMap.remove("buyTime");
			}
			map.put("resultMap", gMap);

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

	@Override
	public Map<Object, Object> submitGuaranteeDetail(Guarantee guarantee) {
		//返回前端map
	    Map<Object,Object> map = new HashMap<Object,Object>();
        try {
			guaranteeMapper.submitGuaranteeDetail(guarantee);
        	map.put("code", BaseConstant.appUserSuccessStatus);
			map.put("msg", "提交成功");
			map.put("extra",null);
			map.put("resultMap", null);
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

	@Override
	public Map<Object, Object> getCustomerList(Customerservice customerservice) {
		//返回前端map
	    Map<Object,Object> map = new HashMap<Object,Object>();
		try {
			Map<Object,Object> fieldMap = new HashMap<Object,Object>();
			List<Customerservice> list = new ArrayList<Customerservice>();
			fieldMap.put("user_id", customerservice.getUser_id());
			list = customerserviceMapper.getCustomerList(fieldMap);
			map.put("code", BaseConstant.appUserSuccessStatus);
			map.put("msg", "获取成功");
			map.put("extra",null);
			map.put("resultMap", list);
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

	@Override
	public Map<Object, Object> getCustomerDetail(Customerservice customerservice) {
		//返回前端map
	    Map<Object,Object> map = new HashMap<Object,Object>();
		try {
			Customerservice c = customerserviceMapper.getCustomerDetail(customerservice.getCustomer_id());

			List<String> pics = new ArrayList<>();
			List<Customerpicture> picadds = customerserviceMapper.getPics(customerservice.getCustomer_id());
			for(Customerpicture p: picadds) {
				pics.add(p.getPictureAddress());
			}
			if(pics.size()!=0){
				c.setPictureAdd(pics);
			}
			//获取报修进度
			TbCustomerservicedetailsExample example =new TbCustomerservicedetailsExample();
			example.createCriteria().andCustomerIdEqualTo(Long.valueOf(customerservice.getCustomer_id()));
			List<TbCustomerservicedetails> ls=tbCustomerservicedetailsMapper.selectByExample(example);
			List<ServiceDetail> ds=new ArrayList<>();
			for (TbCustomerservicedetails t : ls) {
				ServiceDetail d=new ServiceDetail();
				d.setId(t.getServicedetailId());
				d.setDetail(t.getContent());
				d.setTime(t.getTime());
				ds.add(d);
			}
			if(ds.size()!=0){
				c.setServiceDetails(ds);
			}
			map.put("code", BaseConstant.appUserSuccessStatus);
			map.put("msg", "获取成功");
			map.put("extra",null);
			map.put("resultMap", c);		
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

	
	@Override
	public Map<Object, Object> getUserDetail(User user) {
		//返回前端map
	    Map<Object,Object> map = new HashMap<Object,Object>();
		try {
			User u = appUserMapper.getUserDetail(user.getUser_id());
			u.setOwned_devices_count(deviceMapper.findUserListByUserId(user.getUser_id()).size());
			map.put("code", BaseConstant.appUserSuccessStatus);
			map.put("msg", "获取成功");
			map.put("extra",null);
			map.put("resultMap", u);
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

	@Override
	public Map<Object, Object> applyCustomer(Customerservice customerservice) {
		//返回前端map
	    Map<Object,Object> map = new HashMap<Object,Object>();
		try {
			//验证是否有进行中的售后
			int id = customerservice.getCustomer_id();
			List<Customerservice> css=customerserviceMapper.getCustomerListByDevice(customerservice.getEquipment_id());
			for (Customerservice cs : css) {
				if(!"2".equals(cs.getStatus())) {
					map.put("code", BaseConstant.appUserFaileStatus);
					map.put("msg", "此设备已经有进行中的售后");
					return map;
				}
			}
			customerservice.setStatus("0");
			customerservice.setCreateTime(Calendar.getInstance().getTime());
			customerserviceMapper.insertCustomer(customerservice);
			List<String> pics = customerservice.getPictureAdd();
			for(String pic: pics) {
				Customerpicture cp = new Customerpicture();
				cp.setCustomer_id(id);
				cp.setPictureAddress(pic);
				customerserviceMapper.insertSinglePicture(cp);
			}

			map.put("code", BaseConstant.appUserSuccessStatus);
			map.put("msg", "申请成功");
			map.put("extra",null);
			map.put("resultMap", null);
			
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

	@Override
	public Map<Object, Object> deleteCustomer(Customerservice customerservice) {
		//返回前端map
		Map<Object,Object> map = new HashMap<Object,Object>();
		try {
			customerserviceMapper.deleteCustomerDetail(customerservice);
			map.put("code", BaseConstant.appUserSuccessStatus);
			map.put("msg", "获取成功");
			map.put("extra",null);
			map.put("resultMap", null);
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

	@Override
	public Map<Object, Object> getCommonProblemList() {
		//返回前端map
	    Map<Object,Object> map = new HashMap<Object,Object>();
		try {

			Commonproblem commonproblem = commonproblemMapper.getCommonProblemList();
			map.put("code", BaseConstant.appUserSuccessStatus);
			map.put("msg", "获取成功");
			map.put("extra",null);
			map.put("resultMap", commonproblem);
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

	@Override
	public Map<Object, Object> insertFeedback(Feedback feedback) {
		//返回前端map
	    Map<Object,Object> map = new HashMap<Object,Object>();
		try {
			feedbackMapper.insertFeedback(feedback);
			map.put("code", BaseConstant.appUserSuccessStatus);
			map.put("msg", "提交成功");
			map.put("extra",null);
			map.put("resultMap", null);		
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

	@Override
	public Map<Object, Object> getEULA() {
		//返回前端map
		Map<Object,Object> map = new HashMap<Object,Object>();
		try {
			EULA eula = eulaMapper.getEULA();
			map.put("code", BaseConstant.appUserSuccessStatus);
			map.put("msg", "获取成功");
			map.put("extra",null);
			map.put("resultMap", eula);
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

	@Override
	public void saveOrUpdateTbGuarantee(TbGuarantee t) {
		if(t.getGuaranteeId()!=null){
			tbGuaranteeMapper.updateByPrimaryKeySelective(t);
		}else{
			tbGuaranteeMapper.insertSelective(t);
		}
	}

	@Override
	public void updateOrAddEula(TbEula eula) {
		if(eula.getId()!=null){
			tbEulaMapper.updateByPrimaryKey(eula);
		}else{
			tbEulaMapper.insertSelective(eula);
		}
	}

	@Override
	public TbEula getEula() {
		TbEulaExample example=new TbEulaExample();
		List<TbEula> eulas=tbEulaMapper.selectByExample(example);
		if(eulas.size()==0){return null;}
		else return eulas.get(0);
	}

	@Override
	public Integer getIndexCustomerService() {
		return customerserviceMapper.getIndexCustomerService();
	}

	@Override
	public List<Customerpicture> getPicsCustomerService(Integer customer_id) {
		return customerserviceMapper.getPics(customer_id);
	}

	@Override
	public List<TsDetail> getTsDetailS() {
		TsDetailExample example =new TsDetailExample();
		example.createCriteria().andDictIdEqualTo(6);
		return tsDetailMapper.selectByExample(example);
	}

	@Override
	public TbWcal getTbwcal(String deviceNo) {
		TbWcalExample example=new TbWcalExample();
		example.createCriteria().andDeviceNoEqualTo(deviceNo);
		List<TbWcal> ls=tbWcalMapper.selectByExample(example);
		if(ls.size()==0)return null;
		return ls.get(ls.size()-1);
	}
	
}
