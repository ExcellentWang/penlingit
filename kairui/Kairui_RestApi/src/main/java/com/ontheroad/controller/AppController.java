package com.ontheroad.controller;

import com.ontheroad.pojo.Constant.BaseConstant;
import com.ontheroad.service.AppService.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/app")
public class AppController {

    @Autowired
    AppService appService;

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Map<Object,Object> update() {
        Map<Object,Object> map = new HashMap<Object,Object>();
        try {
            return appService.update();
        } catch (Exception ex) {
            ex.printStackTrace();
            map.put("code", BaseConstant.appUserErrorStatus);
            map.put("msg", "服务器异常");
            map.put("extra", null);
            map.put("resultMap", null);
            return map;
        }
    }
}
