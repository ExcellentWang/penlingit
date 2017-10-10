package com.ontheroad.service;


import com.ontheroad.entity.SysUser;

import cn.modoumama.page.PageObject;
import cn.modoumama.service.base.BaseService;

public interface SysManageUserService extends BaseService<SysUser,Long>{
	
	public void updateUserPwd(SysUser record);
	
	public  void deleteUserByIds(String[] userIds);
	
	public cn.modoumama.page.GridDataModel getGridDataModelByCondition4UserRole(PageObject po);

}
