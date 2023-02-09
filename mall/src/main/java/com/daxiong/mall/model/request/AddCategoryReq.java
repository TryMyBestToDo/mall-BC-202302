package com.daxiong.mall.model.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
public class AddCategoryReq {

    @Size(min = 2, message = "name最短两个字符")
    @NotNull(message = "name不能为null")
    private String name;

    @NotNull(message = "type不能为null")
    private Integer type;

    @NotNull(message = "parentId不能为null")
    private Integer parentId;

    @NotNull(message = "orderNum不能为null")
    private Integer orderNum;

}
