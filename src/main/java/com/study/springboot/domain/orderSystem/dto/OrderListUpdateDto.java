package com.study.springboot.domain.orderSystem.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class OrderListUpdateDto {
    private Long id;
    private LocalDateTime orderListTime;
    private Integer orderListTotalPrice;
    private String orderListStatus;
}
