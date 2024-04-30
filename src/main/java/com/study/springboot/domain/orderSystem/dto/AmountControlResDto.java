package com.study.springboot.domain.orderSystem.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AmountControlResDto {
    private Integer orderPrice;
    private Integer orderAmount;
}
