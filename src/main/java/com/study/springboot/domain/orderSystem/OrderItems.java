package com.study.springboot.domain.orderSystem;


import com.study.springboot.domain.member.Member;
import com.study.springboot.domain.product.Product;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table
public class OrderItems {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_items_id")
    private Long id;


    @Column
    private Integer orderPrice;

    @Column
    private Integer productAmount;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;


    @ManyToOne
    @JoinColumn(name = "order_list_id")
    private OrderList orderList;


}
