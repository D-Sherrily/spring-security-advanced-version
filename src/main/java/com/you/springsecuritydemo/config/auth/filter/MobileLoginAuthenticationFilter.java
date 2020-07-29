package com.you.springsecuritydemo.config.auth.filter;

import com.you.springsecuritydemo.config.auth.token.MobileLoginAuthenticationToken;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 手机短信登录过滤器
 *
 * @author ： CatalpaFlatCatalpaFlat
 */

public class MobileLoginAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    private boolean postOnly = true;
    private static final Logger logger = LoggerFactory.getLogger(MobileLoginAuthenticationFilter.class.getName());

    @Getter
    @Setter
    private String mobileParameterName;

    public MobileLoginAuthenticationFilter(String mobileLoginUrl, String mobileParameterName,
                                           String httpMethod) {
        super(new AntPathRequestMatcher(mobileLoginUrl, httpMethod));
        this.mobileParameterName = mobileParameterName;
        logger.info("MobileLoginAuthenticationFilter loading ...");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException, IOException, ServletException {

        if (postOnly && !request.getMethod().equals(HttpMethod.POST.name())) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        }

        //获取手机号
        String mobile = obtainMobile(request);

        //获取 token
        MobileLoginAuthenticationToken authRequest = new MobileLoginAuthenticationToken(mobile);

        // Allow subclasses to set the "details" property
        setDetails(request, authRequest);

        return this.getAuthenticationManager().authenticate(authRequest);
    }
    /**
     * 设置身份认证的详情信息
     */
    private void setDetails(HttpServletRequest request, MobileLoginAuthenticationToken authRequest) {
        authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
    }
    /**
     * 获取手机号
     */
    private String obtainMobile(HttpServletRequest request) {
        return request.getParameter(mobileParameterName);
    }

    /**
     * 只允许POST请求
     **/
    public void setPostOnly(boolean postOnly) {
        this.postOnly = postOnly;
    }
}
