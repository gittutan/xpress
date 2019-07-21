package com.wuyuncheng.xpress.service;

import com.wuyuncheng.xpress.model.dto.UserDetailDTO;
import com.wuyuncheng.xpress.model.entity.User;
import com.wuyuncheng.xpress.model.param.EditUserParam;
import com.wuyuncheng.xpress.model.param.LoginParam;
import com.wuyuncheng.xpress.model.param.UserParam;
import com.wuyuncheng.xpress.model.vo.AuthToken;

import java.util.List;

public interface AdminService {

    AuthToken auth(LoginParam loginParam);
    void createUser(UserParam userParam);
    List<UserDetailDTO> listUsers();
    void deleteUser(Integer userId);
    User findUser(Integer userId);
    void updateUser(EditUserParam editUserParam);

}
