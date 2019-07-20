package com.wuyuncheng.xpress.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wuyuncheng.xpress.exception.AlreadyExistsException;
import com.wuyuncheng.xpress.exception.AuthException;
import com.wuyuncheng.xpress.model.dao.PostDAO;
import com.wuyuncheng.xpress.model.dao.UserDAO;
import com.wuyuncheng.xpress.model.dto.UserDTO;
import com.wuyuncheng.xpress.model.dto.UserDetailDTO;
import com.wuyuncheng.xpress.model.entity.Post;
import com.wuyuncheng.xpress.model.entity.User;
import com.wuyuncheng.xpress.model.enums.PostType;
import com.wuyuncheng.xpress.model.param.EditUserParam;
import com.wuyuncheng.xpress.model.param.LoginParam;
import com.wuyuncheng.xpress.model.param.UserParam;
import com.wuyuncheng.xpress.model.vo.AuthToken;
import com.wuyuncheng.xpress.service.AdminService;
import com.wuyuncheng.xpress.util.DateUtils;
import com.wuyuncheng.xpress.util.JWTUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.DigestUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private UserDAO userDAO;
    @Autowired
    private PostDAO postDAO;

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
        // 检查用户名是否已存在
        String username = userParam.getUsername();
        if (null != userDAO.selectOne(new QueryWrapper<User>().eq("username", username))) {
            throw new AlreadyExistsException("该用户已存在");
        }
        User user = new User();
        BeanUtils.copyProperties(userParam, user);
        user.setCreated(DateUtils.nowUnix());
        String password = user.getPassword();
        String passwordMD5 = DigestUtils.md5DigestAsHex(password.getBytes());
        user.setPassword(passwordMD5);
        userDAO.insert(user);
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(user, userDTO);
        return userDTO;
    }

    @Override
    public List<UserDetailDTO> listUserDetail() {
        List<User> users = userDAO.selectList(null);
        List<Post> posts = postDAO.selectList(null);
        return convertToUserDetailDTOList(users, posts);
    }

    @Override
    public void deleteUser(Integer userId) {
        Assert.notNull(userDAO.selectById(userId), "没有此用户");
        userDAO.deleteById(userId);
    }

    @Override
    public User findUser(Integer userId) {
        User user = userDAO.selectById(userId);
        Assert.notNull(user, "没有此用户");
        user.setPassword("");
        return user;
    }

    @Override
    public int updateUser(EditUserParam editUserParam) {
        User user = new User();
        BeanUtils.copyProperties(editUserParam, user);
        String password = user.getPassword();
        String passwordMD5 = DigestUtils.md5DigestAsHex(password.getBytes());
        user.setPassword(passwordMD5);
        return userDAO.updateById(user);
    }

    private AuthToken createToken(String username) {
        String token = JWTUtils.generateToken(username);
        AuthToken authToken = new AuthToken(token);
        return authToken;
    }

    private List<UserDetailDTO> convertToUserDetailDTOList(List<User> users, List<Post> posts) {
        List<UserDetailDTO> userDetailDTOList = new ArrayList<>();
        users.stream()
                .forEach(user -> {
                    UserDetailDTO userDetailDTO = new UserDetailDTO();
                    BeanUtils.copyProperties(user, userDetailDTO);

                    Integer userId = userDetailDTO.getUserId();
                    // 计算 postCount
                    long postCount = posts.stream()
                            .filter(post ->
                                    post.getAuthorId().equals(userId) && post.getType().equals(PostType.POST.getValue())
                            )
                            .count();
                    userDetailDTO.setPostCount(postCount);
                    // 计算 pageCount
                    long pageCount = posts.stream()
                            .filter(post ->
                                    post.getAuthorId().equals(userId) && post.getType().equals(PostType.PAGE.getValue())
                            )
                            .count();
                    userDetailDTO.setPageCount(pageCount);

                    userDetailDTOList.add(userDetailDTO);
                });
        return userDetailDTOList;
    }

}
