package com.you.springsecuritydemo.domain.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName: RolePermission
 * @Description: RolePermission 实体类
 * @author: D
 * @create: 2020-06-24 9:27
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RolePermission implements Serializable {
    private static final long serialVersionUID = 838806703606481891L;
    private Integer roleid;

    private Integer permissionid;
}