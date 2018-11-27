package com.baizhi.cmfz.service;

import com.baizhi.cmfz.entity.Admin;

public interface AdminService {
    /**
     * 管理员登录
     *
     * @param name
     * @param pwd
     * @return
     */
    public Admin adminLogin(String name, String pwd);

    /**
     * 管理员注册
     *
     * @param admin
     */
    public void adminRegister(Admin admin);
}
