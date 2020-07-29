package com.you.springsecuritydemo.exception;

/**
 * @ClassName: BaseException
 * @Description: BaseException
 * @author: D
 * @create: 2020-07-27 14:19
 **/

public class BaseException extends RuntimeException {

    public BaseException() {
        super();
    }

    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public BaseException(String message) {
        super(message);
    }

    public BaseException(Throwable cause) {
        super(cause);
    }

}
