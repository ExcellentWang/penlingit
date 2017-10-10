package com.ontheroad.service.impl;

import java.util.Date;

import com.ontheroad.api.request.Request;
import com.ontheroad.dao.SysDetailMapper;
import com.ontheroad.entity.SysDetail;
import com.ontheroad.service.SysDetailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.modoumama.service.base.impl.BaseServiceImpl;

@Service
@Transactional
public class SysDetailServiceImpl extends BaseServiceImpl<SysDetail, Long>
		implements SysDetailService {
	@Autowired
	public void setMapper(SysDetailMapper mapper) {
		super.setGenericMapper(mapper);
	}
	
	@Override
	public void insert(SysDetail record) {
		record.setCreateTime(new Date());
		record.setUpdateTime(new Date());
		record.setDetailStatus(1);
		super.insert(record);
	}
	
	@Override
	public int removeById(Long detailId) {
		int flag = super.removeById(detailId);
		return flag;
	}

	@Override
	public void addNews(Request request) {
		System.out.println("添加成功");
	}
}
