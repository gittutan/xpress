package com.wuyuncheng.xblog.model.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AuthToken {

    @JsonProperty("access_token")
    private String accessToken;

}
