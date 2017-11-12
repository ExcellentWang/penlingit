package com.ontheroad.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ontheroad.dao.LunboMapper;
import com.ontheroad.entity.Lunbo;
import com.ontheroad.entity.LunboExample;
import com.ontheroad.service.LunboService;
@Service
public class LunboServiceImpl implements LunboService {
	@Autowired
	private LunboMapper lunboMapper;
	@Override
	public void addOrUpdate(Lunbo lunbo) {
		lunbo.setCtime(new Date());
		if(lunbo.getId()==null){
			lunboMapper.insert(lunbo);
			lunbo.setAddress("https://sec.ldzhn.com/view/Modal/newsmanager/lunbomanager/show.html?id="+lunbo.getId());
			lunboMapper.updateByPrimaryKey(lunbo);
		}else{
			lunboMapper.updateByPrimaryKey(lunbo);
		}
	}

	@Override
	public List<Lunbo> getList() {
		LunboExample example=new LunboExample();
		example.setOrderByClause("lunbo_order");
		return lunboMapper.selectByExample(example);
	}

	@Override
	public void del(Long id) {
		lunboMapper.deleteByPrimaryKey(id);
	}

	@Override
	public Lunbo getLunboId(Long id) {
		return lunboMapper.selectByPrimaryKey(id);
	}

}
