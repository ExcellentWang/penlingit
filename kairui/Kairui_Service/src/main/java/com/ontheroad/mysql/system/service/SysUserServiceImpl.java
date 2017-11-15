package com.ontheroad.mysql.system.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ontheroad.core.util.MD5Encoder;
import cn.modoumama.service.base.impl.BaseServiceImpl;
import com.ontheroad.system.entity.SysUser;
import com.ontheroad.system.service.SysUserService;
import com.ontheroad.mysql.system.mapper.SysRoleUserMapper;
import com.ontheroad.mysql.system.mapper.SysUserMapper;

@Service
@Transactional
public class SysUserServiceImpl extends BaseServiceImpl<SysUser, Long>
		implements SysUserService {
	
	@Autowired
	private SysRoleUserMapper roleUsermapper;
	@Autowired
	private SysUserMapper  sysUserMapper;
	@Autowired
	public void setMapper(SysUserMapper mapper) {
		setGenericMapper(mapper);
	}
	
	@Override
	public void insert(SysUser record){
		record.setUserStatus(1);
		record.setUserType(1);
		record.setCreateTime(new Date());
		record.setUpdateTime(new Date());
		record.setUserPwd(MD5Encoder.encode(record.getUserPwd()));
		super.insert(record);
	}
	
	@Override
	public int updateById(SysUser record){
		record.setUpdateTime(new Date());
		int flag = super.updateById(record);
		return flag;
	}
	
	@Override
	public void updateUserPwd(SysUser record) {
		record.setUserPwd(MD5Encoder.encode(record.getUserPwd()));
		record.setUpdateTime(new Date());
		super.updateById(record);
	}
	
	public  void deleteUserByIds(String[] userIds){
		for(String userId : userIds){
			//删除用户
			removeById(Long.parseLong(userId));
			//删除用户和角色的绑定关系
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("userId", userId);					
			roleUsermapper.deleteByCondition(condition);
		}
		
	}

	
}
