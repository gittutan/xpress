package com.wuyuncheng.xpress.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.io.Serializable;

@JsonPropertyOrder({"id"})
@Data
public class UploadDetailDTO implements Serializable {

    @JsonProperty("id")
    private Integer uploadId;
    private String username;
    private String filename;
    private String mimetype;
    private Long size;
    private Integer created;

}
