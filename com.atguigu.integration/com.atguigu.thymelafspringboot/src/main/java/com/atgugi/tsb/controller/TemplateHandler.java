package com.atgugi.tsb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.Arrays;

@Controller
public class TemplateHandler {

    @RequestMapping("/hello")
    public String get(ModelMap modelMap, HttpSession session){
        modelMap.addAttribute("modelmap","123456789");

        session.setAttribute("asdas","asdasdasasc");
        modelMap.addAttribute("list", Arrays.asList("AAA","BBB","CCC","CCCCDDD"));

    return "hello";
  }

}
