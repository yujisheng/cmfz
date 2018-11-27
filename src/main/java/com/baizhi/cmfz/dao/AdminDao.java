package com.baizhi.cmfz.dao;

import com.baizhi.cmfz.entity.Admin;
import org.apache.ibatis.annotations.Param;

public interface AdminDao {
    /**
     * 管理员登录
     *
     * @param name
     * @param pwd
     * @return
     */
    public Admin adminLogin(@Param("name") String name, @Param("pwd") String pwd);

    /**
     * 管理员注册
     *
     * @param admin
     */
    public void adminRegister(Admin admin);
}
