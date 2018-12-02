package com.baizhi.cmfz.controller;

import com.baizhi.cmfz.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/getUserCount")
    @ResponseBody
    public List<Integer> getUserCount() {
        return userService.getUserCount();
    }

    @RequestMapping("getCountManAndWoman")
    @ResponseBody
    public Map getCountManAndWoman() {
        return userService.getCountManAndWoman();
    }

    @RequestMapping("getManCount")
    @ResponseBody
    public Map getManCount() {
        System.out.println("男");
        return userService.getManCount();
    }

    @RequestMapping("getWomanCount")
    @ResponseBody
    public Map getWomanCount() {
        System.out.println("女");
        return userService.getWomanCount();
    }

    @RequestMapping("/getAllUser")
    @ResponseBody
    public Map getAllUser(Integer page, Integer rows) {
        return userService.getAllUser(page, rows);
    }
}
