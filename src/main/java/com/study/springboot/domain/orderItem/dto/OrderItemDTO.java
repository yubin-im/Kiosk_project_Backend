package com.study.springboot.domain.orderItem.dto;

import com.study.springboot.domain.orders.entity.Orders;
import com.study.springboot.domain.product.entity.Product;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderItemDTO {
    private Long id;
    private int orderAmount;
    private int orderPrice;
    private Product product;
    private Orders orders;
}
