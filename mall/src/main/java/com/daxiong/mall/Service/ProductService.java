package com.daxiong.mall.Service;

import com.daxiong.mall.model.pojo.Product;
import com.daxiong.mall.model.request.AddProductReq;
import com.daxiong.mall.model.request.ProductListReq;
import com.github.pagehelper.PageInfo;

/**
 * 描述：     商品Service
 */
public interface ProductService {

    void add(AddProductReq addProductReq);

    void update(Product updateProduct);

    void delete(Integer id);

    void batchUpdateSellStatus(Integer[] ids, Integer sellStatus);

    PageInfo listForAdmin(Integer pageNum, Integer pageSize);

    Product detail(Integer id);

    PageInfo list(ProductListReq productListReq);
}
