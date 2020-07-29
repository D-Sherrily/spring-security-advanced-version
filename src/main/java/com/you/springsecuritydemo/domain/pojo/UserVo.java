package com.you.springsecuritydemo.domain.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName: UserDto
 * @Description: userVo
 * @author: D
 * @create: 2020-07-22 15:09
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserVo implements Serializable {
    private static final long serialVersionUID = -5706829415062950205L;

    private Integer id;

    private String username;
}
