package com.atguigu.spring.cloud.factory;

import com.atguigu.spring.cloud.api.EmployeeRemoteService;
import com.atguigu.spring.cloud.entity.Employee;
import com.atguigu.spring.cloud.util.ResultEntity;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

// 下面的这个类就是全局降级的service接口
//
@Component
public class MyfallbackFactory implements FallbackFactory<EmployeeRemoteService> {
    @Override
    public EmployeeRemoteService create(Throwable throwable) {
       // 以匿名内部类的方式 返回降级结果
        return new EmployeeRemoteService() {
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
                System.out.println("sada");
                return ResultEntity.failed(throwable.getMessage());
            }
        };
    }
}
