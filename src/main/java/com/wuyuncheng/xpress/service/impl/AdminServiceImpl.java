package com.wuyuncheng.xpress.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wuyuncheng.xpress.exception.AlreadyExistsException;
import com.wuyuncheng.xpress.exception.AuthException;
import com.wuyuncheng.xpress.exception.NotFoundException;
import com.wuyuncheng.xpress.model.dao.UserDAO;
import com.wuyuncheng.xpress.model.dto.UserDTO;
import com.wuyuncheng.xpress.model.entity.Post;
import com.wuyuncheng.xpress.model.entity.User;
import com.wuyuncheng.xpress.model.param.LoginParam;
import com.wuyuncheng.xpress.model.param.UserParam;
import com.wuyuncheng.xpress.model.vo.AuthInfo;
import com.wuyuncheng.xpress.service.AdminService;
import com.wuyuncheng.xpress.service.PostService;
import com.wuyuncheng.xpress.util.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.DigestUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Service
public class AdminServiceImpl extends ServiceImpl<UserDAO, User> implements AdminService {

    @Autowired
    private UserDAO userDAO;
    @Autowired
    private PostService postService;

    @Override
    public AuthInfo getToken(LoginParam loginParam) {
        String username = loginParam.getUsername();
        String passwordMD5 = DigestUtils.md5DigestAsHex(loginParam.getPassword().getBytes());
        User user = userDAO.selectOne(
                new QueryWrapper<User>().eq("username", username)
        );
        if (null == user || !(passwordMD5.equals(user.getPassword()))) {
            throw new AuthException("用户名或密码错误");
        }
        return createToken(user);
    }

    @Transactional
    @Override
    public void createUser(UserParam userParam) {
        userMustNotExist(userParam.getUsername());

        User user = userParam.convertTo();
        int row = userDAO.insert(user);
        Assert.state(row != 0, "用户创建失败");
    }

    @Override
    public List<UserDTO> listUsers() {
        List<User> users = userDAO.selectList(null);
        List<UserDTO> userDTOList = new ArrayList<>();
        for (User user : users) {
            UserDTO userDTO = UserDTO.convertFrom(user);
            userDTOList.add(userDTO);
        }
        return userDTOList;
    }

    @Transactional
    @Override
    public void removeUser(Integer userId) {
        userMustExist(userId);

        // 删除该用户发布的所有文章
        postService.remove(
                new QueryWrapper<Post>()
                        .eq("author_id", userId)
        );

        int row = userDAO.deleteById(userId);
        Assert.state(row != 0, "用户删除失败");
    }

    @Override
    public UserDTO getUser(Integer userId) {
        User user = userDAO.selectById(userId);
        if (null == user) {
            throw new NotFoundException("该用户不存在");
        }
        return UserDTO.convertFrom(user);
    }

    @Transactional
    @Override
    public void updateUser(UserParam userParam, Integer userId) {
        userMustExist(userId);

        User user = userParam.convertTo();
        user.setUserId(userId);
        int row = userDAO.updateById(user);
        Assert.state(row != 0, "用户更新失败");
    }

    /**
     * 该用户不存在时抛出异常
     */
    private User userMustExist(Serializable idOrName) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>()
                .eq("user_id", idOrName)
                .or()
                .eq("username", idOrName);
        User user = userDAO.selectOne(queryWrapper);
        if (null == user) {
            throw new NotFoundException("该用户不存在");
        }
        return user;
    }

    /**
     * 该用户存在时抛出异常
     */
    private void userMustNotExist(Serializable idOrName) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>()
                .eq("user_id", idOrName)
                .or()
                .eq("username", idOrName);
        if (null != userDAO.selectOne(queryWrapper)) {
            throw new AlreadyExistsException("该用户已存在");
        }
    }

    private AuthInfo createToken(User user) {
        String token = JWTUtils.generateToken(user);
        UserDTO userDTO = UserDTO.convertFrom(user);
        return new AuthInfo(userDTO, token);
    }

}
