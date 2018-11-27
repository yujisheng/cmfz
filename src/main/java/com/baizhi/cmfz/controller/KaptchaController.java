package com.baizhi.cmfz.controller;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

@Controller
public class KaptchaController {

    @Autowired
    private DefaultKaptcha defaultKaptcha;

    @RequestMapping("/getKaptcha")
    public void getkaptcha(HttpSession session, HttpServletResponse response) throws IOException {
        // 生成文本
        String text = defaultKaptcha.createText();
        // 将生成的文本放到session空间中
        session.setAttribute("kaptcha", text);
        // 将生成的文本放入到图片中
        BufferedImage image = defaultKaptcha.createImage(text);
        // 将生成的验证码图片以流的方式响应出去
        ImageIO.write(image, "jpg", response.getOutputStream());
    }
}
