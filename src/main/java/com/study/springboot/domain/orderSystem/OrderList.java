package com.study.springboot.domain.orderSystem;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.study.springboot.domain.product.Product;
import com.study.springboot.domain.user.User;
import com.study.springboot.enumeration.OrderListStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "order_list")
@Builder
@AllArgsConstructor
public class OrderList {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private LocalDateTime orderListTime = LocalDateTime.now();

    @Column
    private Integer orderListTotalPrice;

    @Column
    @Enumerated(EnumType.STRING)
    private OrderListStatus orderListStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    @OneToMany(mappedBy = "orderList", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

    public void update(LocalDateTime orderListTime, Integer orderListTotalPrice, OrderListStatus orderListStatus){
        this.orderListTime=orderListTime;
        this.orderListTotalPrice=orderListTotalPrice;
        this.orderListStatus=orderListStatus;
    }

    @Override
    public String toString() {
        return "OrderList{" +
                "id=" + id +
                ", orderListTime=" + orderListTime +
                ", orderListTotalPrice=" + orderListTotalPrice +
                ", orderListStatus=" + orderListStatus +
                ", user=" + user +
                ", orderItems=" + orderItems +
                '}';
    }

    // 주문 완료 시 시간과 상태 업데이트
    public void updateTimeAndStatus(LocalDateTime orderListTime, OrderListStatus orderListStatus) {
        this.orderListTime = orderListTime;
        this.orderListStatus = orderListStatus;
    }

    private OrderList(OrderList old){
        this.orderItems = old.orderItems;
        this.orderListStatus = old.orderListStatus;
        this.orderListTotalPrice = old.getOrderListTotalPrice();
        this.orderListTime = old.orderListTime;
        this.id = old.id;
        this.user = old.user;
    }

    public OrderList addProduct(Product product){
        OrderItem orderItem = OrderItem.builder()
                .orderList(this)
                .product(product)
                .orderPrice(product.getProductPrice())
                .orderAmount(1)
                .build();

        this.orderItems.add(orderItem);
        return new OrderList(this);
    }
}