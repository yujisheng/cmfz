package com.baizhi.cmfz.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelIgnore;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
    @ExcelIgnore
    private Integer user_id;
    @Excel(name = "电话号码", orderNum = "1", width = 20)
    private String phoneNum;
    @Excel(name = "用户名", orderNum = "0", width = 15)
    private String username;
    @Excel(name = "密码", orderNum = "2")
    private String password;
    @Excel(name = "盐值", orderNum = "3")
    private String salt;
    @Excel(name = "法号", orderNum = "4")
    private String dharmaName;
    @Excel(name = "省份", orderNum = "5")
    private String province;
    @Excel(name = "城市", orderNum = "6")
    private String city;
    @Excel(name = "性别", orderNum = "7", replace = {"男_1", "女_0"}, isImportField = "true_st")
    private Integer sex;
    @Excel(name = "个性签名", orderNum = "8", width = 15)
    private String sign;
    @Excel(name = "头像", orderNum = "9")
    private String headPic;
    @Excel(name = "用户状态", orderNum = "10", replace = {"冻结_1", "正常_0"}, isImportField = "true_st")
    private Integer user_status;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JSONField(format = "yyyy-MM-dd")
    @Excel(name = "注册日期", orderNum = "11", databaseFormat = "yyyyMMddHHmmss", format = "yyyy-MM-dd")
    private Date user_date;
}
