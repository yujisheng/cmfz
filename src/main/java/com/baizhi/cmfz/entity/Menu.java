package com.baizhi.cmfz.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class Menu implements Serializable {
    private Integer menu_id;
    private String menu_title;
    private String menu_iconCls;
    private Integer menu_pid;
    private String menu_url;
    private List<Menu> menus;
}
