package com.study.springboot.domain.orderSystem.dto;

import com.study.springboot.domain.orderSystem.OrderItem;
import com.study.springboot.domain.orderSystem.OrderList;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class OrderListDto {
    private Long orderId;
    private LocalDateTime orderListTime;
    private Integer orderListTotalPrice;
    private String orderListStatus;
    private String userId;

    @Builder
    public OrderListDto(OrderList entity) {
        this.orderId = entity.getId();
        this.orderListTime = entity.getOrderListTime();
        this.orderListTotalPrice = entity.getOrderListTotalPrice();
        this.orderListStatus = entity.getOrderListStatus().toString();
        this.userId = entity.getUser().getUserId();
    }
}
