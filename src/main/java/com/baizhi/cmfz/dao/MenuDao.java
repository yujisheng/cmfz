package com.baizhi.cmfz.dao;

import com.baizhi.cmfz.entity.Menu;

import java.util.List;

public interface MenuDao {

    /**
     * 获取菜单
     *
     * @return
     */
    public List<Menu> getMenus();

}
