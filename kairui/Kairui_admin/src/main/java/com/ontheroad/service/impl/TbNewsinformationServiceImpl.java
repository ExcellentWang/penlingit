package com.ontheroad.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ontheroad.dao.TbNewsinformationMapper;
import com.ontheroad.entity.TbNewsinformation;
import com.ontheroad.entity.TbNewsinformationExample;
import com.ontheroad.entity.TbNewsinformationExample.Criteria;
import com.ontheroad.service.TbNewsinformationService;
@Service
public class TbNewsinformationServiceImpl implements TbNewsinformationService {
	@Autowired
	private TbNewsinformationMapper tbNewsinformationMapper;
	@Override
	public void addOrUpdate(TbNewsinformation tbNewsinformation) {
		tbNewsinformation.setCreatetime(new Date());
		if(tbNewsinformation.getId()==null){
			tbNewsinformation.setStatus(1);
			tbNewsinformationMapper.insert(tbNewsinformation);
		}else{
			tbNewsinformationMapper.updateByPrimaryKeySelective(tbNewsinformation);
		}

	}


	@Override
	public void del(Integer id) {
		tbNewsinformationMapper.deleteByPrimaryKey(id);
	}

	@Override
	public TbNewsinformation getTbNewsinformationId(Integer id) {
		return tbNewsinformationMapper.selectByPrimaryKey(id);
	}


	@Override
	public List<TbNewsinformation> selectByExample(TbNewsinformation tbNewsinformation) {
		TbNewsinformationExample example = new TbNewsinformationExample();
		Criteria c = example.createCriteria();
		if (tbNewsinformation.getTitle() != null && tbNewsinformation.getTitle() != "") {
			c.andTitleEqualTo(tbNewsinformation.getTitle());
		}
		if (tbNewsinformation.getStatus()!= null ) {
			c.andStatusEqualTo(tbNewsinformation.getStatus());
		}
		if (tbNewsinformation.getCreatetime() != null) {
			c.andCreatetimeGreaterThanOrEqualTo(tbNewsinformation.getCreatetime());
		}
		return tbNewsinformationMapper.selectByExample(example);
	}

}
