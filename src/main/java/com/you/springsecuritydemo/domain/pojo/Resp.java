package com.you.springsecuritydemo.domain.pojo;

import io.swagger.models.auth.In;
import lombok.Data;

/**
 * @ClassName: Resp
 * @Description:
 * @author: D
 * @create: 2020-07-29 15:37
 **/
@Data
public class Resp <T>{
    private Integer status = 200;
    private String msg = "";
    private T data = null;
}
