package com.atguigu.crowd.service;

import com.atguigu.crowd.entity.Menu;

import java.util.List;

public interface MenuService {
    List<Menu> getAll();

    void saveMenu(Menu menu);

    void deleteMenu(Menu menu);

    void updateMenu(Menu menu);
}
