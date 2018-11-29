package com.baizhi.cmfz.service;

import com.baizhi.cmfz.entity.Album;

import java.util.Map;

public interface AlbumService {
    /**
     * 获取所有的专辑和章节并分页
     *
     * @param page
     * @param rows
     * @return
     */
    public Map getAllAlbumAndChapter(Integer page, Integer rows);

    /**
     * 添加专辑
     *
     * @param album
     */
    public void addAlbum(Album album);

}
