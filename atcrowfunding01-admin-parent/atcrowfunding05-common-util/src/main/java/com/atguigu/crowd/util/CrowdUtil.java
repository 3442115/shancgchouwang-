package com.atguigu.crowd.util;


import com.atguigu.crowd.constant.CrowdConstant;

import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Logger;

/*判断是否为ajax请求
@param：ruquest请求对象
return：
true ：是ajax请求
false： 不是ajax请求
*
* */
public class CrowdUtil {

     /*对字符串进行md5加密
     **/
     public static String md5(String resource){
          if(resource==null||resource.length()==0){
              throw  new RuntimeException(CrowdConstant.MESSAGE_STRING_INVALIDATE);
          }

          try {
               // 1.获取MessageDigest对象
               String algorithm="md5";
              // 2.将resource 转换为字节数组
               MessageDigest instance = MessageDigest.getInstance(algorithm);

               byte[] input = resource.getBytes();
               // 3.执行加密
               byte[] output = instance.digest(input);
               // 4.创建bigInteger
               int signum=1;
               BigInteger bigInteger = new BigInteger(signum,output);
               // 5.按照16禁进制 转换为字符串
               int radix=16;
               String encoding = bigInteger.toString(radix).toUpperCase();

               return encoding;
          } catch (NoSuchAlgorithmException e) {
               e.printStackTrace();
          }
          return null;
     }



     public static  boolean judgeRequestAjax(HttpServletRequest httpServletRequest){

          String accept = httpServletRequest.getHeader("Accept");
          String header = httpServletRequest.getHeader("X-Requested-With");
          return (accept != null && (accept.contains("application/json")))
                  ||
                  (header != null && (header.equals("XMLHttpRequest")));
     }
}
