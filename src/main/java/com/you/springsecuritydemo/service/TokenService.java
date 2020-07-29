package com.you.springsecuritydemo.service;

import com.you.springsecuritydemo.domain.pojo.LoginUser;
import com.you.springsecuritydemo.domain.pojo.TokenDto;



public interface TokenService {

    TokenDto saveToken(LoginUser loginUser);

    void refresh(LoginUser loginUser);

    LoginUser getLoginUser(String token);

    boolean deleteToken(String token);
}
