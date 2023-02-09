package com.daxiong.mall.model.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddProductReq {

    @NotNull(message = "商品名称不能为null")
    private String name;

    @NotNull(message = "商品图片不能为null")
    private String image;

    private String detail;

    @NotNull(message = "商品分类不能为null")
    private Integer categoryId;

    @NotNull(message = "商品价格不能为null")
    @Min(value = 1, message = "价格不能小于1分")
    private Integer price;

    @NotNull(message = "商品库存不能为null")
    @Max(value = 100000, message = "库存不能大于100000")
    private Integer stock;

    private Integer status;

}