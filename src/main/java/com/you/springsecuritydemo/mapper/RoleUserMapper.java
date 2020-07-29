package com.you.springsecuritydemo.mapper;

import com.you.springsecuritydemo.domain.pojo.RoleUser;
import org.apache.ibatis.annotations.Param;

public interface RoleUserMapper {
    int deleteByPrimaryKey(@Param("userid") Integer userid, @Param("roleid") Integer roleid);

    int insert(RoleUser record);

    int insertSelective(RoleUser record);
}