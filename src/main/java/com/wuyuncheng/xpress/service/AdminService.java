package com.wuyuncheng.xpress.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wuyuncheng.xpress.model.dto.UserDTO;
import com.wuyuncheng.xpress.model.dto.UserDetailDTO;
import com.wuyuncheng.xpress.model.entity.User;
import com.wuyuncheng.xpress.model.param.LoginParam;
import com.wuyuncheng.xpress.model.param.UserParam;
import com.wuyuncheng.xpress.model.vo.AuthToken;

import java.util.List;

public interface AdminService extends IService<User> {

    AuthToken auth(LoginParam loginParam);
    void createUser(UserParam userParam);
    List<UserDetailDTO> listUsers();
    void deleteUser(Integer userId);
    UserDTO findUser(Integer userId);
    void updateUser(UserParam userParam, Integer userId);

}
