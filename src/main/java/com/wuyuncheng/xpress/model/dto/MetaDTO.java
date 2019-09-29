package com.wuyuncheng.xpress.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wuyuncheng.xpress.model.entity.Meta;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;

@Data
public class MetaDTO implements Serializable {

    @JsonProperty("id")
    private Integer metaId;
    private String name;
    private String slug;
    private String description;
    private Integer count;

    public static MetaDTO convertFrom(Meta meta) {
        MetaDTO metaDTO = new MetaDTO();
        BeanUtils.copyProperties(meta, metaDTO);
        return metaDTO;
    }

}
