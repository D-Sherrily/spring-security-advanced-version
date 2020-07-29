package com.you.springsecuritydemo.domain.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName: Token
 * @Description: token 类
 * @author: D
 * @create: 2020-07-20 16:59
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TokenDto implements Serializable {
    private static final long serialVersionUID = -7250145921118144028L;

    private String token;

    /**登录时间 （毫秒）*/
    private Long loginTime;


}
