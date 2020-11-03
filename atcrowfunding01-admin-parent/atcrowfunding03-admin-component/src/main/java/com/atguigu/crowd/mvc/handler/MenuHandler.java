package com.atguigu.crowd.mvc.handler;

import com.atguigu.crowd.entity.Menu;
import com.atguigu.crowd.service.MenuService;
import com.atguigu.crowd.util.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
public class MenuHandler {
    @Autowired
    private MenuService menuService;



    @RequestMapping(value = "/menu/update.json")
    public ResultEntity<String> update(Menu menu){
        menuService.updateMenu(menu);
        return ResultEntity.successWithoutData();
    }

    @RequestMapping(value = "/menu/remove.json")
    public ResultEntity<String> delete(Menu menu){
        menuService.deleteMenu(menu);
        return ResultEntity.successWithoutData();
    }


    @RequestMapping(value = "/menu/save.json")
    public ResultEntity<String> getChildTree(Menu menu){
       menuService.saveMenu(menu);
        return ResultEntity.successWithoutData();
    }



    @RequestMapping(value = "/menu/get/whole/tree.json")
    public ResultEntity<Menu> getWholeTreeNew(){
        // 查询全部的Menu对象
        List<Menu> menuList = menuService.getAll();
        // 声明一个变量用来存储找到的根节点
        Menu root=null;
        Map<Integer,Menu> menuMap=new HashMap<>();
        // 检查皮带是否为null 为空则为父节点
        for (Menu menu : menuList) {
           menuMap.put(menu.getId(),menu);
        }
        for (Menu menufather : menuList) {
            Integer pid = menufather.getPid();
            if(pid == null){
                root=menufather;
                continue;
            }
            // 找到他爹了
            Menu menu = menuMap.get(pid);
            menu.getChildren().add(menufather);
        }
        //  不为空 找父节点
        return ResultEntity.successWithData(root);
    }


//
//    public ResultEntity<Menu> getWholeTreeOld(){
//        // 查询全部的Menu对象
//        List<Menu> menuList = menuService.getAll();
//        // 声明一个变量用来存储找到的根节点
//        Menu root=null;
//        // 检查皮带是否为null 为空则为父节点
//        for (Menu menu : menuList) {
//            Integer pid=menu.getPid();
//            if(pid == null){
//                root=menu;
//                continue;
//            }
//            for (Menu maybeFather : menuList) {
//                Integer id = maybeFather.getId();
//
//                if(Objects.equals(pid,id)){
//                    maybeFather.getChildren().add(menu);
//                    break ;
//                }
//            }
//        }
//    //  不为空 找父节点
//        return ResultEntity.successWithData(root);
//    }
}
