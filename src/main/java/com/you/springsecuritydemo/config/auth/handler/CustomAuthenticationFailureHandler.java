package com.you.springsecuritydemo.config.auth.handler;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.you.springsecuritydemo.domain.pojo.Resp;
import com.you.springsecuritydemo.utils.ResponseUtil;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自定义失败处理器
 *
 * @author ： D
 */
@Component
public class CustomAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {
    private static final Logger logger = LoggerFactory.getLogger(CustomAuthenticationFailureHandler.class.getName());




    public CustomAuthenticationFailureHandler() {
        logger.info("CustomAuthenticationFailureHandler loading ...");
    }

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {
        /*
         * exception：错误信息（认证过程中的错误）
         */
        logger.error("Authentication does not pass");


        Resp<String> resp = new Resp<>();
        resp.setMsg(exception.getMessage());
        resp.setStatus(HttpStatus.UNAUTHORIZED.value());
        ResponseUtil.responseJson(response,HttpStatus.UNAUTHORIZED.value(),resp);


    }
}
