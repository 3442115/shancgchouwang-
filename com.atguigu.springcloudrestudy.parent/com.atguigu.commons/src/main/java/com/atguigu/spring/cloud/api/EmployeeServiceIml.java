package com.atguigu.spring.cloud.api;

import com.atguigu.spring.cloud.entity.Employee;
import com.atguigu.spring.cloud.util.ResultEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EmployeeServiceIml implements EmployeeRemoteService{
    @Override
    public Employee getEmployee() {
        return null;
    }

    @Override
    public Employee getEmployee2() {
        return null;
    }

    @Override
    public List<Employee> getEmployeeByCan(String kwyword) {
        return null;
    }

    @Override
    public ResultEntity<Employee> getEmployByCr(String keyword) {
        return ResultEntity.failed("throwable.getMessage()+吱吱吱吱吱吱吱吱在");
    }
}
