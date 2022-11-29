package com.south.study.common.core.exception;

import com.south.study.common.core.constant.BasicResponseCode;

/**
 * 业务异常
 *
 * @author YueJiaJun
 * @version 0.0.1
 * @date 2022年03月09日 17:36 星期三
 * @since JDK_1.8.0.271
 */
public class BusinessesException extends RuntimeException {

    private final int code;

    private final String message;

    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public BusinessesException(BasicResponseCode code) {
        super();
        this.code = code.key();
        this.message = code.value();
    }

    public BusinessesException(String message) {
        super();
        this.code = 500;
        this.message = message;
    }

    public BusinessesException(String message, int code) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public BusinessesException(String message, Throwable cause, int code) {
        super(message, cause);
        this.code = code;
        this.message = message;
    }

    public BusinessesException(Throwable cause, int code, String message) {
        super(cause);
        this.code = code;
        this.message = message;
    }

    public BusinessesException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, int code) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.code = code;
        this.message = message;
    }

}
