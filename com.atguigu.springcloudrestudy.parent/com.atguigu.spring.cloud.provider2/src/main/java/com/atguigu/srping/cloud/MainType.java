package com.atguigu.srping.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;

@SpringBootApplication
@EnableCircuitBreaker
public class MainType {
    public static void main(String[] args) {
        SpringApplication.run(MainType.class,args);
    }
}
