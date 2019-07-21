package com.wuyuncheng.xpress.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.io.Serializable;

@JsonPropertyOrder({"id"})
@Data
public class CategoryDetailDTO implements Serializable {

    @JsonProperty("id")
    private Integer metaId;
    private String name;
    private String slug;
    private String description;
    private Integer count;

}
