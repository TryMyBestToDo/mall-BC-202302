package com.daxiong.mall.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 描述：     CartVO，给前端展示用
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartVO {

    private Integer id;

    private Integer productId;

    private Integer userId;

    private Integer quantity;

    private Integer selected;

    private Integer price;

    private Integer totalPrice;

    private String productName;

    private String productImage;

}
