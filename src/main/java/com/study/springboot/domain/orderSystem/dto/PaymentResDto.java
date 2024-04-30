package com.study.springboot.domain.orderSystem.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PaymentResDto {
    private Integer orderItemTotalAmount;
    private Integer orderListTotalPrice;
}
