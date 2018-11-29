package com.baizhi.cmfz.service.impl;

import com.baizhi.cmfz.dao.AlbumDao;
import com.baizhi.cmfz.entity.Album;
import com.baizhi.cmfz.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class AlbumServiceImpl implements AlbumService {

    @Autowired
    private AlbumDao albumDao;

    @Override
    public Map getAllAlbumAndChapter(Integer page, Integer rows) {
        int start = (page - 1) * rows;
        List<Album> albums = albumDao.getAllAlbumAndChapter(start, rows);
        int total = albumDao.getCount();
        Map map = new HashMap();
        map.put("total", total);
        map.put("rows", albums);
        return map;
    }

    @Override
    public void addAlbum(Album album) {
        album.setPublishDate(new Date());
        albumDao.addAlbum(album);
    }
}
