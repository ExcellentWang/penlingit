package com.ontheroad.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ontheroad.core.util.CopyUtil;
import com.ontheroad.dao.TbNewsinformationMapper;
import com.ontheroad.entity.TbNewsinformation;
import com.ontheroad.entity.TbNewsinformationExample;
import com.ontheroad.entity.TbNewsinformationExample.Criteria;
import com.ontheroad.mysql.entity.TbInformation;
import com.ontheroad.service.TbNewsinformationService;
@Service
public class TbNewsinformationServiceImpl implements TbNewsinformationService {
	@Autowired
	private TbNewsinformationMapper tbNewsinformationMapper;
	@Autowired
	private com.ontheroad.service.InformationService.InformationService informationService;
	@Override
	public TbNewsinformation addOrUpdate(TbNewsinformation tbNewsinformation) {
		tbNewsinformation.setCreatetime(new Date());
		if(tbNewsinformation.getId()==null){
			tbNewsinformation.setStatus(1);
			tbNewsinformationMapper.insert(tbNewsinformation);
			tbNewsinformation.setAddress("http://106.14.173.153:9999/view/Modal/newsmanager/newslist/show.html?id="+tbNewsinformation.getId());
			tbNewsinformationMapper.updateByPrimaryKey(tbNewsinformation);
			//插入消息公用表数据
			TbInformation info=new TbInformation();
			try {
				CopyUtil.Copy(tbNewsinformation, info);
				info.setId(null);
				info.setInformationtype(tbNewsinformation.getType());
				info.setNewsId(Long.valueOf(tbNewsinformation.getId()));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			informationService.addOrUpdateTbInformation(info);
		}else{
			tbNewsinformationMapper.updateByPrimaryKeySelective(tbNewsinformation);
			TbInformation info=new TbInformation();
			try {
				CopyUtil.Copy(tbNewsinformation, info);
				info.setNewsId(Long.valueOf(tbNewsinformation.getId()));
				info.setInformationtype(tbNewsinformation.getType());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			informationService.addOrUpdateTbInformation(info);
		}
		return tbNewsinformation;
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
	public List<TbNewsinformation> selectByExample(TbNewsinformation tbNewsinformation,Date createtime2) {
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
		if (createtime2 != null) {
			c.andCreatetimeLessThanOrEqualTo(createtime2);
		}
		c.andTypeEqualTo(tbNewsinformation.getType());
		return tbNewsinformationMapper.selectByExample(example);
	}

}
