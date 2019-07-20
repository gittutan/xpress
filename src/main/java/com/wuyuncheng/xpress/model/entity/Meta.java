package com.wuyuncheng.xpress.model.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

@Data
public class Meta implements Serializable {

    @TableId
    private Integer metaId;
    private String name;
    private String slug;
    private String type;
    private String description;
    private Integer count;

}
