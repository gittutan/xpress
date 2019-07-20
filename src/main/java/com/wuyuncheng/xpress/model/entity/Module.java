package com.wuyuncheng.xpress.model.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

@Data
public class Module implements Serializable {

    @TableId
    private Integer moduleId;
    private String name;
    private String slug;
    private String content;
    private String htmlId;
    private String type;
    private Boolean isHideTitle;

}
