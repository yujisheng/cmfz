package com.baizhi.cmfz.dao;

import com.baizhi.cmfz.entity.User;
import com.baizhi.cmfz.entity.UserDto;
import org.apache.ibatis.annotations.Param;

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

    /**
     * 获取所有用户信息并分页
     *
     * @return
     */
    public List<User> getAllUser(@Param("start") Integer start, @Param("rows") Integer rows);

    /**
     * 统计用户的数量
     *
     * @return
     */
    public int getAllCount();

    /**
     * 获取所有用户信息不分页
     *
     * @return
     */
    public List<User> getAll();
}
