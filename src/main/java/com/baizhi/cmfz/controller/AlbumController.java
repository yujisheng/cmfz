package com.baizhi.cmfz.controller;

import com.baizhi.cmfz.entity.Album;
import com.baizhi.cmfz.service.AlbumService;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/album")
public class AlbumController {

    @Autowired
    private AlbumService albumService;

    @RequestMapping("/getAllAlbumAdnChapter")
    @ResponseBody
    public Map getAllAlbumAndChapter(Integer page, Integer rows) {
        System.out.println("page =" + page + "\trows = " + rows);
        return albumService.getAllAlbumAndChapter(page, rows);
    }

    @RequestMapping("/addAlbum")
    @ResponseBody
    public boolean addAlbum(HttpSession session, MultipartFile uploadFile, Album album) {
        System.out.println(album);
        // 获取扩展名
        String extension = FilenameUtils.getExtension(uploadFile.getOriginalFilename());
        String newFileName = UUID.randomUUID().toString() + "." + extension;
        String realPath = session.getServletContext().getRealPath("/images/albumImages");
        try {
            uploadFile.transferTo(new File(realPath + "\\" + newFileName));
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        album.setCoverImg("/images/albumImages/" + newFileName);
        try {
            albumService.addAlbum(album);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
