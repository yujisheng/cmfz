package com.baizhi.cmfz.service.impl;

import com.baizhi.cmfz.dao.UserDao;
import com.baizhi.cmfz.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public List<Integer> getUserCount() {
        int[] days = {7, 15, 30, 90, 180, 365};

        List<Integer> data = new ArrayList<Integer>();
        for (int i = 0; i < days.length; i++) {
            data.add(userDao.getUserCount(days[i]));
        }
        return data;
    }

    @Override
    public Map getCountManAndWoman() {
        Map map = new HashMap();
        map.put("man", userDao.getManCount());
        map.put("woman", userDao.getWomanCount());
        return map;
    }

    @Override
    public Map getManCount() {
        Map map = new HashMap();
        map.put("data", userDao.getManCount());
        return map;
    }

    @Override
    public Map getWomanCount() {
        Map map = new HashMap();
        map.put("data", userDao.getWomanCount());
        return map;
    }
}
