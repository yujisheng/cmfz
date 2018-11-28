package com.baizhi.cmfz.controller;

import com.baizhi.cmfz.entity.TurnImg;
import com.baizhi.cmfz.service.TurnImgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Map;

@Controller
@RequestMapping("/turnImg")
public class TurnImgController {

    @Autowired
    private TurnImgService turnImgService;

    @RequestMapping("/getTurnImg")
    @ResponseBody
    public Map getTurnImg(Integer page, Integer rows) {
        System.out.println("page =" + page + "\trows = " + rows);
        return turnImgService.getAllImg(page, rows);
    }

    @RequestMapping("/addImg")
    @ResponseBody
    public boolean addImg(MultipartFile uploadFile, TurnImg turnImg, HttpSession session) {
        String fileName = uploadFile.getOriginalFilename();
        fileName = new Date().getTime() + fileName;
        String realPath = session.getServletContext().getRealPath("/turn img");
        try {
            try {
                uploadFile.transferTo(new File(realPath + "\\" + fileName));
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
            turnImg.setImgPath("/turn img/" + fileName);
            turnImgService.addImg(turnImg);
            return true;
        } catch (IllegalStateException e) {
            e.printStackTrace();
            return false;
        }
    }

    @RequestMapping("/deleteMultiImg")
    @ResponseBody
    public boolean deleteMultiImg(int[] ids) {
        try {
            turnImgService.deleteMultiImg(ids);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @RequestMapping("/updateImg")
    @ResponseBody
    public boolean updateImg(TurnImg turnImg) {
        try {
            turnImgService.updateImg(turnImg);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
