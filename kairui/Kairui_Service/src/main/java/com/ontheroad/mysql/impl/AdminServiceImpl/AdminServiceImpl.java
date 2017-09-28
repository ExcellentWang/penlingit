package com.ontheroad.mysql.impl.AdminServiceImpl;

import com.ontheroad.core.util.MD5Encoder;
import com.ontheroad.mysql.Mapper.AdminMapper.AdminMapper;
import com.ontheroad.pojo.Admin.Admin;
import com.ontheroad.pojo.Admin.AdminLogin;
import com.ontheroad.service.AdminService.AdminService;
import org.springframework.beans.factory.annotation.Autowired;

public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public Admin login(AdminLogin login) {

        login.setPassword(MD5Encoder.encode(login.getPassword()));
        Admin admin = adminMapper.login(login);
        return admin;
    }
}
