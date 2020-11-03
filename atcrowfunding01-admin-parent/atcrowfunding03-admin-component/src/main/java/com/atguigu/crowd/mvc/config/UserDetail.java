package com.atguigu.crowd.mvc.config;

import com.atguigu.crowd.entity.Admin;
import com.atguigu.crowd.entity.Role;
import com.atguigu.crowd.mapper.AdminMapper;
import com.atguigu.crowd.service.AdminService;
import com.atguigu.crowd.service.AuthService;
import com.atguigu.crowd.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserDetail implements UserDetailsService {
    @Autowired
    private AdminService adminService;
    @Autowired
    private AuthService authService;
    @Autowired
    private RoleService roleService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 查询Admin对象
        Admin admin= adminService.getAdminByLoginacct(username);
        // 获得角色id
        Integer id = admin.getId();
        // 获得权限
        List<String> assignedAuthNameByadminId = authService.getAssignedAuthNameByadminId(id);
        // 获得角色
        List<Role> assignRole = roleService.getAssignRole(id);
        
        List<GrantedAuthority> authority=new ArrayList<>();

        for (Role role : assignRole) {
            String roleName="ROLE_"+role.getName();

            SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(roleName);

            authority.add(simpleGrantedAuthority);
        }

        for (String str : assignedAuthNameByadminId) {
            SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(str);

            authority.add(simpleGrantedAuthority);
        }
        SecurityAdmin securityAdmin = new SecurityAdmin(admin, authority);
        return securityAdmin;
    }
}
