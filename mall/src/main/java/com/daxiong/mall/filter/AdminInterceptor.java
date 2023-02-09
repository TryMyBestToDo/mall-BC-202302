package com.daxiong.mall.filter;


import com.daxiong.mall.Service.UserService;
import com.daxiong.mall.common.Constant;
import com.daxiong.mall.exception.MallException;
import com.daxiong.mall.exception.MallExceptionEnum;
import com.daxiong.mall.model.pojo.User;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * 管理员拦截器
 */
public class AdminInterceptor implements HandlerInterceptor {
    @Resource
    UserService userService;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute(Constant.CURRENT_MALL_USER);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("json/html; charset=utf-8");
        // 防止纵向越权
        if (currentUser == null || !userService.checkAdminRole(currentUser)) {
            throw new MallException(MallExceptionEnum.NEED_ADMIN);
        }
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }
}
