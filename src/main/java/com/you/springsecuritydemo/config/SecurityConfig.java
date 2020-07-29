package com.you.springsecuritydemo.config;


import com.you.springsecuritydemo.config.auth.config.MobileLoginAuthenticationSecurityConfig;
import com.you.springsecuritydemo.config.auth.config.UsernameLoginAuthenticationSecurityConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.annotation.Resource;
import java.util.List;

/**
 * security 配置
 *
 * @author ： D
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static final Logger logger = LoggerFactory.getLogger(SecurityConfig.class.getName());



    @Autowired
    private ValidateCodeSecurityConfig validateCodeSecurityConfig;
    @Autowired
    private MobileLoginAuthenticationSecurityConfig mobileLoginAuthenticationSecurityConfig;

    @Resource
    private UsernameLoginAuthenticationSecurityConfig usernameLoginAuthenticationSecurityConfig;


    public SecurityConfig() {
        logger.info("CustomSecurityConfig loading ...");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {



        //禁止csrf防护
        http.csrf().disable();
        //基于token  不需要session
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.authorizeRequests()
                .antMatchers("/", "/*.html", "/favicon.ico", "/css/**", "/js/**", "/fonts/**", "/layui/**", "/img/**",
                        "/pages/**", "/druid/**", "/statics/**").permitAll()
                .antMatchers("/login/**","/sys/**").permitAll()
                //除了上面放行的请求 ，所有请求都需要鉴权
                .anyRequest().authenticated()
                .and()
                .apply(validateCodeSecurityConfig)
                .and()
                .apply(usernameLoginAuthenticationSecurityConfig)
                .and()
                .apply(mobileLoginAuthenticationSecurityConfig);


    }


}
