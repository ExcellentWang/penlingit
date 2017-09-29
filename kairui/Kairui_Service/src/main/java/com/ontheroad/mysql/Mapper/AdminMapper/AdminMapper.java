package com.ontheroad.mysql.Mapper.AdminMapper;

import com.ontheroad.pojo.Admin.Admin;
import com.ontheroad.pojo.Admin.AdminLogin;

public interface AdminMapper {
	AdminLogin login(AdminLogin login);
}
