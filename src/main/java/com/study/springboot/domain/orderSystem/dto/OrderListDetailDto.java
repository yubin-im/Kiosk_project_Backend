package com.study.springboot.domain.orderSystem.dto;


import com.study.springboot.domain.orderSystem.OrderItem;
import com.study.springboot.domain.orderSystem.OrderList;
import com.study.springboot.domain.product.Product;
import com.study.springboot.domain.product.dto.ProductDto;
import com.study.springboot.enumeration.OrderListStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class OrderListDetailDto {
    private Long orderId;
    private LocalDateTime orderListTime;
    private Integer orderListTotalPrice;
    private OrderListStatus orderListStatus;
    private String userId;

    private List<OrderItem> orderItemList;


    @Builder
    public OrderListDetailDto(final OrderList orderList, final List<OrderItem> items) {
        this.orderId = orderList.getId();
        this.orderListTime = orderList.getOrderListTime();
        this.orderListTotalPrice = orderList.getOrderListTotalPrice();
        this.orderListStatus = orderList.getOrderListStatus();
        this.userId = orderList.getUser().getUserId();
        this.orderItemList = items;
    }
}
