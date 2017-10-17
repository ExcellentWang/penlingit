package com.ontheroad.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ontheroad.dao.TbNewsinformationMapper;
import com.ontheroad.entity.TbNewsinformation;
import com.ontheroad.entity.TbNewsinformationExample;
import com.ontheroad.service.TbNewsinformationService;
@Service
public class TbNewsinformationServiceImpl implements TbNewsinformationService {
	@Autowired
	private TbNewsinformationMapper tbNewsinformationMapper;
	@Override
	public void addOrUpdate(TbNewsinformation tbNewsinformation) {
		tbNewsinformation.setCreatetime(new Date());
		if(tbNewsinformation.getId()==null){
			tbNewsinformationMapper.insert(tbNewsinformation);
		}else{
			tbNewsinformationMapper.updateByPrimaryKeySelective(tbNewsinformation);
		}

	}

	@Override
	public List<TbNewsinformation> getList() {
		TbNewsinformationExample example=new TbNewsinformationExample();
		return tbNewsinformationMapper.selectByExample(example);
	}

	@Override
	public void del(Integer id) {
		tbNewsinformationMapper.deleteByPrimaryKey(id);
	}

	@Override
	public TbNewsinformation getTbNewsinformationId(Integer id) {
		return tbNewsinformationMapper.selectByPrimaryKey(id);
	}

}
