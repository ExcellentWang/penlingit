package com.ontheroad.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.modoumama.service.base.impl.BaseServiceImpl;

import com.ontheroad.dao.SysLogMapper;
import com.ontheroad.entity.SysLog;
import com.ontheroad.service.SysLogService;

@Service
@Transactional
public class SysLogServiceImpl extends BaseServiceImpl<SysLog, Long> implements
		SysLogService {

	@Autowired
	public void setMapper(SysLogMapper mapper) {
		setGenericMapper(mapper);
	}
}
