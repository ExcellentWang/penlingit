package com.ontheroad.controller;


import com.ontheroad.pojo.Constant.BaseConstant;
import com.ontheroad.service.DeviceService.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/log")
public class LogController {
    @Autowired
    private DeviceService deviceService;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public Map<Object,Object> index() {
        //返回前端map
        Map<Object,Object> map = new HashMap<Object,Object>();
        try {
            return deviceService.getDeviceLogs();
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", BaseConstant.appUserErrorStatus);
            map.put("msg", "服务器异常");
            map.put("extra",null);
            map.put("resultMap", null);
            return map;
        }
    }

    @RequestMapping(value = "/sessions", method = RequestMethod.GET)
    public Map<Object,Object> sessions() {
        //返回前端map
        Map<Object,Object> map = new HashMap<Object,Object>();
        try {
            return deviceService.getSessions();
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", BaseConstant.appUserErrorStatus);
            map.put("msg", "服务器异常");
            map.put("extra",null);
            map.put("resultMap", null);
            return map;
        }
    }
}
