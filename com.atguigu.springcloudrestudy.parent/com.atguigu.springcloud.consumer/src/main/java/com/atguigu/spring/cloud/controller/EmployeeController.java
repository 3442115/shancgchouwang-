package com.atguigu.spring.cloud.controller;


import com.atguigu.spring.cloud.api.EmployeeRemoteService;
import com.atguigu.spring.cloud.entity.Employee;
import com.atguigu.spring.cloud.util.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class EmployeeController {
    private static final String PAYMENT_URL="http://ATGUIGU-PROVIDER";
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private EmployeeRemoteService employeeRemoteService;

    @RequestMapping("/consumer/getmessage")
    public Employee getMessage(){
        return  restTemplate.getForObject(PAYMENT_URL+"/provider/get/employee/remote",Employee.class);

    }

    @RequestMapping("/consumer/remote/openfeign")
    public Employee getNewMessage(){
        return employeeRemoteService.getEmployee();
    }

    @RequestMapping("/consumer/remote/openfeign/canshu")
    public List<Employee> getNewMessageByCan(String keyword){
        return employeeRemoteService.getEmployeeByCan(keyword);
    }

    @RequestMapping("/consumer/remote/openfeign/breaker")
    public ResultEntity<Employee> getIt(@RequestParam("keyword") String keyword){

        return employeeRemoteService.getEmployByCr(keyword);
    }
}
