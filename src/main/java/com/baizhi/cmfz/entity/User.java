package com.baizhi.cmfz.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class User implements Serializable {
    private Integer user_id;
    private String phoneNum;
    private String username;
    private String password;
    private String salt;
    private String dharmaName;
    private String province;
    private String city;
    private Integer sex;
    private String sign;
    private String headPic;
    private Integer user_status;
    private Date user_date;
}
