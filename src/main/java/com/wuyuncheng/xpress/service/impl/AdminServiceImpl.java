package com.wuyuncheng.xpress.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wuyuncheng.xpress.exception.AlreadyExistsException;
import com.wuyuncheng.xpress.exception.AuthException;
import com.wuyuncheng.xpress.exception.NotFoundException;
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
import com.wuyuncheng.xpress.service.PostService;
import com.wuyuncheng.xpress.util.DateUtils;
import com.wuyuncheng.xpress.util.JWTUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.DigestUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private UserDAO userDAO;
    @Autowired
    private PostService postService;

    @Override
    public AuthToken auth(LoginParam loginParam) {
        String username = loginParam.getUsername();
        String passwordMD5 = DigestUtils.md5DigestAsHex(loginParam.getPassword().getBytes());
        User user = userDAO.selectOne(new QueryWrapper<User>().eq("username", username));
        if (null == user || !(passwordMD5.equals(user.getPassword()))) {
            throw new AuthException("用户名或密码错误");
        }
        return createToken(user.getUsername());
    }

    @Override
    public void createUser(UserParam userParam) {
        userMustNotExist(userParam.getUsername());

        User user = new User();
        BeanUtils.copyProperties(userParam, user);
        user.setCreated(DateUtils.nowUnix());
        String password = user.getPassword();
        String passwordMD5 = DigestUtils.md5DigestAsHex(password.getBytes());
        user.setPassword(passwordMD5);
        int row = userDAO.insert(user);
        Assert.state(row != 0, "用户创建失败");
    }

    @Override
    public List<UserDetailDTO> listUsers() {
        List<User> users = userDAO.selectList(null);
        List<Post> posts = postService.list();
        return convertToUserDetailDTOList(users, posts);
    }

    @Override
    public void deleteUser(Integer userId) {
        userMustExist(userId);

        int row = userDAO.deleteById(userId);
        Assert.state(row != 0, "用户删除失败");
    }

    @Override
    public UserDTO findUser(Integer userId) {
        User user = userDAO.selectById(userId);
        if (null == user) {
            throw new NotFoundException("该用户不存在");
        }
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(user, userDTO);
        return userDTO;
    }

    @Override
    public void updateUser(EditUserParam editUserParam, Integer userId) {
        userMustExist(userId);

        User user = new User();
        BeanUtils.copyProperties(editUserParam, user);
        String password = user.getPassword();
        String passwordMD5 = DigestUtils.md5DigestAsHex(password.getBytes());
        user.setPassword(passwordMD5);
        user.setUserId(userId);
        int row = userDAO.updateById(user);
        Assert.state(row != 0, "用户更新失败");
    }

    /**
     * 该用户不存在时抛出异常
     */
    private void userMustExist(Serializable idOrName) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>()
                .eq("user_id", idOrName)
                .or()
                .eq("username", idOrName);
        if (null == userDAO.selectOne(queryWrapper)) {
            throw new NotFoundException("该用户不存在");
        }
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

    private AuthToken createToken(String username) {
        String token = JWTUtils.generateToken(username);
        return new AuthToken(token);
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
