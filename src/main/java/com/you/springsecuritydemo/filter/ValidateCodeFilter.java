package com.you.springsecuritydemo.filter;


import com.you.springsecuritydemo.domain.constants.Constant;
import com.you.springsecuritydemo.domain.pojo.SmsCode;
import com.you.springsecuritydemo.exception.CustomException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * 验证码过滤器
 * <p>
 * OncePerRequestFilter确保过滤器每次只被调用一次
 *
 * @author ： D
 */
@Component
public class ValidateCodeFilter extends OncePerRequestFilter implements InitializingBean {

    private static final Logger logger = LoggerFactory.getLogger(ValidateCodeFilter.class.getName());



    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    /**
     * 验证请求url与配置的url是否匹配的工具类
     */
    private AntPathMatcher pathMatcher = new AntPathMatcher();

    public ValidateCodeFilter() {
        logger.info("Loading ValidateCodeFilter...");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String url = "/login/mobile";
        if (pathMatcher.match(url, request.getRequestURI())) {

            String codeParamName = "smsCode";

            String code = request.getParameter(codeParamName);
            if (StringUtils.isBlank(code)) {
                throw new CustomException("Not code in the parameters of the request");
            }
            String smsCode = (String) redisTemplate.boundValueOps("smsCode").get();
            if (StringUtils.isBlank(smsCode)) {
                throw new CustomException("Verification code expired");
            }
            if (StringUtils.isBlank(smsCode)) {
                throw new CustomException("Verification code does not exist");
            }
            if (StringUtils.equals(code, smsCode)) {
                redisTemplate.delete("smsCode");
                //let it go
                filterChain.doFilter(request, response);
            } else {
                throw new CustomException( "Validation code is incorrect");
            }
        }else {
            //let it go
            filterChain.doFilter(request, response);
        }
    }
}
