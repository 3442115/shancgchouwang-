package com.atguigu.sbm.controller;


import com.atguigu.sbm.entity.Emple;
import com.atguigu.sbm.service.serviceIml.EmpleServiceIml;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Hello {
    @Autowired
    private EmpleServiceIml empleServiceIml;


    @ResponseBody
    @RequestMapping("/hello")
    public String get(){
        Emple emple = empleServiceIml.selectByPrimaryKey(1);
        System.out.println(emple.toString());
        return "sd";
    }
}
