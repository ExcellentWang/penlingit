package com.ontheroad.mysql.system.mapper;

import java.util.Map;

import org.mybatis.mapper.interfaces.GenericMapper;

import com.ontheroad.system.entity.SysRoleUser;

public interface SysRoleUserMapper extends GenericMapper<SysRoleUser,Long>{
	
	public int deleteByCondition(Map<String, Object> condition);

}
