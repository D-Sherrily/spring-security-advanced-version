package com.you.springsecuritydemo.domain.pojo;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 短信验证码
 *
 * @author ： CatalpaFlat
 * @date ：Create in 16:58 2017/12/21
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SmsCode implements Serializable {


    protected String code;

    protected int expireTime;

}
