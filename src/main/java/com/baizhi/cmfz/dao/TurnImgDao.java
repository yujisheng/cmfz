package com.baizhi.cmfz.dao;

import com.baizhi.cmfz.entity.TurnImg;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TurnImgDao {
    /**
     * 查询所有轮番图数据
     *
     * @param start
     * @param rows
     * @return
     */
    public List<TurnImg> getAllImg(@Param("start") Integer start, @Param("rows") Integer rows);

    /**
     * 统计轮番图数据总数
     *
     * @return
     */
    public int getImgCount();

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
