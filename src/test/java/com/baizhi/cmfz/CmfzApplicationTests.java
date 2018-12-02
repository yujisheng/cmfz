package com.baizhi.cmfz;

import com.baizhi.cmfz.dao.AdminDao;
import com.baizhi.cmfz.dao.AlbumDao;
import com.baizhi.cmfz.entity.Admin;
import com.baizhi.cmfz.entity.Album;
import com.baizhi.cmfz.entity.Chapter;
import com.baizhi.cmfz.entity.Menu;
import com.baizhi.cmfz.service.MenuService;
import com.baizhi.cmfz.service.TurnImgService;
import com.baizhi.cmfz.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

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

    @Autowired
    private TurnImgService turnImgService;

    @Test
    public void mmm() {
        Map turnImgs = turnImgService.getAllImg(1, 3);
        for (Object value : turnImgs.values()) {
            System.out.println("value = " + value);
        }

    }

    @Autowired
    private AlbumDao albumDao;

    @Test
    public void mm2() {
        List<Album> albums = albumDao.getAllAlbumAndChapter(0, 10);
        for (Album album : albums) {
            System.out.println("album = " + album);
            for (Chapter child : album.getChildren()) {
                System.out.println("child = " + child);
            }
        }
        System.out.println(albumDao.getCount());
    }

    @Autowired
    private UserService userService;

    @Test
    public void mm3() {
        List<Integer> userCount = userService.getUserCount();
        for (Integer integer : userCount) {
            System.out.println("integer = " + integer);
        }

        Map countManAndWoman = userService.getCountManAndWoman();
        for (Object value : countManAndWoman.values()) {
            System.out.println("value = " + value);
        }
    }

}
