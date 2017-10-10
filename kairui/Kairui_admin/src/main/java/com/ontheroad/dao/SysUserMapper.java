package com.ontheroad.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.mapper.interfaces.GenericMapper;

import com.ontheroad.entity.SysUser;


public interface SysUserMapper extends GenericMapper<SysUser,Long>{

	List findModelsByCondition4UserRole(Map<String, Object> condition, RowBounds rowBounds);

}
