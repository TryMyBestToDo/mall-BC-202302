package com.daxiong.mall.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductListReq {

    private String keyword;

    private Integer categoryId;

    private String orderBy;

    private Integer pageNum = 1;

    private Integer pageSize = 10;


}