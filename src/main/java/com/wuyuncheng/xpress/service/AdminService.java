package com.wuyuncheng.xpress.service;

import com.wuyuncheng.xpress.model.dto.UserDTO;
import com.wuyuncheng.xpress.model.param.LoginParam;
import com.wuyuncheng.xpress.model.param.UserParam;
import com.wuyuncheng.xpress.model.vo.AuthToken;

public interface AdminService {

    AuthToken auth(LoginParam loginParam);
    UserDTO createUser(UserParam userParam);

}
