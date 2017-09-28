package com.ontheroad.mysql.system.mapper;

import java.util.List;
import java.util.Map;

import org.mybatis.mapper.interfaces.GenericMapper;

import com.ontheroad.system.entity.SysMenu;

public interface SysMenuMapper extends GenericMapper<SysMenu, Long> {
	
	public int deleteByCondition(Map<String, Object> condition);
	
	public List<SysMenu> getAllMenus(Long userId);

}
