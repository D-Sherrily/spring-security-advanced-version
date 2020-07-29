package com.you.springsecuritydemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *
 * @ClassName: SpringSecurityDemoApplication
 * @Description: 启动类
 * @author: D
 * @create: 2020-06-23 16:38
 **/

@SpringBootApplication
@MapperScan("com.you.springsecuritydemo.mapper")
public class SpringSecurityDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityDemoApplication.class, args);
    }

}
