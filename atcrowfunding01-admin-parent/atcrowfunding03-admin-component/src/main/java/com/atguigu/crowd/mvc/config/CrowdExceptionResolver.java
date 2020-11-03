package com.atguigu.crowd.mvc.config;



import com.atguigu.crowd.constant.CrowdConstant;
import com.atguigu.crowd.exception.AccessForbiddenException;
import com.atguigu.crowd.exception.LoginFailException;
import com.atguigu.crowd.exception.LoginacctAlreadyInUseException;
import com.atguigu.crowd.exception.OperationException;
import com.atguigu.crowd.util.CrowdUtil;
import com.atguigu.crowd.util.ResultEntity;
import com.google.gson.Gson;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


//表示当前类是一个基于注解的异常处理类
@ControllerAdvice
public class CrowdExceptionResolver {





    @ExceptionHandler(value = LoginacctAlreadyInUseException.class)
    public ModelAndView resolverDobulKeyException(LoginacctAlreadyInUseException exception,
                                                   HttpServletRequest request,
                                                   HttpServletResponse response) throws IOException{
        String name="admin-add";
        return commonsException(name,exception, response, request);
    }
    @ExceptionHandler(value = OperationException.class)
    public ModelAndView resolverOperationException(AccessForbiddenException exception,
                                                         HttpServletRequest request,
                                                         HttpServletResponse response) throws IOException{
        String name="system-error";
        return commonsException(name,exception, response, request);
    }
    @ExceptionHandler(value = AccessForbiddenException.class)
    public ModelAndView resolverAccessForbiddenException(AccessForbiddenException exception,
                                                   HttpServletRequest request,
                                                   HttpServletResponse response) throws IOException{
        String name="system-error";
        return commonsException(name,exception, response, request);
    }


    @ExceptionHandler(value = AccessDeniedException.class)
    public ModelAndView resolverAccessDeniedException(
            //异常
            AccessDeniedException exception,
            //判断是是什么请求
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) throws IOException {

        String name="system-error";
        return commonsException(name,exception,httpServletResponse,httpServletRequest);
    }

    //将一个具体的异常类型和方法关联起来
    @ExceptionHandler(value = LoginFailException.class)
    public ModelAndView resolverLoginFailException(
            //异常
            LoginFailException exception,
            //判断是是什么请求
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) throws IOException {

        String name="admin-login";
        return commonsException(name,exception,httpServletResponse,httpServletRequest);
    }

    //将一个具体的异常类型和方法关联起来
    @ExceptionHandler(value = ArithmeticException.class)
    public ModelAndView resolverMathException(
            //异常
            ArithmeticException exception,
            //判断是是什么请求
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) throws IOException {

         String name="system-error";
        return commonsException(name,exception,httpServletResponse,httpServletRequest);
    }

    //将一个具体的异常类型和方法关联起来
    @ExceptionHandler(value = NullPointerException.class)
    public ModelAndView resolverNullpoinException(
            //异常
            NullPointerException nullPointerException,
            //判断是是什么请求
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) throws IOException {

        String name="system-error";
        return commonsException(name,nullPointerException,httpServletResponse,httpServletRequest);
    }



    private ModelAndView commonsException(String viewname,
                                          Exception exception,
                                          HttpServletResponse httpServletResponse,
                                          HttpServletRequest httpServletRequest) throws IOException {

        boolean b = CrowdUtil.judgeRequestAjax(httpServletRequest);
        //如果是 ajax请求
        if(b){
            ResultEntity<Object> failed = ResultEntity.failed(exception.getMessage());
            //创建 gson
            Gson gson=new Gson();
            //将信息 转换为json字符串
            String toJson = gson.toJson(failed);
            //将json 字符串作为响应体传给浏览器
            httpServletResponse.getWriter().write(toJson);
            //由于上面  已将将json字符串传给浏览器做了响应，在这就不提供ModelandView对象了  ，
            return  null;
        }
        //如果不是ajax 请求 就创建modelandview
        ModelAndView modelAndView = new ModelAndView();
        //将exception存入模型
        modelAndView.addObject(CrowdConstant.ATTR_NAME_EXCEPTION,exception);
        //设置试图
        modelAndView.setViewName(viewname);
        //返回
        return modelAndView;
    }
}
