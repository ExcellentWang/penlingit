package com.ontheroad.system.service;

import com.ontheroad.system.entity.SysUser;
import org.springframework.remoting.service.annotation.RemoteService;

import cn.modoumama.service.base.BaseService;

@RemoteService
public interface SysUserService extends BaseService<SysUser,Long>{
	
	public void updateUserPwd(SysUser record);
	
	public  void deleteUserByIds(String[] userIds);

}
