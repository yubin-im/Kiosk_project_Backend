package com.study.springboot.domain.orderSystem.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class OrderCountResponseDto {
    private String orderListDate;
    private String orderListCount;
}
