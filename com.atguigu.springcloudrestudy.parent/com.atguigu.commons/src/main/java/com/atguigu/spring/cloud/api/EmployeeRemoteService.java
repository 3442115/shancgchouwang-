package com.atguigu.spring.cloud.api;

import com.atguigu.spring.cloud.entity.Employee;
import com.atguigu.spring.cloud.factory.MyfallbackFactory;
import com.atguigu.spring.cloud.util.ResultEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
                                   //  第二种方式      fallback= EmployeeServiceIml
@FeignClient(value = "ATGUIGU-PROVIDER",fallbackFactory = MyfallbackFactory.class)
public interface EmployeeRemoteService {
    @RequestMapping("/provider/get/employee/remote")
    public Employee getEmployee();

    @RequestMapping("/provider/get/employee/remote2")
    public Employee getEmployee2();

    @RequestMapping("/provider/get/employee/canshu")
    public List<Employee> getEmployeeByCan(@RequestParam("keyword") String kwyword);


    @RequestMapping("/provider/get/life/breaker")
    public ResultEntity<Employee> getEmployByCr(@RequestParam("keyword") String keyword);
}