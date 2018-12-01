package com.baizhi.cmfz.controller;

import com.baizhi.cmfz.entity.Chapter;
import com.baizhi.cmfz.service.ChapterService;
import com.baizhi.cmfz.util.FileUtil;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.DecimalFormat;
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

        /*// 获取文件扩展名.的下标
        int a = uploadFile.getOriginalFilename().lastIndexOf(".");
        // 获取文件的扩展名
        String extension1= uploadFile.getOriginalFilename().substring(a);*/

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
        DecimalFormat df = new DecimalFormat("#.00");
        String format = df.format(size / 1024.0 / 1024.0);
        chapter.setChapter_size(format + "M");
        System.out.println(format);

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

    /*@RequestMapping("/download")
    public void download(String url, String title, HttpSession session, HttpServletResponse response) {

        System.out.println(url+"====="+title);

        String uploadPath = session.getServletContext().getRealPath("/");  //webapp当前项目的路径
        System.out.println("uploadPath = " + uploadPath);
        String path = uploadPath+url;
        System.out.println("path = " + path);
        File file = new File(path);
        System.out.println(file.getName());

        String s = title + "." + "mp3";


        try {
            // 设置文件类型
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(s, "utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        response.setContentType("audio/mpeg");

        try {
            ServletOutputStream outputStream = response.getOutputStream();
            outputStream.write(FileUtils.readFileToByteArray(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/

    @RequestMapping("/download")
    public ResponseEntity<byte[]> export(String title, String url, HttpSession session) throws IOException {

        //webapp当前项目的路径
        String uploadPath = session.getServletContext().getRealPath("/");
        // 获取文件路径
        String path = uploadPath + url;
        // 将下载的文件放在一个对象中
        File file = new File(path);
        // 获取下载文件扩展名
        String extension = FilenameUtils.getExtension(file.getName());

        String fileName = title + "." + extension;
        System.out.println(fileName);
        //fileName=new String(fileName.getBytes("gbk"),"iso8859-1");//防止中文乱码

        // 设置响应的文件类型和下载文件名（设置编码集。防止中文乱码）
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", URLEncoder.encode(fileName, "utf-8"));
        //headers.add("Content-Disposition", "attchement;filename=" + URLEncoder.encode(fileName, "utf-8"));

        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),
                headers, HttpStatus.CREATED);
    }
}
