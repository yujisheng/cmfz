package com.baizhi.cmfz.dao;

import com.baizhi.cmfz.entity.UserDto;

import java.util.List;

public interface UserDao {
    /**
     * 获取近几天注册的用户数量
     *
     * @param day 当前日期距离注册日期的天数
     * @return
     */
    public Integer getUserCount(Integer day);

    /**
     * 获取每个省份男性用户的人数
     *
     * @return
     */
    public List<UserDto> getManCount();

    /**
     * 获取每个省份女性用户的人数
     *
     * @return
     */
    public List<UserDto> getWomanCount();
}
