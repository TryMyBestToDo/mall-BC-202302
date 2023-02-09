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
public class UpdateProductReq {

    @NotNull(message = "商品id不能为空")
    private Integer id;

    private String name;

    private String image;

    private String detail;

    private Integer categoryId;
    @Min(value = 1, message = "价格不能小于1分")
    private Integer price;
    @Max(value = 100000, message = "库存不能大于100000")
    private Integer stock;

    private Integer status;


}