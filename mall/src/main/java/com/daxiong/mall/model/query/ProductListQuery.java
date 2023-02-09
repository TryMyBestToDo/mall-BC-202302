package com.daxiong.mall.model.query;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 描述：     查询商品列表的Query
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductListQuery {

    private String keyword;

    private List<Integer> categoryIds;
}
