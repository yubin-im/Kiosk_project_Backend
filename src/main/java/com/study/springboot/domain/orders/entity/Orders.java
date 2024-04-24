package com.study.springboot.domain.orders.entity;

import com.study.springboot.domain.member.entity.Member;
import com.study.springboot.domain.orderItem.entity.OrderItem;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "orders_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate ordersDate;

    @Column(name = "orders_state")
    @Enumerated(EnumType.STRING)
    private OrdersStateEnum ordersState;

    @Column(name = "orders_total_price")
    private int orderTotalPrice;

    // orders: member = n : 1
    @ManyToOne
    private Member member;

    // orders: order_item = 1 : n
    @OneToMany(mappedBy = "orders")
    private List<OrderItem> orderItemList = new ArrayList<>();
}
