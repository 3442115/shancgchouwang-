package com.atguigu.crowd;


import javax.servlet.http.HttpServletRequest;

/*判断是否为ajax请求
@param：ruquest请求对象
return：
true ：是ajax请求
false： 不是ajax请求
*
* */
public class CrowdUtil {

     public static  boolean judgeRequestAjax(HttpServletRequest httpServletRequest){

          String accept = httpServletRequest.getHeader("Accept");
          String header = httpServletRequest.getHeader("X-Requested-With");
          return (accept != null && (accept.contains("application/json")))
                  ||
                  (header != null && (header.equals("XMLHttpRequest")));
     }
}
