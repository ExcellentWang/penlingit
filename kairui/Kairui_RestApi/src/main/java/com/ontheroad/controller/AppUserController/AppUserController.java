package com.ontheroad.controller.AppUserController;
import java.io.*;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.http.HttpServletRequest;

import com.ontheroad.pojo.user.*;
import com.ontheroad.service.AppService.QiniuService;
import com.ontheroad.service.DeviceService.DeviceService;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ontheroad.core.util.UploadUtil;
import com.ontheroad.mysql.entity.TbGuarantee;
import com.ontheroad.pojo.Constant.BaseConstant;
import com.ontheroad.pojo.TerminalDevice.TerminalDevice;
import com.ontheroad.service.AppService.AppUserService;
import com.ontheroad.tokenUtil.EhcacheUtil;
import com.ontheroad.utils.StringUtilsCommon;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

@RestController
@RequestMapping("/app/user")
public class AppUserController extends BaseConstant{
    
	
	@Autowired
    private AppUserService appUserService;
	
	@Autowired
	private QiniuService qiniuService;
	@Autowired
	private  DeviceService deviceService;
	/*用户注册 2017.7.19
	 * 
	 * 
	 * 
	 */
	@RequestMapping(value = "/appUserRegister", method = RequestMethod.POST)
    public Map<Object,Object> regist(User user,String code) {
		//返回前端map集合
	    Map<Object,Object> map = new HashMap<Object,Object>(); 
        try {	        	
        	return appUserService.findUserByPhone(user,code);      	        	        	
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", BaseConstant.appUserErrorStatus);
    		map.put("msg", "服务器异常");
    		map.put("extra",null);
    		map.put("resultMap", null);
            return map;
        }
    }
	
	
	/*发送短信获取验证码
	 * 
	 * 
	 */

	@RequestMapping(value = "/appUserGetVerificationCode")
    public Map<Object,Object> appUserGetVerificationCode(String phone) {
		//返回前端map
	    Map<Object,Object> map = new HashMap<Object,Object>();
		try {					 								
			 map= appUserService.appUserGetVerificationCode(phone);	
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
	 * 用户登录 2017.7.19
	 * 
	 * @param user
	 * @return
	 */
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/appUserLogin")
    public Map<Object,Object> login(User user) {
		//返回前端map
	    Map<Object,Object> map = new HashMap<Object,Object>();
	    Map<Object,Object> serviceMap = new HashMap<Object,Object>(); //service返回的map
	    Map<Object,Object> resultMap = new HashMap<Object,Object>();  //service的map	中的map   
	    Map<Object,Object> appResultMap = new HashMap<Object,Object>();
		try {	
			serviceMap = appUserService.appUserLogin(user);
			resultMap  = (Map<Object, Object>) serviceMap.get("resultMap");
			if(resultMap == null || resultMap.size() == 0) {
				return serviceMap;
			}
			//获取到登录用户的信息
			User u =(User) resultMap.get("user");
			
			//第一次通过userid验证token是否存在;
			String oldToken = (String) EhcacheUtil.getInstance().get("token", u.getUser_id()+"");	
			
			if (StringUtils.isNotBlank(oldToken)) {
	            // 存在token，说明用户处于登录状态中
	            // 解除之前的token与userId的对应关系
				EhcacheUtil.getInstance().remove("token", oldToken);
				
				String oldId = (String) EhcacheUtil.getInstance().get("token", oldToken);	
				if (StringUtils.isNotBlank(oldId)) {
					
					EhcacheUtil.getInstance().remove("token", oldId);
					
				}
				
	        }
			//生成token
	        String newToken = StringUtilsCommon.getToken();
	        
	        EhcacheUtil.getInstance().put("token", newToken, u.getUser_id()+""); //绑定新token和id的关系
	        EhcacheUtil.getInstance().put("token", u.getUser_id()+"",newToken); //绑定id和新token的关系
	        
	        appResultMap.put("user", u);
	        appResultMap.put("token", newToken);
	          
	        map.put("code", BaseConstant.appUserSuccessStatus);
    		map.put("msg", "登陆成功");
    		map.put("extra",null);
    		map.put("resultMap", appResultMap);  
	        
	        
			return 	map;
			
		} catch (Exception e) {
		   e.printStackTrace();
           map.put("code", BaseConstant.appUserErrorStatus);
	   	   map.put("msg", "服务器异常");
	   	   map.put("extra",null);
	   	   map.put("resultMap", null);
           return map;

		}
		
	}
	
	
	public URL test(){
		String xmlPath = "/spring/ehcache.xml";
		URL url = this.getClass().getResource(xmlPath);
		System.out.println(this.getClass());
		System.out.println(this.getClass().getClassLoader());
		System.out.println(this.getClass().getResource(xmlPath));
		System.out.println(this.getClass().getResourceAsStream(xmlPath));
		return url;
	}

	/*忘记密码设置新的密码
	 * 
	 * 
	 */
	@RequestMapping(value = "/appUserForgetPassword", method = RequestMethod.POST)
    public Map<Object,Object> appUserForgetPassword(String phone,String newPassword,String code) {
		//返回前端map
	    Map<Object,Object> map = new HashMap<Object,Object>();	    
		try {																					
			return appUserService.forgetPassword(phone,newPassword,code);	
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
	 * 修改密码
	 * 
	 */
	@RequestMapping(value = "/appUserUpdatePassword", method = RequestMethod.POST)
    public Map<Object,Object> appUserUpdatePassword(User user,String newPassword,String oldPassword) {
		//返回前端map
	    Map<Object,Object> map = new HashMap<Object,Object>();	    
		try {																					
			return appUserService.updatePassword(user,newPassword,oldPassword);	
		} catch (Exception e) {
		   e.printStackTrace();
           map.put("code", BaseConstant.appUserErrorStatus);
	   	   map.put("msg", "服务器异常");
	   	   map.put("extra",null);
	   	   map.put("resultMap", null);
           return map;
		}	
	}
	
	/*更换手机号
	 * 
	 * 
	 */
	@RequestMapping(value = "/appUserUpdatePhone", method = RequestMethod.POST)
    public Map<Object,Object> appUserUpdatePhone(User user,String newPhone, String code) {
		//返回前端map
	    Map<Object,Object> map = new HashMap<Object,Object>();
		try {																					
			return appUserService.updatePhone(user,newPhone, code);
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
	 * 修改用户资料
	 * 
	 */
	@RequestMapping(value = "/appUserUpdateData")
    public Map<Object,Object> appUserUpdateData(User user, @RequestParam("file1") CommonsMultipartFile file,HttpServletRequest request) {
		//返回前端map
	    Map<Object,Object> map = new HashMap<Object,Object>();
	    String headPortrait = null;
		try {
			headPortrait=UploadUtil.save(file, request);
			if(headPortrait != null && !headPortrait.equals("")){
				user.setHeadPortrait(headPortrait);
			}
			return appUserService.appUserUpdateData(user);
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
	 * 保修卡页面信息获取
	 * 
	 */
	@RequestMapping(value = "/getGuaranteeDetail", method = RequestMethod.POST)
    public Map<Object,Object> getGuaranteeDetail(Guarantee guarantee) {
		//返回前端map
	    Map<Object,Object> map = new HashMap<Object,Object>();	    
		try {
			return appUserService.getGuaranteeDetail(guarantee);
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
	 * 提交保修卡
	 * 
	 * 
	 * 
	 */
	@RequestMapping(value = "/submitGuarantee")
    public Map<Object,Object> subGuarantee(MultipartHttpServletRequest request,TbGuarantee tbGuarantee) {
		//返回前端map
	    Map<Object,Object> map = new HashMap<Object,Object>();
		String sImg="";
		Integer guarantee_id=null;
		Integer user_id=null;
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try {
			MultipartFile file = request.getFile("file1");
			if(file != null){
				//获取本地文件地址
		        String path = request.getSession().getServletContext().getRealPath("view/upload");  
		        String fileName = file.getOriginalFilename();  
		        File targetFile = new File(path, fileName);  
		        if(!targetFile.exists()){  
		            targetFile.mkdirs();  
		        }  
				file.transferTo(targetFile);
				  sImg= "http://"+"106.14.173.153"+":9999"+"/Kairui_RestApi/view/upload/"+fileName;
			}
			Integer equipment_id=Integer.parseInt(request.getParameter("equipment_id"));
			if(request.getParameter("guarantee_id")!=null){
				guarantee_id =Integer.parseInt(request.getParameter("guarantee_id"));
			}
			if(request.getParameter("user_id")!=null){
				user_id=Integer.parseInt(request.getParameter("user_id"));
			}
			
			//保修期
			TerminalDevice t=new TerminalDevice();
			t.setEquipment_id(equipment_id);
			Integer period=deviceService.getDeviceDetail(equipment_id).getPeriod();
			tbGuarantee.setEquipmentId(equipment_id);
			tbGuarantee.setUserId(user_id);
			if(request.getParameter("buytime")!=null){
				tbGuarantee.setBuytime(format.parse(request.getParameter("buytime")));
			}
			tbGuarantee.setInvoice(sImg);
			tbGuarantee.setStatus(1);
			tbGuarantee.setGuaranteeId(guarantee_id);
			tbGuarantee.setGuaranteetime(String.valueOf(period));
			tbGuarantee.setSubmitTime(new Date());
			appUserService.saveOrUpdateTbGuarantee(tbGuarantee);
			map.put("code", BaseConstant.appUserSuccessStatus);
			map.put("msg", "成功");
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

	
	
	/*
	 * 获取退换修列表
	 * 
	 */
	@RequestMapping(value = "/getCustomerList", method = RequestMethod.POST)
    public Map<Object,Object> getCustomerList(Customerservice customerservice) {
		//返回前端map
	    Map<Object,Object> map = new HashMap<Object,Object>();	    
		try {																					
			return appUserService.getCustomerList(customerservice);	
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
	 * 获取报修详情
	 * 
	 * 
	 */
	@RequestMapping(value = "/getCustomerDetail")
    public Map<Object,Object> getCustomerDetail(Customerservice customerservice) {
		//返回前端map
	    Map<Object,Object> map = new HashMap<Object,Object>();	    
		try {																					
			return appUserService.getCustomerDetail(customerservice);	
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
	 * 获取单个用户资料
	 * 
	 */
	@RequestMapping(value = "/getUserDetail")
    public Map<Object,Object> getUserDetail(User user) {
		//返回前端map
	    Map<Object,Object> map = new HashMap<Object,Object>();	    
		try {																					
			return appUserService.getUserDetail(user);	
		} catch (Exception e) {
		   e.printStackTrace();
           map.put("code", BaseConstant.appUserErrorStatus);
	   	   map.put("msg", "服务器异常");
	   	   map.put("extra",null);
	   	   map.put("resultMap", null);
           return map;
		}	
	}

	
	/*2017.7.25*/
	
	/*
	 * 申请售后
	 * 
	 */
	
	@RequestMapping(value = "/applyCustomer ")
    public Map<Object,Object> applyCustomer(MultipartHttpServletRequest request) {
		//返回前端map
	    Map<Object,Object> map = new HashMap<Object,Object>();
	    Customerservice customerservice = new Customerservice();
		List<String> images = new ArrayList<>();
		try {
			//图片上传
			Iterator<String> it = request.getFileNames();
			while(it.hasNext()) {
				MultipartFile file = request.getFile(it.next().toString());
				if(file != null){
					//获取本地文件地址
			        String path = request.getSession().getServletContext().getRealPath("view/upload");  
			        String fileName = file.getOriginalFilename();  
			        File targetFile = new File(path, fileName);  
			        if(!targetFile.exists()){  
			            targetFile.mkdirs();  
			        }  
					file.transferTo(targetFile);
					 String sImg= "http://"+"106.14.173.153"+":9999"+"/Kairui_RestApi/view/upload/"+fileName;
					images.add(sImg);
				}
			}
			customerservice.setPictureAdd(images);
			customerservice.setEquipment_id(Integer.parseInt(request.getParameter("equipment_id")));
			customerservice.setUser_id(Integer.parseInt(request.getParameter("user_id")));
			customerservice.setEquipmentNum(request.getParameter("equipmentNum"));
			customerservice.setRepairType(request.getParameter("repairType"));
			customerservice.setPhenomenon(request.getParameter("phenomenon"));
			customerservice.setAddress(request.getParameter("address"));
			customerservice.setPhone(request.getParameter("phone"));
			customerservice.setArea(request.getParameter("area"));

			DateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm");
			customerservice.setAppointmentTime(format.parse(request.getParameter("appointmentTime")));

			return appUserService.applyCustomer(customerservice);	
		} catch (Exception e) {
		   e.printStackTrace();
           map.put("code", BaseConstant.appUserErrorStatus);
	   	   map.put("msg", "服务器异常");
	   	   map.put("extra",null);
	   	   map.put("resultMap", null);
           return map;
		}	
	}

	@RequestMapping(value = "/deleteCustomer", method = RequestMethod.POST)
	public Map<Object, Object> deleteCustomer(Customerservice customerservice) {
		//返回前端map
		Map<Object,Object> map = new HashMap<Object,Object>();
		try {
			return appUserService.deleteCustomer(customerservice);
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
	 * 获取常见问题列表
	 */
	@RequestMapping(value = "/getCommonProblemList ", method = RequestMethod.POST)
    public Map<Object,Object> getCommonProblemList() {
		//返回前端map
	    Map<Object,Object> map = new HashMap<Object,Object>();	    
		try {																					
			return appUserService.getCommonProblemList();
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
	 * 意见反馈
	 * 
	 */
	@RequestMapping(value = "/insertFeedback ", method = RequestMethod.POST)
    public Map<Object,Object> insertFeedback(Feedback feedback) {
		//返回前端map
	    Map<Object,Object> map = new HashMap<Object,Object>();	    
		try {																					
			return appUserService.insertFeedback(feedback);	
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
	 * 获取用户协议
	 */
	@RequestMapping(value = "/getEULA ", method = RequestMethod.POST)
	public Map<Object,Object> getEULA() {
		//返回前端map
		Map<Object,Object> map = new HashMap<Object,Object>();
		try {
			return appUserService.getEULA();
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
