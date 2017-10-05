package com.ontheroad.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ontheroad.core.util.Md5Util;
import com.ontheroad.dao.TsUserMapper;
import com.ontheroad.entity.TsUser;
import com.ontheroad.entity.TsUserExample;
import com.ontheroad.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private TsUserMapper tsUserMapper;

	@Override
	public TsUser login(TsUser user) {
		String password = Md5Util.makeMd5Sum(user.getUserPwd().getBytes());
		TsUserExample example=new TsUserExample();
		example.createCriteria().andUserNameEqualTo(user.getUserName()).andUserPwdEqualTo(password);
		List<TsUser> ls=tsUserMapper.selectByExample(example);
		if(ls.size()>0){
			return ls.get(0);
		}
		return null;
	}

}
