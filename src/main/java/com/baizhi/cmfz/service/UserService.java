package com.baizhi.cmfz.service;

import java.util.List;
import java.util.Map;

public interface UserService {
    /**
     * 获取近几天注册的用户数量
     *
     * @return
     */
    public List<Integer> getUserCount();

    /**
     * 获取每个省份男性用户的数量，封装到Map集合中
     *
     * @return
     */
    public Map getManCount();

    /**
     * 获取每个省份女性用户的数量，封装到Map集合中
     *
     * @return
     */
    public Map getWomanCount();

    public Map getCountManAndWoman();
}
