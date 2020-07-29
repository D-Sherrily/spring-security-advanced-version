package com.you.springsecuritydemo.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * 自定义异常
 *
 * @author ： D
 */
public class CustomException extends BaseException {
    public CustomException() {
    }

    public CustomException(String message, Throwable cause) {
        super(message, cause);
    }

    public CustomException(String message) {
        super(message);
    }

    public CustomException(Throwable cause) {
        super(cause);
    }
}
