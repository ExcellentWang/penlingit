package com.ontheroad.service;

import java.util.List;

import com.ontheroad.entity.Lunbo;
import com.ontheroad.entity.TbNewsinformation;

public interface TbNewsinformationService {
	void addOrUpdate(TbNewsinformation tbNewsinformation);
	List<TbNewsinformation> getList();
	void del(Integer id);
	TbNewsinformation getTbNewsinformationId(Integer id);
}
