package com.yiyue.lesson.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TestController {

    @GetMapping("/user/filter")
    public String helloword(){
        return "我被myfilter监控了";
    }

    @GetMapping("/home/open/info")
    public String getHome(){
        return "欢迎访问首页";
    }

    @GetMapping("/open/unLogin")
    public String login(){
        return "尚未登录请登录";
    }
}
