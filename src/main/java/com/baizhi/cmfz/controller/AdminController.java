package com.baizhi.cmfz.controller;

import com.baizhi.cmfz.entity.Admin;
import com.baizhi.cmfz.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @RequestMapping("/adminLogin")
    @ResponseBody
    public boolean adminLogin(String name, String pwd, String kaptcha, HttpSession session) {
        String code = (String) session.getAttribute("kaptcha");
        Admin admin1 = adminService.adminLogin(name, pwd);
        System.out.println("name = " + name + "\tpwd = " + pwd);
        System.out.println("code = " + code + "\tkaptcha = " + kaptcha);

        if (admin1 != null && code.equals(kaptcha)) {
            return true;
        } else {
            return false;
        }
    }
}
