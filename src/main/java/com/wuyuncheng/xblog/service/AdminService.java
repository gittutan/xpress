package com.wuyuncheng.xblog.service;

import com.wuyuncheng.xblog.model.dto.UserDTO;
import com.wuyuncheng.xblog.model.param.LoginParam;
import com.wuyuncheng.xblog.model.param.UserParam;
import com.wuyuncheng.xblog.model.vo.AuthToken;

public interface AdminService {

    AuthToken auth(LoginParam loginParam);
    void clearToken(String sessionId);
    UserDTO createUser(UserParam userParam);

}
