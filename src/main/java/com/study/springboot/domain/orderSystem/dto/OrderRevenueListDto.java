package com.study.springboot.domain.orderSystem.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class OrderRevenueListDto {
    private String type;
    private int year;
    private int month;
    private List<OrderRevenueResponseDto> OrderRevenueList;
}
