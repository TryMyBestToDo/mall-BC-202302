package com.daxiong.mall.controller;

import com.daxiong.mall.Service.OrderService;
import com.daxiong.mall.common.ApiRestResponse;
import com.daxiong.mall.common.Constant;
import com.daxiong.mall.model.pojo.User;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 描述：     订单后台管理Controller
 */
@RestController
public class OrderAdminController {

    @Resource
    OrderService orderService;

    @GetMapping("admin/order/list")
    //@ApiOperation("管理员订单列表")
    public ApiRestResponse listForAdmin(@RequestParam Integer pageNum,
                                        @RequestParam Integer pageSize) {
        PageInfo pageInfo = orderService.listForAdmin(pageNum, pageSize);
        return ApiRestResponse.success(pageInfo);
    }

    /**
     * 发货。订单状态流程：0用户已取消，10未付款，20已付款，30已发货，40交易完成
     */
    @PostMapping("admin/order/delivered")
    //@ApiOperation("管理员发货")
    public ApiRestResponse delivered(@RequestParam String orderNo) {
        orderService.deliver(orderNo);
        return ApiRestResponse.success();
    }

    /**
     * 完结订单。订单状态流程：0用户已取消，10未付款，20已付款，30已发货，40交易完成。管理员和用户都可以调用
     */
    @PostMapping("order/finish")
    //@ApiOperation("完结订单")
    public ApiRestResponse finish(@RequestParam String orderNo, HttpSession session) {
        orderService.finish(orderNo, (User) session.getAttribute(Constant.CURRENT_MALL_USER));
        return ApiRestResponse.success();
    }
}
