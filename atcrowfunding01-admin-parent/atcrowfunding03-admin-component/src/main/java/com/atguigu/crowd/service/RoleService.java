package com.atguigu.crowd.service;

import com.atguigu.crowd.entity.Role;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface RoleService {
    PageInfo<Role> getPageInfo(Integer pageNum,Integer pageSize,String keyword);

    void saveRole(Role role);

    void updateInfo(Role role);

    void  deleteInfo(List<Integer> list);

    List<Role> getAssignRole(Integer id);

    List<Role> getUnAssignRole(Integer id);

}
