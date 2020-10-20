package com.atguigu.crowd.mvc.handler;

import com.atguigu.crowd.CrowdUtil;
import com.atguigu.crowd.entity.Admin;
import com.atguigu.crowd.entity.ParamData;
import com.atguigu.crowd.entity.Student;
import com.atguigu.crowd.service.AdminService;
import com.atguigu.crowd.ResultEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

    @Controller
    public class TestHandler {
    private Logger logger = LoggerFactory.getLogger(TestHandler.class);
    @Autowired
    private AdminService adminService;

     @RequestMapping(value = "/test/ssm.html")
    public String  getAllInfo(ModelMap model,HttpServletRequest httpServletRequest){
//    System.out.println(10/0);
         boolean b = CrowdUtil.judgeRequestAjax(httpServletRequest);
         logger.info(String.valueOf(b));
         List<Admin> adminList=adminService.getAll();
    model.addAttribute("adminList",adminList);

    return  "target";
}

    @ResponseBody
    @RequestMapping(value = "/test/send/object.json")
    public ResultEntity<Student> getInfo(@RequestBody Student student,HttpServletRequest httpServletRequest){
         boolean b = CrowdUtil.judgeRequestAjax(httpServletRequest);
         logger.info(String.valueOf(b));
         logger.info(student.toString());
         return ResultEntity.successWithData(student);
    }

    @ResponseBody
    @RequestMapping(value = "/send/array/one.html")
    public String getReceiveArray(@RequestParam("array[]") List<Integer> list){

  for (Integer integer:list){
      System.out.println(integer);
  }
    return "success";
}


    @ResponseBody
    @RequestMapping(value = "/send/array/two.html")
    public String getReceiveArray2(ParamData paramData){
        List<Integer> array = paramData.getArray();
        for (Integer integer:array){
            System.out.println(integer);
        }
        return "success";
    }


    @ResponseBody
    @RequestMapping(value = "/send/array/three.html")
    public String getReceiveArray3(@RequestBody List<Integer> list){

        for (Integer integer:list){
            logger.debug("list");
        }
        return "success";
    }
}
