package com.you.springsecuritydemo.domain.pojo;

import java.io.Serializable;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName: Token
 * @Description: token 类
 * @author: D
 * @create: 2020-07-20 16:59
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Token implements Serializable {
    private static final long serialVersionUID = 6031106172137124878L;
    /**
    * token
    */
    private String id;

    /**
    * LoginUser的json串
    */
    private String val;

    private Date expiretime;

    private Date createtime;

    private Date updatetime;
}