package com.study.springboot.domain.orderSystem.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Builder
@Getter
public class OrderRevenueResponseDto {
    private String orderListDate;
    private String orderListTotalPrice;
}
