package com.wuyuncheng.xpress.model.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wuyuncheng.xpress.model.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDAO extends BaseMapper<User> {

//    @Select("SELECT * FROM user WHERE username = #{username}")
//    User findByUsername(String username);
//    @Insert("INSERT INTO user VALUES (null, #{user.username}, #{user.password}, #{user.mail}, #{user.url}, #{user.nickname}, #{user.created})")
//    int insertOne(@Param("user") User user);

}
