package com.wuyuncheng.xpress.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wuyuncheng.xpress.exception.AuthException;
import com.wuyuncheng.xpress.model.dao.UserDAO;
import com.wuyuncheng.xpress.model.dto.UserDTO;
import com.wuyuncheng.xpress.model.entity.User;
import com.wuyuncheng.xpress.model.param.LoginParam;
import com.wuyuncheng.xpress.model.param.UserParam;
import com.wuyuncheng.xpress.model.vo.AuthToken;
import com.wuyuncheng.xpress.service.AdminService;
import com.wuyuncheng.xpress.util.DateUtils;
import com.wuyuncheng.xpress.util.JWTUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private UserDAO userDAO;

    @Override
    public AuthToken auth(LoginParam loginParam) {
        String username = loginParam.getUsername();
        String passwordMD5 = DigestUtils.md5DigestAsHex(loginParam.getPassword().getBytes());
        User user = userDAO.selectOne(new QueryWrapper<User>().eq("username", username));
        if (null == user) {
            throw new AuthException("用户名或密码错误");
        }
        if (!(passwordMD5.equals(user.getPassword()))) {
            throw new AuthException("用户名或密码错误");
        }
        return createToken(user.getUsername());
    }

    @Override
    public UserDTO createUser(UserParam userParam) {
        User user = new User();
        BeanUtils.copyProperties(userParam, user);
        user.setCreated(DateUtils.nowUnix());
        userDAO.insert(user);
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(user, userDTO);
        return userDTO;
    }

    private AuthToken createToken(String username) {
        String token = JWTUtils.generateToken(username);
        AuthToken authToken = new AuthToken(token);
        return authToken;
    }

}
