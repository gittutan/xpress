package com.wuyuncheng.xblog.model.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wuyuncheng.xblog.model.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDAO extends BaseMapper<User> {

//    @Select("SELECT * FROM user WHERE username = #{username}")
//    User findByUsername(String username);
//    @Insert("INSERT INTO user VALUES (null, #{user.username}, #{user.password}, #{user.mail}, #{user.url}, #{user.nickname}, #{user.created})")
//    int insertOne(@Param("user") User user);

}
