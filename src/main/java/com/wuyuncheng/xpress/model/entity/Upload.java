package com.wuyuncheng.xpress.model.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

@Data
public class Upload implements Serializable {

    @TableId
    private Integer uploadId;
    private Integer postId;
    private String filename;
    private String mimetype;
    private Integer size;
    private Integer created;

}
