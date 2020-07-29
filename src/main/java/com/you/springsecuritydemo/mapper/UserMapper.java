package com.you.springsecuritydemo.mapper;


import com.you.springsecuritydemo.domain.pojo.User;

public interface UserMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);




    /**根据用户名查找用户信息**/
    User selectByUsername(String username);

    /**根据手机号查找用户信息**/
    User selectByPhone(String string);

    int updatePasswordByUsername(User user);
}