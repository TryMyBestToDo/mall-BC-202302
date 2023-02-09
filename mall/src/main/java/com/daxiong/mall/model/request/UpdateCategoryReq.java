package com.daxiong.mall.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * 描述：     AddCategoryReq
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCategoryReq {

    @NotNull(message = "id不能为null")
    private Integer id;

    @NotBlank(message = "商品name不能为空")
    private String name;

    private Integer type;

    private Integer parentId;

    private Integer orderNum;

}
