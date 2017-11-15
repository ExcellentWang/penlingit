/*package com.ontheroad.mysql.system.service;

import java.util.Date;

import com.ontheroad.api.request.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.modoumama.service.base.impl.BaseServiceImpl;
import com.ontheroad.system.entity.SysDetail;
import com.ontheroad.system.service.SysDetailService;
import com.ontheroad.mysql.system.mapper.SysDetailMapper;
import com.ontheroad.mysql.web.context.SystemCache;

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
		SystemCache.reCacheDict();
	}
	
	@Override
	public int removeById(Long detailId) {
		int flag = super.removeById(detailId);
		SystemCache.reCacheDict();
		return flag;
	}

	@Override
	public void addNews(Request request) {
		System.out.println("添加成功");
	}
}
*/