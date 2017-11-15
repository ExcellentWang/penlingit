package com.ontheroad.user.service;

import cn.modoumama.service.base.BaseService;
import com.ontheroad.user.model.UserModel;
import org.springframework.remoting.service.annotation.RemoteService;

@RemoteService
public interface SystemUserService extends BaseService<UserModel, Integer> {

    void deleteUserByIds(String[] userIds);

    void updateUserPwd(UserModel sUser);
}
