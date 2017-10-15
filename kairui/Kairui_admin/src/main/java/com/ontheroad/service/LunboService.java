package com.ontheroad.service;

import java.util.List;

import com.ontheroad.entity.Lunbo;

/**
 * 轮播
 * @author Administrator
 *
 */
public interface LunboService {
	void addOrUpdate(Lunbo lunbo);
	List<Lunbo> getList();
	void del(Long id);
	Lunbo getLunboId(Long id);
}
