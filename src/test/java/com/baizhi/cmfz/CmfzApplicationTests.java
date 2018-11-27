package com.baizhi.cmfz;

import com.baizhi.cmfz.dao.AdminDao;
import com.baizhi.cmfz.entity.Admin;
import com.baizhi.cmfz.entity.Menu;
import com.baizhi.cmfz.service.MenuService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CmfzApplicationTests {

    @Autowired
    private AdminDao adminDao;

    @Test
    public void contextLoads() {
        Admin admin = adminDao.adminLogin("admin", "123456");
        System.out.println("admin = " + admin);
    }

    @Autowired
    private MenuService menuService;

    @Test
    public void mm1() {
        List<Menu> menus = menuService.getMenus();
        for (Menu menu : menus) {
            System.out.println("menu = " + menu);
        }
    }

}
