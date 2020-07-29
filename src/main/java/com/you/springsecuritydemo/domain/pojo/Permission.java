package com.you.springsecuritydemo.domain.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName: Permission
 * @Description: Permission 实体类
 * @author: D
 * @create: 2020-06-24 9:27
 **/


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Permission implements Serializable {
    private static final long serialVersionUID = -109732883519751381L;
    private Integer id;

    private Integer parentid;

    private String name;

    private String css;

    private String href;

    private Boolean type;

    private String permission;

    private Integer sort;
}