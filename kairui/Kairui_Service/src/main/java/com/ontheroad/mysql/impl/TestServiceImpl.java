package com.ontheroad.mysql.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ontheroad.api.request.UserRequest;
import com.ontheroad.api.response.Response;
import com.ontheroad.mysql.Mapper.TestMapper;
import com.ontheroad.pojo.TestUser;
import com.ontheroad.service.TestService;




@Service
@Transactional
public class TestServiceImpl implements TestService{
	@Autowired
    private TestMapper testMapper;
	
	
	
	@Override
	public Response regist(UserRequest request) {
		
		return null;
	}

	@Override
	public String insert(TestUser user) {
		try{
			testMapper.insertUser(user);
			return "yes";
		}catch(Exception e){
			e.printStackTrace();
			return "no";
		}
	}
}
