package com.ontheroad.controller;

import com.ontheroad.entity.Login;
import com.ontheroad.pojo.Admin.Admin;
import com.ontheroad.pojo.Admin.AdminLogin;
import com.ontheroad.service.AdminService.AdminService;
import com.ontheroad.service.DeviceService.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private DeviceService deviceService;
    @Autowired
    private AdminService adminService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String showLogin() {
        return "admin/login";
    }

    @RequestMapping(value = "/loginProcess", method = RequestMethod.POST)
    public String loginProcess(HttpSession session, AdminLogin login) {
        Admin admin = adminService.login(login);
        if(admin == null) {
            return "admin/login";
        }
        else {
            session.setAttribute("username", admin.getUsername());
            return "admin/index";
        }
    }



    @RequestMapping("/index")
    public String index() {
        return "admin/index";
    }

    @RequestMapping("/device/devices")
    public String devices() {
        return "admin/device/devices";
    }

    @RequestMapping(value = "/device/devicelist", method = RequestMethod.POST)
    @ResponseBody
    public Map<Object, Object> devicelist() {
        return deviceService.getAllDevices();
    }
}
