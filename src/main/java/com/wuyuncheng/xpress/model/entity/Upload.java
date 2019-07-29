package com.wuyuncheng.xpress.model.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.io.Serializable;

@JsonPropertyOrder({"id"})
@Data
public class Upload implements Serializable {

    @JsonProperty("id")
    @TableId
    private Integer uploadId;
    private Integer authorId;
    private String filename;
    private String mimetype;
    private Long size;
    private Integer created;

}
