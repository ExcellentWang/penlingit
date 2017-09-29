package com.ontheroad.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by kedong on 2017/6/23 0023.
 */
@Controller
public class index {

    @RequestMapping("/test")
    public String home() {
        return "qf";
    }
}
