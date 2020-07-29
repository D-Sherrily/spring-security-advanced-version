package com.you.springsecuritydemo.domain.pojo;

import java.io.Serializable;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName: Role
 * @Description: Role 实体类
 * @author: D
 * @create: 2020-06-24 9:27
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role implements Serializable {
    private static final long serialVersionUID = -5272912296259776844L;
    private Integer id;

    private String name;

    private String description;

    private Date createtime;

    private Date updatetime;
}