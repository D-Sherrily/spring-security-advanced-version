package com.you.springsecuritydemo.filter;

import com.you.springsecuritydemo.domain.constants.Constant;
import com.you.springsecuritydemo.domain.pojo.LoginUser;
import com.you.springsecuritydemo.service.TokenService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
*
 * @ClassName: TokenFilter
 * @Description: 获取token的过滤器
 * @author: D
 * @create: 2020-06-24 15:21
 *
*/


@Component
@Slf4j
@Primary
public class TokenFilter extends OncePerRequestFilter {


    @Resource
    private TokenService tokenService;

    @Resource
    private UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
       //获取token
        String token = getToken(request);
        if (StringUtils.isNotBlank(token)){
            LoginUser loginUser = tokenService.getLoginUser(token);
            if (loginUser != null){
                loginUser = checkLoginTime(loginUser);
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(loginUser, null, loginUser.getAuthorities());
                log.info("UsernamePasswordAuthenticationToken:"+ usernamePasswordAuthenticationToken);
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }

        filterChain.doFilter(request,response);

    }



    /**
     * @Description 根据request 参数获取head 获取请求
     **/

    public static String getToken(HttpServletRequest request){
        //从参数里面获取
        String token = request.getParameter(Constant.TOKEN_KEY);
        //参数里没有  从请求体里面获取
        if (StringUtils.isBlank(token)){
            token = request.getHeader(Constant.TOKEN_KEY);
        }
        return token;
    }


    /**
     * @Description 过期时间与当前时间对比，临期十分钟内  自定刷新缓存
     **/

    private LoginUser checkLoginTime(LoginUser loginUser){
        long expireTime = loginUser.getExpireTime();
        long currentTime = System.currentTimeMillis();

        if (expireTime - currentTime <= Constant.MINUTES_10){
            String token = loginUser.getToken();
            loginUser = (LoginUser)userDetailsService.loadUserByUsername(loginUser.getUsername());
            loginUser.setToken(token);
            tokenService.refresh(loginUser);
        }
        return loginUser;
    }

}
