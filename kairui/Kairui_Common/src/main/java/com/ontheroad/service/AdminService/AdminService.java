package com.ontheroad.service.AdminService;

import com.ontheroad.pojo.Admin.Admin;
import com.ontheroad.pojo.Admin.AdminLogin;
import org.springframework.remoting.service.annotation.RemoteService;

@RemoteService
public interface AdminService {
	AdminLogin login(AdminLogin login);
}
