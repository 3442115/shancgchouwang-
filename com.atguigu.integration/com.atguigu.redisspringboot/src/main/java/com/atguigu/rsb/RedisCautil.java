package com.atguigu.rsb;

import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component //注入spring容器
public class RedisCautil {
    @Resource
    private RedisTemplate<String, Object> redisTemplate;
  @Bean
    public RedisTemplate get() {
        return new  RedisTemplate();
    }


}
