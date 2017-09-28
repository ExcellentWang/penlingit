package com.ontheroad.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ontheroad.pojo.TestUser;
import com.ontheroad.service.TestService;



@RestController
@RequestMapping("/test")
public class TestController extends BaseController{
	
	@Autowired
    private TestService testService;
	
	 @RequestMapping(value = "qftest", method = RequestMethod.POST)
	    public Map<Object,Object> regist(TestUser user) {
		    Map<Object,Object> map = new HashMap<Object,Object>();
	        try {	        	
	        	map.put("flag", true);
	        	
	        	String msg = testService.insert(user);
	        	map.put("res", msg);
	            return map;
	        } catch (Exception e) {
	            e.printStackTrace();
	            map.put("res", "no");
	            return map;
	        }
	    }
	
	
	
}
