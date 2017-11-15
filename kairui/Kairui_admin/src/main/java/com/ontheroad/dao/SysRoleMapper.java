package com.ontheroad.dao;

import java.util.List;

import org.mybatis.mapper.interfaces.GenericMapper;

import com.ontheroad.entity.SysRole;


public interface SysRoleMapper extends GenericMapper<SysRole,Long>{

	public List<SysRole>  selectRolesByUserId(Long userId);
}
