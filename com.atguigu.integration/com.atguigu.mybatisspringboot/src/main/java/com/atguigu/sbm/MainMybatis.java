package com.atguigu.sbm;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.atguigu.sbm.mapper")
@SpringBootApplication
public class MainMybatis {

    public static void main(String[] args) {
        SpringApplication.run(MainMybatis.class,args);
    }
}
