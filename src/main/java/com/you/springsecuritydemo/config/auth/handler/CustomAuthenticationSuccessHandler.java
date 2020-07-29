package com.you.springsecuritydemo.config.auth.handler;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.you.springsecuritydemo.domain.pojo.LoginUser;
import com.you.springsecuritydemo.domain.pojo.TokenDto;
import com.you.springsecuritydemo.service.TokenService;
import com.you.springsecuritydemo.utils.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 认证成功处理器
 *
 * @author ： D
 */
@Component
@Slf4j
public class CustomAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    private static final Logger logger = LoggerFactory.getLogger(CustomAuthenticationSuccessHandler.class.getName());
/*
    @Value("${response.type}")
    public String responseType;*/

    @Autowired
    private ObjectMapper objectMapper;

    @Resource
    private TokenService tokenService;

    public CustomAuthenticationSuccessHandler() {
        logger.info("CustomAuthenticationSuccessHandler loading ...");
    }

    /**
     * 登录成功被调用
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
         /*
         * authentication:封装认证信息（用户信息等）
         */
        logger.info("Authentication success");

        //根据认证的信息获取用户信息
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        log.info("loginUser:"+loginUser);
        //产生JWTToken
        TokenDto tokenDto = tokenService.saveToken(loginUser);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        ResponseUtil.responseJson(response, HttpStatus.OK.value(),tokenDto);

    }
}
