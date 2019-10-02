package com.wuyuncheng.xpress.model.vo;

import com.wuyuncheng.xpress.model.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthInfo {

    private UserDTO user;
    private String token;

}
