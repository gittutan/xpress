package com.wuyuncheng.xblog.service.impl;

import com.wuyuncheng.xblog.config.Constant;
import com.wuyuncheng.xblog.exception.LoginException;
import com.wuyuncheng.xblog.model.dao.UserDAO;
import com.wuyuncheng.xblog.model.dto.UserDTO;
import com.wuyuncheng.xblog.model.entity.User;
import com.wuyuncheng.xblog.model.param.LoginParam;
import com.wuyuncheng.xblog.model.param.UserParam;
import com.wuyuncheng.xblog.model.vo.AuthToken;
import com.wuyuncheng.xblog.service.AdminService;
import com.wuyuncheng.xblog.util.DateUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.session.Session;
import org.springframework.session.SessionRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private SessionRepository sessionRepository;
    @Autowired
    private UserDAO userDAO;

    @Override
    public AuthToken auth(LoginParam loginParam) {
        String username = loginParam.getUsername();
        String passwordMD5 = DigestUtils.md5DigestAsHex(loginParam.getPassword().getBytes());
        User user = userDAO.findByUsername(username);
        if (null == user) {
            throw new LoginException("用户名或密码错误");
        }
        if (!(passwordMD5.equals(user.getPassword()))) {
            throw new LoginException("用户名或密码错误");
        }
        return createToken(user);
    }

    @Override
    public void clearToken(String sessionId) {
        sessionRepository.deleteById(sessionId);
    }

    @Override
    public UserDTO createUser(UserParam userParam) {
        User user = new User();
        BeanUtils.copyProperties(userParam, user);
        user.setCreated(DateUtils.nowUnix());
        /**
         * Throw SQLIntegrityConstraintViolationException.class
         * Manual catch DataIntegrityViolationException.class
         */
        userDAO.insertOne(user);
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(user, userDTO);
        return userDTO;
    }

    private AuthToken createToken(User user) {
        Session session = sessionRepository.createSession();
        session.setAttribute(Constant.SESSION_KEY, user);
        sessionRepository.save(session);
        AuthToken authToken = new AuthToken();
        authToken.setAccessToken(session.getId());
        return authToken;
    }

}
