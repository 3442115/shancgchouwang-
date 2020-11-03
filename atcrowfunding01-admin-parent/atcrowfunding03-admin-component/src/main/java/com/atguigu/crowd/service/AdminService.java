package com.atguigu.crowd.service;

import com.atguigu.crowd.entity.Admin;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface AdminService {
    void saveInfo(Admin admin);

    List<Admin> getAll();

    Admin getAdminByAcctPswd(String loginAcct, String userPswd);

    PageInfo<Admin> getPageInfo(String keyword,Integer pageNum,Integer pageSize);

    void remove(Integer adminId);

    Admin selectById(Integer integer);

    void update(Admin admin);

    void saveAssignAdmin(Integer adminId,List<Integer> roleIdList);

    Admin getAdminByLoginacct(String username);
}
