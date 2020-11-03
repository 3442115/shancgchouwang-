package com.atguigu.spring.cloud.controller;


import com.atguigu.spring.cloud.entity.Employee;
import com.atguigu.spring.cloud.util.ResultEntity;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class EmployeeController {

    @RequestMapping("/provider/get/employee/remote")
    public Employee getEmployee(){
        return new Employee(1,"gf",15360.0);
    }

    @RequestMapping("/provider/get/employee/remote2")
    public Employee getEmployee2(){
        return new Employee(1,"gf",25360.0);
    }

    @RequestMapping("/provider/get/employee/canshu")
    public List<Employee> getEmployeeByCan(@RequestParam("keyword") String kwyword){
        System.out.println(kwyword);
        ArrayList<Employee> employees = new ArrayList<>();
        employees.add(new Employee(2,"gf",25698.0));
        employees.add(new Employee(3,"gf",29698.0));
        employees.add(new Employee(4,"gf",35698.0));
        return employees;
    }


    @RequestMapping("/provider/get/life/breaker")
    @HystrixCommand(fallbackMethod ="getEmployByCrbackup" )
    public ResultEntity<Employee> getEmployByCr(@RequestParam("keyword") String keyword){
        if(keyword.equals("bang")){
            throw new RuntimeException();
        }

        return ResultEntity.successWithData(new Employee(4645712,"gfwss",30000.0));
    }

    public ResultEntity<Employee> getEmployByCrbackup(@RequestParam("keyword") String keyword){
        return ResultEntity.failed("出错");
    }

}
