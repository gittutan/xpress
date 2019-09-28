package com.wuyuncheng.xpress.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.io.Serializable;

@JsonPropertyOrder({"id"})
@Data
public class UserDTO implements Serializable {

    @JsonProperty("id")
    private Integer userId;
    private String username;
    private String mail;
    private String url;
    private String nickname;

}
