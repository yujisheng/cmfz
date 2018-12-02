package com.baizhi.cmfz.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.baizhi.cmfz.entity.User;
import com.baizhi.cmfz.service.UserService;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.NoSuchElementException;

@Controller
@RequestMapping("/exprotAndImport")
public class ExprotAndImporController {

    @Autowired
    private UserService userService;

    @RequestMapping("/exportExcel")
    public void exprotExcel(HttpServletResponse response) throws UnsupportedEncodingException {
        //System.out.println("==========================");
        try {
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode("用户信息.xls", "utf-8"));
            List<User> userList = userService.getAll();
            Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("持名法洲用户信息", "用户信息"), User.class, userList);
            workbook.write(response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/importExcel")
    public void importExcel(MultipartFile importFile, Integer titleRows, Integer headerRows) {
        /*if (importFile == null){
            return null;
        }*/
        ImportParams params = new ImportParams();
        // 表格标题行数,默认0
        params.setTitleRows(titleRows);
        //表头行数,默认1
        params.setHeadRows(headerRows);
        List<User> userList = null;
        try {
            userList = ExcelImportUtil.importExcel(importFile.getInputStream(), User.class, params);
        } catch (NoSuchElementException e) {
            //throw new NormalException("excel文件不能为空");
        } catch (Exception e) {
            //throw new NormalException(e.getMessage());
        }

        for (User user : userList) {
            System.out.println("user = " + user);
        }
    }
}
