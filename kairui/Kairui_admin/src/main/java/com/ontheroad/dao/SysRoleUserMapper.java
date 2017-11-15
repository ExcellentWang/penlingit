package com.ontheroad.dao;

import java.util.Map;

import org.mybatis.mapper.interfaces.GenericMapper;

import com.ontheroad.entity.SysRoleUser;


public interface SysRoleUserMapper extends GenericMapper<SysRoleUser,Long>{
	
	public int deleteByCondition(Map<String, Object> condition);

}
