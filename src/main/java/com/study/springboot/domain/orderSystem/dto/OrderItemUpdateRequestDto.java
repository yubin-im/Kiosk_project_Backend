package com.study.springboot.domain.orderSystem.dto;

import lombok.Getter;

@Getter
public class OrderItemUpdateRequestDto {
    private Long id;
    private Integer orderItemAmount;
    private Integer orderItemPrice;
}
