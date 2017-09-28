package com.ontheroad.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by kedong on 2017/6/28 0028.
 */
@RequestMapping("map/")
@Controller
public class MapController {

    @RequestMapping("index")
    public String index(){
        return "map/index";
    }
}
