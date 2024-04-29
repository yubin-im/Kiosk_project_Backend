package com.study.springboot.domain.orderSystem.dto;

import com.study.springboot.domain.orderSystem.OrderItem;
import com.study.springboot.domain.orderSystem.OrderList;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class OrderListDto {
    private Long id;
    private LocalDateTime orderListTime;
    private Integer orderListTotatlPrice;
    private String orderListStatus;
    private Long userId;
    private List<OrderItem> orderItems;

    @Builder
    public OrderListDto(OrderList entity) {
        this.id = entity.getId();
        this.orderListTime = entity.getOrderListTime();
        this.orderListTotatlPrice = entity.getOrderListTotalPrice();
        this.orderListStatus = entity.getOrderListStatus().toString();
        this.userId = entity.getUser().getId();
        this.orderItems=entity.getOrderItems();
    }
}
