package com.daxiong.mall.Service;

import com.daxiong.mall.model.pojo.User;
import com.daxiong.mall.model.request.CreateOrderReq;
import com.daxiong.mall.model.vo.OrderVO;
import com.github.pagehelper.PageInfo;

/**
 * 描述：     订单Service
 */
public interface OrderService {


    String create(CreateOrderReq createOrderReq, Integer userId);

    OrderVO detail(String orderNo, Integer userId);

    PageInfo listForCustomer(Integer pageNum, Integer pageSize, Integer userId);

    void cancel(String orderNo, Integer userId);

    String qrcode(String orderNo);

    void pay(String orderNo);

    PageInfo listForAdmin(Integer pageNum, Integer pageSize);

    void deliver(String orderNo);

    void finish(String orderNo, User user);
}
