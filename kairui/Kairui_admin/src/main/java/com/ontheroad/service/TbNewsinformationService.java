package com.ontheroad.service;

import java.util.Date;
import java.util.List;

import com.ontheroad.entity.Lunbo;
import com.ontheroad.entity.TbNewsinformation;

public interface TbNewsinformationService {
	TbNewsinformation addOrUpdate(TbNewsinformation tbNewsinformation);
	List<TbNewsinformation> selectByExample(TbNewsinformation tbNewsinformation,Date createtime2);
	void del(Integer id);
	TbNewsinformation getTbNewsinformationId(Integer id);
}
