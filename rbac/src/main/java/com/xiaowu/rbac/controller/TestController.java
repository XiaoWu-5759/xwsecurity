package com.xiaowu.rbac.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {

    @RequestMapping("/test")
    public String test(){
        int i = 1/0;
        return "test";
    }

//    @RequestMapping("/error")
//    public void error(){
//        System.out.println("error");
//    }
}
