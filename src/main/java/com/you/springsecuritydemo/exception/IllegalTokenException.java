package com.you.springsecuritydemo.exception;

/**
 * @ClassName: IllegalTokenException
 * @Description: token 异常
 * @author: D
 * @create: 2020-07-27 14:21
 **/

public class IllegalTokenException extends BaseException {

    public IllegalTokenException() {
    }

    public IllegalTokenException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalTokenException(String message) {
        super(message);
    }

    public IllegalTokenException(Throwable cause) {
        super(cause);
    }
}
