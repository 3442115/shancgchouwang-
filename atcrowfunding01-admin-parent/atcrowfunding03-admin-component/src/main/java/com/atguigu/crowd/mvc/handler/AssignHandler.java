package com.atguigu.crowd.mvc.handler;


import com.atguigu.crowd.entity.Auth;
import com.atguigu.crowd.entity.Role;
import com.atguigu.crowd.service.AdminService;
import com.atguigu.crowd.service.AuthService;
import com.atguigu.crowd.service.RoleService;
import com.atguigu.crowd.util.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
public class AssignHandler {
    @Autowired
    private RoleService roleService;
    @Autowired
   private AdminService adminService;
    @Autowired
   private AuthService authService;

    @ResponseBody
    @RequestMapping("assign/do/role/assign/auth.json")
    public ResultEntity<List<Integer>> saveRoleAuthRelationship(
            @RequestBody Map<String,List<Integer>> map
    ){
        authService.saveRoleAuthRelationship(map);
        return ResultEntity.successWithoutData();
    }



    @ResponseBody
    @RequestMapping("/assign/get/assign/auth/id/by/role/id.json")
    public ResultEntity<List<Integer>> getAssignedAuthIdByRoleId(
            @RequestParam("roleId") Integer roleId
    ){
        List<Integer> authIdList=authService.getAssignedAuthIdByRoleId(roleId);
        return ResultEntity.successWithData(authIdList);
    }

@ResponseBody
@RequestMapping("/assign/get/all/auth.json")
public ResultEntity<List<Auth>> getAllAuth(){
    List<Auth> all = authService.getAll();

    return ResultEntity.successWithData(all );
}


    @RequestMapping(value = "assign/do/role/assign.html")
    public String toSaveAssign(
            @RequestParam(value = "adminId") Integer adminid,
            @RequestParam(value = "pageNum") Integer pageNum,
            @RequestParam(value = "keyword") String keyword,
            @RequestParam(value = "roleIdList",required = false) List<Integer> roleIdList
                               ){

          adminService.saveAssignAdmin(adminid,roleIdList);
          return "redirect:/admin/get/page.html?pageNum="+pageNum+"&keyword="+keyword;
    }

@RequestMapping(value = "assign/to/assign/role/page.html")
    public String toAssignPage(@RequestParam(value = "adminId") Integer adminid,
                               ModelMap modelMap){
    // 查询已分配的角色
    List<Role> assignList= roleService.getAssignRole(adminid);
    // 查询未分配的角色
    List<Role> unAssignList= roleService.getUnAssignRole(adminid);
    // 存入模型
    modelMap.addAttribute("assignList",assignList);
    modelMap.addAttribute("unAssignList",unAssignList);

    return "assign-role";
}




}
