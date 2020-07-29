package com.you.springsecuritydemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

/**
 * @ClassName: LoginPageController
 * @Description: 登录的跳转页面
 * @author: D
 * @create: 2020-07-20 17:14
 **/

@Controller
public class LoginPageController {

    @RequestMapping("/")
    public RedirectView loginPage(){
        return new RedirectView("/login.html");
    }
}
