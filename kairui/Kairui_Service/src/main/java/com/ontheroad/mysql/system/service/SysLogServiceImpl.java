package com.ontheroad.mysql.system.service;

import com.ontheroad.mysql.system.mapper.SysLogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.modoumama.service.base.impl.BaseServiceImpl;
import com.ontheroad.system.entity.SysLog;
import com.ontheroad.system.service.SysLogService;

@Service
@Transactional
public class SysLogServiceImpl extends BaseServiceImpl<SysLog, Long> implements
		SysLogService {

	@Autowired
	public void setMapper(SysLogMapper mapper) {
		setGenericMapper(mapper);
	}
}
