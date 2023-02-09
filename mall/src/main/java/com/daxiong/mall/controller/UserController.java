package com.daxiong.mall.controller;

import com.daxiong.mall.Service.UserService;
import com.daxiong.mall.common.ApiRestResponse;
import com.daxiong.mall.common.Constant;
import com.daxiong.mall.exception.MallException;
import com.daxiong.mall.exception.MallExceptionEnum;
import com.daxiong.mall.model.pojo.User;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * 描述：     用户控制器
 */
@RestController
public class UserController {

    @Resource
    UserService userService;

    @GetMapping("/test")
    public User personalPage() {
        return userService.getUser();
    }

    /**
     * 注册
     */
    @PostMapping("/register")
    public ApiRestResponse register(@RequestParam("userName") String userName,
                                    @RequestParam("password") String password) throws MallException {
        if (!StringUtils.hasLength(userName)) {
            return ApiRestResponse.error(MallExceptionEnum.NEED_USER_NAME);
        }
        if (!StringUtils.hasLength(password)) {
            return ApiRestResponse.error(MallExceptionEnum.NEED_PASSWORD);
        }
        //密码长度不能少于8位
        if (password.length() < 6) {
            return ApiRestResponse.error(MallExceptionEnum.PASSWORD_TOO_SHORT);
        }
        userService.register(userName, password);
        return ApiRestResponse.success();
    }

    /**
     * 登录
     */
    @GetMapping("/login")
    public ApiRestResponse login(@RequestParam("userName") String userName,
            @RequestParam("password") String password, HttpSession session)
            throws MallException {
        if (!StringUtils.hasLength(userName)) {
            return ApiRestResponse.error(MallExceptionEnum.NEED_USER_NAME);
        }
        if (!StringUtils.hasLength(password)) {
            return ApiRestResponse.error(MallExceptionEnum.NEED_PASSWORD);
        }
        User user = userService.login(userName, password);
        //保存用户信息时，不保存密码
        user.setPassword(null);
        session.setAttribute(Constant.CURRENT_MALL_USER, user);
        return ApiRestResponse.success(user);
    }

    /**
     * 更新个性签名
     */
    @PostMapping("/user/update")
    public ApiRestResponse updateUserInfo(HttpSession session, @RequestParam String signature)
            throws MallException {
        User currentUser = (User) session.getAttribute(Constant.CURRENT_MALL_USER);
        if (currentUser == null) {
            return ApiRestResponse.error(MallExceptionEnum.NEED_LOGIN);
        }
        User user = new User();
        user.setId(currentUser.getId());
        user.setPersonalizedSignature(signature);
        userService.updateInformation(user);
        return ApiRestResponse.success();
    }

    /**
     * 登出，清除session
     */
    @PostMapping("/user/logout")
    public ApiRestResponse logout(HttpSession session) {
        session.removeAttribute(Constant.CURRENT_MALL_USER);
        return ApiRestResponse.success();
    }

    /**
     * 管理员登录接口
     */
    @GetMapping("/adminLogin")
    public ApiRestResponse adminLogin(@RequestParam("userName") String userName,
            @RequestParam("password") String password, HttpSession session)
            throws MallException {
        if (!StringUtils.hasLength(userName)) {
            return ApiRestResponse.error(MallExceptionEnum.NEED_USER_NAME);
        }
        if (!StringUtils.hasLength(password)) {
            return ApiRestResponse.error(MallExceptionEnum.NEED_PASSWORD);
        }
        User user = userService.login(userName, password);
        //校验是否是管理员
        if (userService.checkAdminRole(user)) {
            //是管理员，执行操作
            //保存用户信息时，不保存密码
            user.setPassword(null);
            session.setAttribute(Constant.CURRENT_MALL_USER, user);
            return ApiRestResponse.success(user);
        } else {
            return ApiRestResponse.error(MallExceptionEnum.NEED_ADMIN);
        }
    }
}
