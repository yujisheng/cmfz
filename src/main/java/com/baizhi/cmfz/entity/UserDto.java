package com.baizhi.cmfz.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserDto implements Serializable {
    private Integer value;
    private String name;
}
