package com.wuyuncheng.xpress.service;

import com.wuyuncheng.xpress.model.dto.UserDTO;
import com.wuyuncheng.xpress.model.dto.UserDetailDTO;
import com.wuyuncheng.xpress.model.entity.User;
import com.wuyuncheng.xpress.model.param.EditUserParam;
import com.wuyuncheng.xpress.model.param.LoginParam;
import com.wuyuncheng.xpress.model.param.UserParam;
import com.wuyuncheng.xpress.model.vo.AuthToken;

import java.util.List;

public interface AdminService {

    AuthToken auth(LoginParam loginParam);
    UserDTO createUser(UserParam userParam);
    List<UserDetailDTO> listUserDetail();
    void deleteUser(Integer userId);
    User findUser(Integer userId);
    int updateUser(EditUserParam editUserParam);

}
