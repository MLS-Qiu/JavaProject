package com.mls.frontend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Test {
    @GetMapping(value = "/hello",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String hello(){
        return "你好！";
    }
}
