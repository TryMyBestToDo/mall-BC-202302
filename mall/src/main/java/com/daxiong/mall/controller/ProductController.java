package com.daxiong.mall.controller;

import com.daxiong.mall.Service.ProductService;
import com.daxiong.mall.common.ApiRestResponse;
import com.daxiong.mall.model.pojo.Product;
import com.daxiong.mall.model.request.ProductListReq;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 描述：     前台商品Controller
 */
@RestController
public class ProductController {

    @Resource
    ProductService productService;

    //@ApiOperation("商品详情")
    @GetMapping("product/detail")
    public ApiRestResponse detail(@RequestParam Integer id) {
        Product product = productService.detail(id);
        return ApiRestResponse.success(product);
    }

    //@ApiOperation("商品详情")
    @GetMapping("product/list")
    public ApiRestResponse list(ProductListReq productListReq) {
        PageInfo list = productService.list(productListReq);
        return ApiRestResponse.success(list);
    }
}
