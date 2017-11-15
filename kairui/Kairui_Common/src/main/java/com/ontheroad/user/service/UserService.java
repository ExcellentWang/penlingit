package com.ontheroad.user.service;

import cn.modoumama.service.base.BaseService;
import com.ontheroad.api.request.Request;
import com.ontheroad.api.request.UserRequest;
import com.ontheroad.api.response.Response;
import com.ontheroad.core.annotation.ValidateGroup;
import com.ontheroad.user.model.UserModel;
import org.springframework.remoting.service.annotation.RemoteService;

import static com.ontheroad.api.request.UserRequest.*;

/**
 * 用户模块业务
 * <p>
 * 与密码有关的接口都要使用https协议
 *
 * @author kedong
 * @version 1.0
 * @Create Date 2017年06月25日
 */
@RemoteService
public interface UserService extends BaseService<UserModel, Integer> {

    /**
     * 注册 HTTPS
     *
     * @param request
     * @return
     * @throws Exception
     */
    @ValidateGroup(groups = regist.class)
    Response regist(UserRequest request) throws Exception;

    /**
     * 登录 HTTPS
     *
     * @param request
     * @return
     */
    @ValidateGroup(groups = login.class)
    Response login(UserRequest request) throws Exception;

    /**
     * 获取验证码
     *
     * @param request
     * @return
     */
    @ValidateGroup(groups = getVerification.class)
    Response getVerification(UserRequest request) throws Exception;

    /**
     * 修改密码 HTTPS
     *
     * @param request
     * @return
     * @throws Exception
     */
    @ValidateGroup(groups = resetPassword.class)
    Response resetPassword(UserRequest request) throws Exception;

    /**
     * 忘记密码 HTTPS
     *
     * @param request
     * @return
     * @throws Exception
     */
    @ValidateGroup(groups = forgotPassword.class)
    Response forgotPassword(UserRequest request) throws Exception;

    /**
     * 退出登录
     *
     * @param request
     * @return
     * @throws Exception
     */
    Response logout(UserRequest request) throws Exception;

    /**
     * 修改用户信息
     *
     * @param request
     * @return
     * @throws Exception
     */
    Response editUserInfo(UserRequest request) throws Exception;

    /**
     * APP 我 模块主页
     *
     * @param request
     * @return
     * @throws Exception
     */
    Response myIndex(Request request) throws Exception;


    /**
     * 用户主页
     *
     * @param request
     * @return
     * @throws Exception
     */
    @ValidateGroup(groups = friendIndex.class)
    Response friendIndex(UserRequest request) throws Exception;
}
