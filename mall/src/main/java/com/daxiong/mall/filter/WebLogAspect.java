package com.daxiong.mall.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Arrays;


/**
 * 打印请求和响应信息
 */
@Aspect
@Component
public class WebLogAspect {
    private final Logger log = LoggerFactory.getLogger(WebLogAspect.class);

    @Around("execution(public * com.daxiong.mall.controller.*.*(..))")
    public Object webLog(ProceedingJoinPoint pjp) throws Throwable {
        log.info("========================新的请求========================");
        // 收到请求，记录请求内容
        ServletRequestAttributes attributes =
                (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        log.info("URL : " + request.getRequestURL().toString());
        log.info("HTTP_METHOD : " + request.getMethod());
        // 若是 localhost，则会返回 0:0:0:0:0:0:0:1
        log.info("IP : " + request.getRemoteAddr());

        log.info("CLASS_METHOD : " + pjp.getSignature().getDeclaringTypeName() + "." + pjp.getSignature().getName());
        log.info("ARGS : " + Arrays.toString(pjp.getArgs()));

        // 执行方法，处理请求
        Object res = pjp.proceed(pjp.getArgs());

        // 记录响应
        log.info("RESPONSE : " + new ObjectMapper().writeValueAsString(res));

        return res;
    }
}
