package com.study.springboot.domain.orderSystem.dto;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Page;

@Getter
@Builder
public class OrderListResponseDto {
    private int totalCount;
    private Page<OrderListDto> orderList;
}
