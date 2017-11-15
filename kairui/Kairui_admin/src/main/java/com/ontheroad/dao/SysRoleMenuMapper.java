package com.ontheroad.dao;

import java.util.Map;

import org.mybatis.mapper.interfaces.GenericMapper;

import com.ontheroad.entity.SysRoleMenu;


public interface SysRoleMenuMapper extends GenericMapper<SysRoleMenu,Long>{
	
	public int deleteByCondition(Map<String, Object> condition);

}
