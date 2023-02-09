package com.daxiong.mall.model.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrderReq {

    @NotNull
    private String receiverName;

    @NotNull
    private String receiverMobile;

    @NotNull
    private String receiverAddress;

    private Integer postage = 0;

    private Integer paymentType = 1;

}