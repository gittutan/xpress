package com.wuyuncheng.xblog.model.dto;

import lombok.Data;

@Data
public class UserDTO {
    private String username;
    private String mail;
    private String url;
    private String nickname;
    private Integer created;
}
