package com.baizhi.cmfz.service;

import com.baizhi.cmfz.entity.TurnImg;

import java.util.Map;

public interface TurnImgService {
    /**
     * 查询所有轮番图数据
     *
     * @param page
     * @param rows
     * @return
     */
    public Map getAllImg(Integer page, Integer rows);

    /**
     * 添加轮番图
     *
     * @param turnImg
     */
    public void addImg(TurnImg turnImg);

    /**
     * 删除轮番图
     *
     * @param ids
     */
    public void deleteMultiImg(int[] ids);

    /**
     * 修改轮番图信息
     *
     * @param turnImg
     */
    public void updateImg(TurnImg turnImg);

}
