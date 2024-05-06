package com.study.springboot.domain.orderSystem;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.study.springboot.domain.product.Product;
import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table
@Builder
@AllArgsConstructor
public class OrderItem {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Integer orderPrice;

    @Column
    private Integer orderAmount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    @JsonIgnore
    private Product product;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_list_id")
    @JsonIgnore
    private OrderList orderList;

    // 수량 수정 위한 메소드
    public void updateOrderAmount(Integer orderAmount) {
        this.orderAmount = orderAmount;
    }

    public void updateAmountAndPrice(Integer orderAmount, Integer orderPrice){
        this.orderAmount = orderAmount;
        this.orderPrice = orderPrice;
    }
  
    // 금액 수정 위한 메소드
    public void updateOrderPrice(Integer orderPrice) {
        this.orderPrice = orderPrice;
    }


    @Builder
    public OrderItem(Integer orderPrice, Integer orderAmount, Product product, OrderList orderList) {
        this.orderPrice = orderPrice;
        this.orderAmount = orderAmount;
        this.product = product;
        this.orderList = orderList;
    }
}