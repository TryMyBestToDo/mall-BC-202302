package com.daxiong.mall.exception;

import lombok.Getter;

/**
 * 统一异常，继承自 RuntimeException 的好处是可以不用在代码中处理
 */
@Getter
public class MallException extends RuntimeException {
    /**
     * 异常码
     */
    private final Integer code;
    /**
     * 异常信息
     */
    private final String msg;

    public MallException(Integer code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public MallException(MallExceptionEnum exceptionEnum) {
        this(exceptionEnum.getCode(), exceptionEnum.getMsg());
    }

}
