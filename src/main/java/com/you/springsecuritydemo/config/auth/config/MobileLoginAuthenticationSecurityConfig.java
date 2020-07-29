package com.you.springsecuritydemo.config.auth.config;


import com.you.springsecuritydemo.config.auth.filter.MobileLoginAuthenticationFilter;
import com.you.springsecuritydemo.config.auth.provider.MobileLoginAuthenticationProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;

/**
 * 手机短信验证码认证配置
 * 1.认证过程
 *
 *  生成未认证成功的AuthenticationToken           生成认证成功的AuthenticationToken
 *        ↑                                                 ↑
 * AuthenticationFilter  ->  AuthenticationManager -> AuthenticationProvider
 *                                                           ↓
 *                                                      UserDetails（认证）
 *
 * 2. 将AuthenticationFilter加入到security过滤链（资源服务器中配置），如：
 *  http.addFilterBefore(AuthenticationFilter, AbstractPreAuthenticatedProcessingFilter.class)
 *
 * @author ： D
 */


@Configuration
public class MobileLoginAuthenticationSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
    private static final Logger logger = LoggerFactory.getLogger(MobileLoginAuthenticationSecurityConfig.class.getName());

    @Resource
    private UserDetailsService userDetailsServiceImpl;
    @Autowired
    private AuthenticationSuccessHandler customAuthenticationSuccessHandler;
    @Autowired
    private AuthenticationFailureHandler customAuthenticationFailureHandler;


    public MobileLoginAuthenticationSecurityConfig() {
        logger.info("MobileLoginAuthenticationSecurityConfig loading ...");
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {

        //创建并配置好自定义 MobileLoginAuthenticationFilter
        //手机号登录请求的URI  ,  请求参数的名字   ， 请求方式
        MobileLoginAuthenticationFilter mobileLoginAuthenticationFilter =
                new MobileLoginAuthenticationFilter("/login/mobile", "mobile","POST");
        //设置AuthenticationManager
        mobileLoginAuthenticationFilter.setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));
        mobileLoginAuthenticationFilter.setAuthenticationSuccessHandler(customAuthenticationSuccessHandler);
        mobileLoginAuthenticationFilter.setAuthenticationFailureHandler(customAuthenticationFailureHandler);

        //创建并配置好自定义 MobileLoginAuthenticationProvider
        MobileLoginAuthenticationProvider mobileLoginAuthenticationProvider = new MobileLoginAuthenticationProvider();
        mobileLoginAuthenticationProvider.setCustomUserDetailsService(userDetailsServiceImpl);
        //将过滤器添加到过滤链路中
        http.authenticationProvider(mobileLoginAuthenticationProvider)
                .addFilterAfter(mobileLoginAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
