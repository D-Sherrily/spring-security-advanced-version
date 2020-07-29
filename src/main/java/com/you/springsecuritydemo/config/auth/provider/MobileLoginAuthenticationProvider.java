package com.you.springsecuritydemo.config.auth.provider;

import com.you.springsecuritydemo.config.auth.token.MobileLoginAuthenticationToken;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

/**
 * 手机短信登录认证提供者
 *
 * @author ： D
 */
@Component
public class MobileLoginAuthenticationProvider implements AuthenticationProvider {

    private static final Logger logger = LoggerFactory.getLogger(MobileLoginAuthenticationProvider.class.getName());

    @Getter
    @Setter
    private UserDetailsService customUserDetailsService;

    public MobileLoginAuthenticationProvider() {
        logger.info("MobileLoginAuthenticationProvider loading ...");
    }

    /**
     * 认证
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        //获取过滤器封装的token信息
        MobileLoginAuthenticationToken authenticationToken = (MobileLoginAuthenticationToken) authentication;
        //获取用户信息（数据库认证）
        UserDetails userDetails = customUserDetailsService.loadUserByUsername((String) authenticationToken.getPrincipal());
        //不通过
        if (userDetails == null) {
            throw new InternalAuthenticationServiceException("Unable to obtain user information");
        }
        //通过
        MobileLoginAuthenticationToken authenticationResult = new MobileLoginAuthenticationToken(userDetails, userDetails.getAuthorities());

        authenticationResult.setDetails(authenticationToken.getDetails());

        return authenticationResult;
    }

    /**
     * 根据token类型，来判断使用哪个Provider
     */
    @Override
    public boolean supports(Class<?> authentication) {
        return MobileLoginAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
