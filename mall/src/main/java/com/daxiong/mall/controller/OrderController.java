package com.daxiong.mall.controller;

import com.daxiong.mall.Service.OrderService;
import com.daxiong.mall.common.ApiRestResponse;
import com.daxiong.mall.model.request.CreateOrderReq;
import com.daxiong.mall.model.vo.OrderVO;
import com.daxiong.mall.util.CommonUtils;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;


/**
 * 描述：     订单Controller
 */
@RestController
public class OrderController {

    @Resource
    OrderService orderService;

    @PostMapping("order/create")
    //@ApiOperation("创建订单")
    public ApiRestResponse create(@RequestBody @Valid CreateOrderReq createOrderReq, HttpSession session) {
        String orderNo = orderService.create(createOrderReq, CommonUtils.getCurrentUserId(session));
        return ApiRestResponse.success(orderNo);
    }

    @GetMapping("order/detail")
    //@ApiOperation("前台订单详情")
    public ApiRestResponse detail(@RequestParam String orderNo, HttpSession session) {
        OrderVO orderVO = orderService.detail(orderNo, CommonUtils.getCurrentUserId(session));
        return ApiRestResponse.success(orderVO);
    }

    @GetMapping("order/list")
    //@ApiOperation("前台订单列表")
    public ApiRestResponse list(@RequestParam Integer pageNum, @RequestParam Integer pageSize, HttpSession session) {
        PageInfo pageInfo = orderService.listForCustomer(pageNum, pageSize, CommonUtils.getCurrentUserId(session));
        return ApiRestResponse.success(pageInfo);
    }

    /**
     * 订单取消
     */
    @PostMapping("order/cancel")
    //@ApiOperation("前台取消订单")
    public ApiRestResponse cancel(@RequestParam String orderNo, HttpSession session) {
        orderService.cancel(orderNo, CommonUtils.getCurrentUserId(session));
        return ApiRestResponse.success();
    }

    /**
     * 生成支付二维码
     */
    @GetMapping("order/qrcode")
    //@ApiOperation("生成支付二维码")
    public ApiRestResponse qrcode(@RequestParam String orderNo) {
        String pngAddress = orderService.qrcode(orderNo);
        return ApiRestResponse.success(pngAddress);
    }

    @GetMapping("pay")
    //@ApiOperation("支付接口")
    public ApiRestResponse pay(@RequestParam String orderNo) {
        orderService.pay(orderNo);
        return ApiRestResponse.success();
    }
}
