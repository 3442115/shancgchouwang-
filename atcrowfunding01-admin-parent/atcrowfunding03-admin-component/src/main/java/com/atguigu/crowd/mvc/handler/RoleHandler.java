package com.atguigu.crowd.mvc.handler;

import com.atguigu.crowd.entity.Role;
import com.atguigu.crowd.service.RoleService;
import com.atguigu.crowd.util.ResultEntity;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class RoleHandler {
    @Autowired
    private RoleService roleService;

    //

    @ResponseBody
    @PreAuthorize("hasRole('部长')")
    @RequestMapping(value = "/role/get/page/info.json")
    public ResultEntity<PageInfo<Role>> getPageInfo(
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize",defaultValue = "5") Integer pageSize,
            @RequestParam(value = "keyword", defaultValue = "")  String keyword
         ){
        PageInfo<Role> pageInfo = roleService.getPageInfo(pageNum, pageSize, keyword);

        return ResultEntity.successWithData(pageInfo);
    }


    // 新增
    @ResponseBody
    @RequestMapping(value = "/role/save.json")
    public ResultEntity<String> saveInfo(Role role){

     roleService.saveRole(role);
     return ResultEntity.successWithoutData();
    }

    // 更新
    @ResponseBody
    @RequestMapping(value = "/role/update.json")
    public ResultEntity<String> updateInfo(Role role){

        roleService.updateInfo(role);
        return ResultEntity.successWithoutData();
    }


    // 删除
    @ResponseBody
    @RequestMapping(value = "/role/remove/by/role/id/array.json")
    public ResultEntity<String> deleteInfo(@RequestBody List<Integer> list){
       roleService.deleteInfo(list);
       return ResultEntity.successWithoutData();
    }





}
