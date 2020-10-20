package com.atguigu.crowd.mvc.config;


import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;


//表示当前类是一个基于注解的异常处理类
@ControllerAdvice
public class CrowdExceptionResolver {

    //将一个具体的异常类型和方法关联起来
    @ExceptionHandler(value = NullPointerException.class)
    public ModelAndView resolverNullpoinException(NullPointerException nullPointerException ){

        return null;
    }
}
