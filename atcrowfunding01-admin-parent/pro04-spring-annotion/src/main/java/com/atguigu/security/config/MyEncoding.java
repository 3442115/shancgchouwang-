package com.atguigu.security.config;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

@Component
public class MyEncoding implements PasswordEncoder {
    @Override
    public String encode(CharSequence charSequence) {
     return    privateEncoding(charSequence);
    }

    @Override
    public boolean matches(CharSequence charSequence, String s) {
        // 明文加密的密码
        String s1 = privateEncoding(charSequence);
        // 数据库的密码
        String dataPassword=s;
        return Objects.equals(s1,dataPassword);
    }

    private String privateEncoding(CharSequence charSequence){
        try {   String algroy="MD5";
            MessageDigest ins = MessageDigest.getInstance(algroy);
            byte[] bytes = ((String) charSequence).getBytes();
            byte[] digest = ins.digest(bytes);
            String string = new BigInteger(1, digest).toString(16);
            return string;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println(new MyEncoding().privateEncoding("123"));
    }
}