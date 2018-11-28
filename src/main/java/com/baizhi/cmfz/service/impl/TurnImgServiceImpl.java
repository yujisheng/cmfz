package com.baizhi.cmfz.service.impl;

import com.baizhi.cmfz.dao.TurnImgDao;
import com.baizhi.cmfz.entity.TurnImg;
import com.baizhi.cmfz.service.TurnImgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class TurnImgServiceImpl implements TurnImgService {

    @Autowired
    private TurnImgDao turnImgDao;

    @Override
    public void addImg(TurnImg turnImg) {
        turnImg.setImg_date(new Date());
        turnImgDao.addImg(turnImg);
    }

    @Override
    public void deleteMultiImg(int[] ids) {
        turnImgDao.deleteMultiImg(ids);
    }

    @Override
    public void updateImg(TurnImg turnImg) {
        turnImgDao.updateImg(turnImg);
    }

    @Override
    public Map getAllImg(Integer page, Integer rows) {
        int start = (page - 1) * rows;
        int total = turnImgDao.getImgCount();
        List<TurnImg> turnImgs = turnImgDao.getAllImg(start, rows);
        /*for (TurnImg turnImg : turnImgs) {
            System.out.println("turnImg = " + turnImg);
        }*/
        Map map = new HashMap<String, Object>();
        map.put("total", total);
        map.put("rows", turnImgs);
        return map;
    }

}
