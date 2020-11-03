package com.atguigu.crowd.mvc.handler;

import com.atguigu.crowd.constant.CrowdConstant;
import com.atguigu.crowd.entity.Admin;
import com.atguigu.crowd.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import javax.servlet.http.HttpSession;

@Controller
public class LoginHandler {

    @Autowired
    private AdminService adminService;


    @RequestMapping("admin/do/login.html")
    public String doLogin(Admin admin,
                          HttpSession session){
       Admin admin2= adminService.getAdminByAcctPswd(admin.getLoginAcct(),admin.getUserPswd());
       session.setAttribute(CrowdConstant.ATTR_NAME_LOGIN_ADMIN,admin2);
        return "redirect:/admin/to/main/page.html";
    }
}
