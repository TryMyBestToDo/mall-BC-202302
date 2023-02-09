package com.daxiong.mall.filter;

import com.daxiong.mall.common.Constant;
import com.daxiong.mall.exception.MallException;
import com.daxiong.mall.exception.MallExceptionEnum;
import com.daxiong.mall.model.pojo.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * 用户拦截器
 */
public class UserInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute(Constant.CURRENT_MALL_USER);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("json/html; charset=utf-8");
        // 未登录用户
        if (currentUser == null) {
            throw new MallException(MallExceptionEnum.NEED_LOGIN);
        }
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }
}
