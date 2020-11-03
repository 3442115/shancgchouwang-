package com.atguigu.crowd.mvc.config;

import com.atguigu.crowd.entity.Admin;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.List;

public class SecurityAdmin extends User {

    /*
    * 在原始的springsecurity中，User只包含账号密码 现在对User类进行扩展  让它能包含更多的东西   * */
  // 原始的Admin对象
    private Admin originAdmin;
    public SecurityAdmin(Admin admin, List<GrantedAuthority> list){
        // 已将将账号密码 传给父类的构造器了 父类会在验证完之后将密码擦除 安全性更好
        super(admin.getLoginAcct(),admin.getUserPswd(),list);
        this.originAdmin=admin;
        //  将自身密码也擦除 安全性 更好
        this.originAdmin.setUserPswd(null);
    }

    public Admin getOriginAdmin() {
        return originAdmin;
    }
}
