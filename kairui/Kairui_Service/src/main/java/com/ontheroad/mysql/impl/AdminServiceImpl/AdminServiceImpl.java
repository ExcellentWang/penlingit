package com.ontheroad.mysql.impl.AdminServiceImpl;

import com.ontheroad.core.util.MD5Encoder;
import com.ontheroad.mysql.Mapper.AdminMapper.AdminMapper;
import com.ontheroad.pojo.Admin.Admin;
import com.ontheroad.pojo.Admin.AdminLogin;
import com.ontheroad.service.AdminService.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
@Transactional
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public AdminLogin login(AdminLogin login) {
    	String pwd=MD5Encoder.encode(login.getPassword());
        login.setPassword(pwd.toLowerCase());
        AdminLogin admin = adminMapper.login(login);
        return admin;
    }
}
