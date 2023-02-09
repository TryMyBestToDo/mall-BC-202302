package com.daxiong.mall.exception;

import com.daxiong.mall.common.ApiRestResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

/**
 * 处理统一异常 handler，使异常在客户端展示时使用指定格式。
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    // 日志
    private final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 将系统异常转换为ApiRestResponse异常输出
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    public ApiRestResponse handlerSystemException(Exception e) {
        log.error("Default Exception: ", e);
        return ApiRestResponse.error(MallExceptionEnum.SYSTEM_ERROR);
    }

    /**
     * 将业务异常转换为ApiRestResponse异常输出
     * @param e
     * @return
     */
    @ExceptionHandler(MallException.class)
    public ApiRestResponse handlerSystemException(MallException e) {
        log.error("MallException: ", e);
        return ApiRestResponse.error(e.getCode(), e.getMsg());
    }

    /**
     * 将 hibernate-validator 数据校验的异常统一处理
     * @param e
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiRestResponse handlerMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error("MethodArgumentNotValidException: ", e);
        BindingResult result = e.getBindingResult();
        List<String> msg = new ArrayList<>();
        if (result.hasErrors()) {
            List<ObjectError> allErrors = result.getAllErrors();
            for (ObjectError error : allErrors)
                msg.add(error.getDefaultMessage());
        }
        if (msg.size() == 0)
            return ApiRestResponse.error(MallExceptionEnum.REQUEST_PARAM_ERROR);

        return ApiRestResponse.error(MallExceptionEnum.REQUEST_PARAM_ERROR.getCode(), msg.toString());
    }
}
