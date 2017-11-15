package com.ontheroad.service;

import org.springframework.remoting.service.annotation.RemoteService;

import com.ontheroad.api.request.UserRequest;
import com.ontheroad.api.response.Response;
import com.ontheroad.pojo.TestUser;



@RemoteService
public interface TestService {

	Response regist(UserRequest request);

	String insert(TestUser user);

}
