package com.atguigu.security.config;

import com.atguigu.security.config.entity.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MyUserDetial implements UserDetailsService {
   @Autowired
   private JdbcTemplate jdbcTemplate;

    // 总目标：根据表单提交的用户名查询user对象   并装配角色 权限等信息
    @Override
    public UserDetails loadUserByUsername(
            // 表单提交的用户名
            String username) throws UsernameNotFoundException {
        // 从数据库查询对象
        String sql="select loginacct,userpswd,username,email,createtime from t_admin where loginacct=?";
        List<Admin> query = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Admin.class), username);
        Admin admin = query.get(0);
        // 给Admin设置权限信息

        ArrayList<GrantedAuthority> authority = new ArrayList<>();
        authority.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        authority.add(new SimpleGrantedAuthority("UPDATE"));
        // 把admin对象和authority对象封装到UserDetails
        String userpswd = admin.getUserpswd();

        return new User(username,userpswd,authority);
    }
}
