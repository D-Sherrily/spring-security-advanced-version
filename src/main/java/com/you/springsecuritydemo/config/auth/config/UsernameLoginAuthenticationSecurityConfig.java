package com.you.springsecuritydemo.config.auth.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
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
public class UsernameLoginAuthenticationSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
    private static final Logger logger = LoggerFactory.getLogger(UsernameLoginAuthenticationSecurityConfig.class.getName());

    @Resource
    private UserDetailsService userDetailsServiceImpl;
    @Autowired
    private AuthenticationSuccessHandler customAuthenticationSuccessHandler;
    @Autowired
    private AuthenticationFailureHandler customAuthenticationFailureHandler;


    public UsernameLoginAuthenticationSecurityConfig() {
        logger.info("UsernameLoginAuthenticationSecurityConfig loading ...");
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {

        //创建并配置好自定义 UsernamePasswordAuthenticationFilter
        UsernamePasswordAuthenticationFilter filter = new UsernamePasswordAuthenticationFilter();

        filter.setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));
        //自定义用户名登录的URI
        filter.setFilterProcessesUrl("/login/username");
        filter.setAuthenticationSuccessHandler(customAuthenticationSuccessHandler);
        filter.setAuthenticationFailureHandler(customAuthenticationFailureHandler);

        //创建并配置好  DaoAuthenticationProvider
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsServiceImpl);
        //配置密码的加密方式
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        //将过滤器添加到过滤链路中
        http.authenticationProvider(daoAuthenticationProvider)
                .addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
    }
}
