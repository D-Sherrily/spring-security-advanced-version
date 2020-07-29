package com.you.springsecuritydemo.domain.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName: RoleUser
 * @Description: RoleUser 实体类
 * @author: D
 * @create: 2020-06-24 9:27
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleUser implements Serializable {
    private static final long serialVersionUID = -6756723367883575079L;
    private Integer userid;

    private Integer roleid;
}