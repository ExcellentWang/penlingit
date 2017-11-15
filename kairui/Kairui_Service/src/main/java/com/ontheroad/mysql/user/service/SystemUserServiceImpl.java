package com.ontheroad.mysql.user.service;

import cn.modoumama.service.base.impl.BaseServiceImpl;
import com.ontheroad.core.util.MD5Encoder;
import com.ontheroad.mysql.user.mapper.UserMapper;
import com.ontheroad.user.model.UserModel;
import com.ontheroad.user.service.SystemUserService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by kedong on 2017/6/27 0027.
 */
public class SystemUserServiceImpl extends BaseServiceImpl<UserModel, Integer> implements SystemUserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    public void setMapper(UserMapper userMapper) {
        setGenericMapper(userMapper);
    }

    @Override
    public void deleteUserByIds(String[] userIds) {
        for (String userId : userIds) {
            //删除用户
            removeById(Integer.parseInt(userId));
        }
    }

    @Override
    public void updateUserPwd(UserModel record) {
        record.setPassword(MD5Encoder.encode(record.getPassword()));
        super.updateById(record);
    }

}
