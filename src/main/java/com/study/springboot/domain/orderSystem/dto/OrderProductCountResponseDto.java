package com.study.springboot.domain.orderSystem.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class OrderProductCountResponseDto {
    private String orderProductName;
    private String orderProductCount;
}
