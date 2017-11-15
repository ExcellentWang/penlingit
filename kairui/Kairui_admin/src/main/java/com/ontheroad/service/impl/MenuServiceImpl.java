package com.ontheroad.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ontheroad.dao.TsMenuMapper;
import com.ontheroad.entity.TsMenu;
import com.ontheroad.service.MenuService;

@Service
public class MenuServiceImpl implements MenuService{
	@Autowired
	private TsMenuMapper tsMenuMapper;
	@Override
	public List<TsMenu> getMenusUser(Integer userId) {
		return tsMenuMapper.getMenusUser(userId);
	}
	
}
