package com.wuyuncheng.xblog.model.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Option implements Serializable {
    private String key;
    private String value;
}
