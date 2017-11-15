package com.ontheroad.mysql.system.mapper;

import java.util.List;

import org.mybatis.mapper.interfaces.GenericMapper;

import com.ontheroad.system.entity.SysRole;

public interface SysRoleMapper extends GenericMapper<SysRole,Long>{

	public List<SysRole>  selectRolesByUserId(Long userId);
}
