package com.you.springsecuritydemo.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @ClassName: WebMvcConfig
 * @Description: 实现WebMvcConfigurer
 * @author: D
 * @create: 2020-06-23 19:27
 **/
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    /**
     *
     * @Author D
     * @Description 跨域支持
     * @Date 19:36 2020/6/23
     * @Param [registry]
     * @return void
     **/
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry
                .addMapping("/**")
                .allowedOrigins("*")
                .allowCredentials(true)
                .allowedMethods("GET","POST","DELETE","PUT")
                .maxAge(3600*24);
    }




}
