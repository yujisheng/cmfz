package com.baizhi.cmfz.service;

import com.baizhi.cmfz.entity.Menu;

import java.util.List;

public interface MenuService {

    /**
     * 获取菜单
     *
     * @return
     */
    public List<Menu> getMenus();
}
