package com.study.springboot.domain.orderSystem.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class OrderDetailItemDto {
    private String productName;
    private Integer orderPrice;
    private Integer orderAmount;
}
