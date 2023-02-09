package com.daxiong.mall.controller;

import com.daxiong.mall.Service.CartService;
import com.daxiong.mall.common.ApiRestResponse;
import com.daxiong.mall.model.vo.CartVO;
import com.daxiong.mall.util.CommonUtils;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 描述：     购物车Controller
 */
@RestController
@RequestMapping("/cart")
public class CartController {

    @Resource
    CartService cartService;

    @GetMapping("/list")
    //@ApiOperation("购物车列表")
    public ApiRestResponse list(HttpSession session) {
        //内部获取用户ID，防止横向越权
        List<CartVO> cartList = cartService.list(CommonUtils.getCurrentUserId(session));
        return ApiRestResponse.success(cartList);
    }

    @PostMapping("/add")
    //@ApiOperation("添加商品到购物车")
    public ApiRestResponse add(@RequestParam Integer productId, @RequestParam Integer count, HttpSession session) {
        List<CartVO> cartVOList = cartService.add(CommonUtils.getCurrentUserId(session), productId, count);
        return ApiRestResponse.success(cartVOList);
    }

    @PostMapping("/update")
    //@ApiOperation("更新购物车")
    public ApiRestResponse update(@RequestParam Integer productId, @RequestParam Integer count, HttpSession session) {
        List<CartVO> cartVOList = cartService
                .update(CommonUtils.getCurrentUserId(session), productId, count);
        return ApiRestResponse.success(cartVOList);
    }

    @PostMapping("/delete")
    //@ApiOperation("删除购物车")
    public ApiRestResponse delete(@RequestParam Integer productId, HttpSession session) {
        //不能传入userID，cartID，否则可以删除别人的购物车
        List<CartVO> cartVOList = cartService.delete(CommonUtils.getCurrentUserId(session), productId);
        return ApiRestResponse.success(cartVOList);
    }

    @PostMapping("/select")
    //@ApiOperation("选择/不选择购物车的某商品")
    public ApiRestResponse select(@RequestParam Integer productId, @RequestParam Integer selected, HttpSession session) {
        //不能传入userID，cartID，否则可以删除别人的购物车
        List<CartVO> cartVOList = cartService
                .selectOrNot(CommonUtils.getCurrentUserId(session), productId, selected);
        return ApiRestResponse.success(cartVOList);
    }

    @PostMapping("/selectAll")
    //@ApiOperation("全选择/全不选择购物车的某商品")
    public ApiRestResponse selectAll(@RequestParam Integer selected, HttpSession session) {
        //不能传入userID，cartID，否则可以删除别人的购物车
        List<CartVO> cartVOList = cartService
                .selectAllOrNot(CommonUtils.getCurrentUserId(session), selected);
        return ApiRestResponse.success(cartVOList);
    }
}
