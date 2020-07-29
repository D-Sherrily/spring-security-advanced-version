package com.you.springsecuritydemo.mapper;

import com.you.springsecuritydemo.domain.pojo.RolePermission;
import org.apache.ibatis.annotations.Param;

public interface RolePermissionMapper {
    int deleteByPrimaryKey(@Param("roleid") Integer roleid, @Param("permissionid") Integer permissionid);

    int insert(RolePermission record);

    int insertSelective(RolePermission record);
}