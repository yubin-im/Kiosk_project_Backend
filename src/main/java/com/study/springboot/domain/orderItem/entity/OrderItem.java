package com.study.springboot.domain.orderItem.entity;

import com.study.springboot.domain.orders.entity.Orders;
import com.study.springboot.domain.product.entity.Product;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "order_item")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "order_amount")
    private int orderAmount;

    @Column(name = "order_price")
    private int orderPrice;

    // order_item: product = n : 1
    @ManyToOne
    private Product product;

    // order_item: orders = n : 1
    @ManyToOne
    private Orders orders;
}
