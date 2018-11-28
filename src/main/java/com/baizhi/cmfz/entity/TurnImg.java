package com.baizhi.cmfz.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Data
public class TurnImg implements Serializable {
    private Integer img_id;
    private String img_title;
    private String imgPath;
    private String img_desc;
    private String img_status;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JSONField(format = "yyyy-MM-dd")
    private Date img_date;
}
