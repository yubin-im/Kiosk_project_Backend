package com.study.springboot.domain.orderSystem.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class OrderDetailResDto {
    List<OrderDetailItemDto> orderDetailItemDtoList;
    private Integer orderListTotalAmount;
    private Integer orderListTotalPrice;
}
