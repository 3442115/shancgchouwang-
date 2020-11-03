package com.atguigu.crowd.mvc.handler;

import com.atguigu.crowd.constant.CrowdConstant;
import com.atguigu.crowd.exception.LoginacctAlreadyInUseException;
import com.atguigu.crowd.exception.OperationException;
import com.atguigu.crowd.util.CrowdUtil;
import com.atguigu.crowd.entity.Admin;
import com.atguigu.crowd.service.AdminService;
import com.atguigu.crowd.util.ResultEntity;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

    @Controller
    public class TestHandler {
    private Logger logger = LoggerFactory.getLogger(TestHandler.class);
    @Autowired
    private AdminService adminService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    SimpleDateFormat simple=new SimpleDateFormat("yyyy/MM/dd HH:00:00");

    // 测试
     @RequestMapping(value = "/test/ssm.html")
    public String  getAllInfo(ModelMap model,HttpServletRequest httpServletRequest){
         boolean b = CrowdUtil.judgeRequestAjax(httpServletRequest);
         logger.info(String.valueOf(b));
         List<Admin> adminList=adminService.getAll();
         model.addAttribute("adminList",adminList);
          return  "target";
}
// 测试
    @ResponseBody
    @RequestMapping(value = "/send/array/one.html")
    public String getReceiveArray(@RequestParam("array[]") List<Integer> list){

     for (Integer integer:list){
      System.out.println(integer);
  }
        return "success";
}
// 测试
    @ResponseBody
    @RequestMapping(value = "/send/array/three.html")
    public String getReceiveArray3(@RequestBody List<Integer> list){

        for (Integer integer:list){
            logger.debug("list");
        }
        return "success";
    }
// 退出界面
    @RequestMapping(value = "admin/do/logout/.html")
        public String doLogyout(HttpSession session){
         // 让session强制失效
        session.invalidate();
        return "redirect:/admin/do/login/page.html";
    }
 // 分页查询
    @RequestMapping(value = "admin/get/page.html")
        public String getPageInfo(
                @RequestParam(value = "keyword", defaultValue = "")  String keyword,
                @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                @RequestParam(value = "pageSize",defaultValue = "5") Integer pageSize,
                ModelMap modelmap
    ){
        // 获得 PageInfo对象
         PageInfo<Admin> pageInfo = adminService.getPageInfo(keyword, pageNum, pageSize);
         modelmap.addAttribute(CrowdConstant.ATTR_NAME_PAGE_INFO,pageInfo);
         return "admin-page";
    }

    // 单条删除
  @RequestMapping(value = "admin/remove/{adminId}/{pageNum}/{keyword}.html")
        public String remove(
                @PathVariable("adminId")Integer adminId,
                @PathVariable("pageNum")Integer pageNum,
                @PathVariable("keyword")String  keyword,
                HttpSession session
       ){
      Admin admin = (Admin) session.getAttribute(CrowdConstant.ATTR_NAME_LOGIN_ADMIN );
      if(adminId.equals(admin.getId())){
          throw  new OperationException(CrowdConstant.ATTR_NAME_OPERATION_INFO);
      }
      adminService.remove(adminId);
         // 方案一 空着手取得没有数据
         // return "admin-page";

        // 方案二  会进行重复删除操作
        // return "forward：admin/get/page.html";
        // 方案三

         return "redirect:/admin/get/page.html?pageNum="+pageNum+"&keyword="+keyword;
  }
  // 存储
        @PreAuthorize("hasAuthority('user:save')")
        @RequestMapping(value = "admin/save.html")
        public String insertPageInfo(
                @RequestParam(value = "loginAcct")  String loginAcct,
                @RequestParam(value = "userPswd")  String userPswd,
                @RequestParam(value = "userName") String userName,
                @RequestParam(value = "email") String email,
                ModelMap modelmap
        ){
//            对密码进行加密
//            String userpswd = CrowdUtil.md5(userPswd);
            String userpsw=bCryptPasswordEncoder.encode(userPswd);

            // 获得昂前时间
            Calendar calendar = Calendar.getInstance();
            Admin admin = new Admin(null, loginAcct, userpsw, userName, email, simple.format(calendar.getTime()));
            //存储
            try {
                adminService.saveInfo(admin);
            }catch (Exception e){
                e.printStackTrace();
                if(e instanceof DuplicateKeyException){
                    throw new LoginacctAlreadyInUseException(CrowdConstant.MESSAGE_LOGIN_ACCT_ALREADY_IN_USE);
                }
            }
            return "redirect:/admin/get/page.html?pageNum="+Integer.MAX_VALUE;
        }

//  修改
        @RequestMapping(value = "admin/to/edit/page.html")
        public String getPageInfo(@RequestParam("adminId")Integer adminId, ModelMap modelMap){
            Admin admin = adminService.selectById(adminId);
            modelMap.addAttribute("admin",admin);
            return "admin-edit";
        }
// 更新
        @RequestMapping(value = "admin/to/update/page.html")
        public String update(Admin admin, @RequestParam("pageNum")Integer pageNum, @RequestParam("keyword")String keyword){
         adminService.update(admin);
            return "redirect:/admin/get/page.html?pageNum="+pageNum+"&keyword="+keyword;
        }

}
