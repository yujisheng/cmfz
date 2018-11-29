package com.baizhi.cmfz.controller;

import com.baizhi.cmfz.entity.Chapter;
import com.baizhi.cmfz.service.ChapterService;
import com.baizhi.cmfz.util.FileUtil;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.UUID;

@Controller
@RequestMapping("/chapter")
public class ChapterController {

    @Autowired
    private ChapterService chapterService;

    @RequestMapping("/addChapter")
    @ResponseBody
    public boolean addChapter(MultipartFile uploadFile, Chapter chapter, HttpSession session) {
        System.out.println("chapter = " + chapter);
        /*
         * 调用业务
         * 获取大小时长
         * 保存文件   //文件上传 文件保存到那 文件重名  将文件保存到指定目录
         * 保存数据
         * */
        String realPath = session.getServletContext().getRealPath("/datagrid/");  //webapp当前项目的路径
        File file = new File(realPath + "/upload");
        // 创建文件夹
        if (!file.exists()) {
            file.mkdir();
        }

        // 获取扩展名
        String extension = FilenameUtils.getExtension(uploadFile.getOriginalFilename());

        String newName = UUID.randomUUID().toString() + "." + extension;

        chapter.setDownPath("/datagrid/upload/" + newName);

        try {
            uploadFile.transferTo(new File(file.getAbsolutePath(), newName));
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        // 获取音频文件时长
        int duration = FileUtil.getDuration(new File(file.getAbsolutePath() + "/" + newName));
        int minute = duration / 60;
        int second = duration % 60;
        String length = minute + "分" + second + "秒";
        chapter.setDuration(length);

        // 获取音频文件大小
        long size = uploadFile.getSize();
        double l = size / 1024.0 / 1024.0;
        chapter.setChapter_size(l);
        System.out.println(l);

        System.out.println(chapter);

        try {
            chapterService.addChapter(chapter);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        //文件大小  时长  名字  url   date
    }

    @RequestMapping("/download")
    @ResponseBody
    public boolean download(String url, String title, HttpSession session, HttpServletResponse response) {

        String uploadPath = session.getServletContext().getRealPath("/datagrid/upload");  //webapp当前项目的路径
        System.out.println("uploadPath = " + uploadPath);
        String path = uploadPath + "/" + url;
        File file = new File(path);

        String s = title + "." + "mp3";


        try {
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(s, "utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return false;
        }
        response.setContentType("audio/mpeg");

        try {
            ServletOutputStream outputStream = response.getOutputStream();
            outputStream.write(FileUtils.readFileToByteArray(file));
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;

    }
}
