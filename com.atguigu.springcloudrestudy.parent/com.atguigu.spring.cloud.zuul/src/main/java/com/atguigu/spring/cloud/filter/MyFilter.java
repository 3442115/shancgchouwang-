package com.atguigu.spring.cloud.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class MyFilter extends ZuulFilter {
    @Override
    public String filterType() {
        String filerType="pre";
     return filerType;
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        // 获取requestContext对象
        RequestContext currentContext = RequestContext.getCurrentContext();
        //  获取Request对象
        HttpServletRequest request = currentContext.getRequest();

        String keyword = request.getParameter("keyword");
        return "hello".equals(keyword);
    }

    @Override
    public Object run() throws ZuulException {
        System.out.println("执行 run  方法了");
        return null;
    }
}
