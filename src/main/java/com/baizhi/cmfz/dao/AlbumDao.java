package com.baizhi.cmfz.dao;

import com.baizhi.cmfz.entity.Album;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AlbumDao {
    /**
     * 获取所有的专辑和章节并分页
     *
     * @param start
     * @param rows
     * @return
     */
    public List<Album> getAllAlbumAndChapter(@Param("start") Integer start, @Param("rows") Integer rows);

    /**
     * 统计所有专辑的数据
     *
     * @return
     */
    public int getCount();

    /**
     * 添加专辑
     *
     * @param album
     */
    public void addAlbum(Album album);
}
