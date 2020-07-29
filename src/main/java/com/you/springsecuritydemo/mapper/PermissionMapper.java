package com.you.springsecuritydemo.mapper;

import com.you.springsecuritydemo.domain.pojo.Permission;

import java.util.List;

public interface PermissionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Permission record);

    int insertSelective(Permission record);

    Permission selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Permission record);

    int updateByPrimaryKey(Permission record);


    /**
     * @Description  通过用户ID 查找用户对应的权限
     **/
    List<Permission> selectPermissionByUserId(Integer userId);


    List<Permission> selectAll();
}