package com.study.springboot.domain.orderSystem.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class OrderCountListDto {
    private String type;
    private int year;
    private int month;
    private List<OrderCountResponseDto> OrderCountList;
}
