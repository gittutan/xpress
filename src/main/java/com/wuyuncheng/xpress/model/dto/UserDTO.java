package com.wuyuncheng.xpress.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wuyuncheng.xpress.model.entity.User;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;

@Data
public class UserDTO implements Serializable {

    @JsonProperty("id")
    private Integer userId;
    private String username;
    private String mail;
    private String url;
    private String nickname;

    public static UserDTO convertFrom(User user) {
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(user, userDTO);
        return userDTO;
    }

}
