package com.you.springsecuritydemo.controller;


import com.you.springsecuritydemo.domain.constants.Constant;
import com.you.springsecuritydemo.domain.pojo.Resp;
import com.you.springsecuritydemo.domain.pojo.SmsCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * 接口
 *
 * @author ： D
 * @date ：Create in 22:48 2017/12/20
 */
@RestController
public class ValidateCodeController {

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    private static final Logger logger = LoggerFactory.getLogger(ValidateCodeController.class.getName());

    @GetMapping("/sys/mobile" + "/{mobile}")
    public Resp sendCode(HttpServletRequest request, @PathVariable String mobile) {
        StringBuilder sb = new StringBuilder();
        //随机产生校验码
        for (int i = 0; i < 4; i++) {
            char c = (char) (new Random().nextInt(10) + '0');
            sb.append(String.valueOf(c));
        }

        redisTemplate.boundValueOps("smsCode").set(sb,Constant.SMS_CODE_TIME, TimeUnit.SECONDS);

        logger.info("向手机：" + mobile + ",发送验证码：[" + sb + "]");
        Resp<Object> resp = new Resp<>();
        resp.setMsg("send code success");
        return resp;
    }

}
