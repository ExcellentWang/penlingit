package com.ontheroad.mysql.system.mapper;

import java.util.Map;

import org.mybatis.mapper.interfaces.GenericMapper;

import com.ontheroad.system.entity.SysDetail;

public interface SysDetailMapper extends GenericMapper<SysDetail, Long> {

	public void deleteByCondition(Map<String, Object> map);

}
