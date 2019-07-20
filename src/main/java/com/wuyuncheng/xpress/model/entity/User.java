package com.wuyuncheng.xpress.model.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

@Data
public class User implements Serializable {

    @TableId
    private Integer userId;
    private String username;
    private String password;
    private String mail;
    private String url;
    private String nickname;
    private Integer created;

}
