package com.wuyuncheng.xblog.model.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class User implements Serializable {
    private Integer userId;
    private String username;
    private String password;
    private String mail;
    private String url;
    private String nickname;
    private Integer created;
}
