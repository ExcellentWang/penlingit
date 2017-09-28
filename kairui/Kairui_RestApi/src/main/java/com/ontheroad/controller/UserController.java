package com.ontheroad.controller;

import com.ontheroad.api.request.UserRequest;
import com.ontheroad.api.response.Response;
import com.ontheroad.core.annotation.Token;
import com.ontheroad.core.annotation.ValidateGroup;
import com.ontheroad.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by kedong on 2017/6/20 0020.
 */
@RestController
@RequestMapping("user/")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;


    @ValidateGroup(groups = UserRequest.regist.class)
    @RequestMapping(value = "regist", method = RequestMethod.POST)
    public Response regist(UserRequest request) {
        try {
            return userService.regist(request);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.ERROR("服务异常");
        }
    }

    @Token(needToken = false)
    @ValidateGroup(groups = UserRequest.login.class)
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public Response login(UserRequest request) {
        try {
            return userService.regist(request);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.ERROR("服务异常");
        }
    }
}
