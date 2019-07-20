package com.wuyuncheng.xpress.model.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserDetailDTO implements Serializable {

    private Integer userId;
    private String username;
    private String nickname;
    private long postCount;
    private long pageCount;

}
