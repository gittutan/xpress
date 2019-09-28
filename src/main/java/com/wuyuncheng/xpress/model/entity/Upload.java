package com.wuyuncheng.xpress.model.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

@Data
public class Upload implements Serializable {

    @TableId
    private Integer uploadId;
    private Integer authorId;
    private String filename;
    private String mimetype;
    private Long size;
    private Integer created;

}
