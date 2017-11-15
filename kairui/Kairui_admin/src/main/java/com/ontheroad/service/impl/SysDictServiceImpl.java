package com.ontheroad.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.modoumama.service.base.impl.BaseServiceImpl;

import com.ontheroad.core.util.SpringUtils;
import com.ontheroad.dao.SysDetailMapper;
import com.ontheroad.dao.SysDictMapper;
import com.ontheroad.entity.SysDetail;
import com.ontheroad.entity.SysDict;
import com.ontheroad.service.SysDictService;

@Service
@Transactional
public class SysDictServiceImpl extends BaseServiceImpl<SysDict, Long> implements SysDictService {
	@Autowired
	private SysDictMapper mapper;
	@Autowired
	private SysDetailMapper sysDetailMapper;

	@Autowired
	public void setMapper(SysDictMapper mapper) {
		setGenericMapper(mapper);
	}

	@Override
	public void insert(SysDict record) {
		record.setDictStatus(1);
		record.setCreateTime(new Date());
		record.setUpdateTime(new Date());
		super.insert(record);
	}

	

	@Override
	public int removeById(Long dictId) {
		Map<String, Object> condititon = new HashMap<String, Object>();
		condititon.put("dictId", dictId);
		sysDetailMapper.deleteByCondition(condititon);
		int flag = mapper.deleteById(dictId);
		return flag;
	}
	
	public Hashtable<String, Map<String, SysDetail>>loadData(){
		SysDictMapper sDictMapper = (SysDictMapper) SpringUtils.getBean(SysDictMapper.class);
		SysDetailMapper SysDetailMapper = (SysDetailMapper) SpringUtils.getBean(SysDetailMapper.class); 
		Hashtable<String, Map<String, SysDetail>> dict = new Hashtable<String, Map<String, SysDetail>>();
		Map<String,Object> para = new HashMap<String,Object>();
		para.put("detailStatus", 1);
		List<SysDict>sDictList = sDictMapper.findModelsByCondition(null);
		List<SysDetail> SysDetailList = SysDetailMapper.findModelsByCondition(para);
		Map<String, SysDetail> dictDetailMap = null;
		for(SysDict sd:sDictList){
			String dictId = sd.getDictId().toString();
			dictDetailMap = new HashMap<String, SysDetail>();
			for (SysDetail SysDetail : SysDetailList) {
				if(dictId.equals(SysDetail.getDictId().toString())){
					dictDetailMap.put(SysDetail.getDetailName(), SysDetail);
				}
			}
			dict.put(sd.getDictName(), dictDetailMap);
		}
		return dict;
	}

	@Override
	public Map<String, String> getDetailNameMap(String dictName) {
		
		Hashtable<String, Map<String, SysDetail>> dict = loadData();
		if (dict == null || dict.size() == 0) {
			return null;
		}
		Map<String, String> params=new TreeMap<String, String>();
		Map<String, SysDetail> dictDetailMap = dict.get(dictName);
		for (Map.Entry<String, SysDetail> e : dictDetailMap.entrySet()) {  
			SysDetail d = e.getValue();
			params.put(d.getDetailName(), d.getDetailValue().toString());
		}  
		return params;
	}

	@Override
	public Map<String, String> getDetailValueMap(String dictName) {
		Hashtable<String, Map<String, SysDetail>> dict = loadData();
		if (dict == null || dict.size() == 0) {
			return null;
		}
		Map<String, String> params=new TreeMap<String, String>();
		Map<String, SysDetail> dictDetailMap = dict.get(dictName);
		for (Map.Entry<String, SysDetail> e : dictDetailMap.entrySet()) {  
			SysDetail d = e.getValue();
			params.put(d.getDetailValue().toString(),d.getDetailName());
		}  
		return params;
	}

	@Override
	public String getDetailValue(String dictName, String detailName) {
		Hashtable<String, Map<String, SysDetail>> dict = loadData();
		if (dict == null || dict.size() == 0) {
			return null;
		}
		SysDetail dictDetail = dict.get(dictName).get(detailName);
		return dictDetail.getDetailValue().toString();
	}
}
