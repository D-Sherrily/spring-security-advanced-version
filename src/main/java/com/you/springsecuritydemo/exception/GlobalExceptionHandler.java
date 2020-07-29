package com.you.springsecuritydemo.exception;

import com.you.springsecuritydemo.domain.pojo.Resp;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName: ExceptionHandlerAdvice
 * @Description: 全局 异常处理
 * @author: D
 * @create: 2020-06-24 13:43
 **/
@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(value = IllegalTokenException.class)
    public Resp exceptionHandler(Exception e){
        Resp<Object> exceptionResp = new Resp<>();

        exceptionResp.setMsg(e.getMessage());
        exceptionResp.setData(null);
        exceptionResp.setStatus(500);
        return exceptionResp;
    }

    @ResponseBody
    @ExceptionHandler(CustomException.class)
    public Resp handlerNotExistException(CustomException e, HttpServletResponse response) {
        Resp<Object> exceptionResp = new Resp<>();

        exceptionResp.setMsg(e.getMessage());
        exceptionResp.setData(null);
        exceptionResp.setStatus(400);
        return exceptionResp;
    }
}
