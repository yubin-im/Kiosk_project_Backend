package com.study.springboot.domain.orderSystem;


import com.study.springboot.domain.member.Member;
import com.study.springboot.enumeration.OrderStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "order_list")
public class OrderList {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private LocalDateTime orderListTime;

    @Column
    private Integer orderListTotalPrice;

    @Column
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "orderList", cascade = CascadeType.ALL)
    private List<OrderItem>  orderItems = new ArrayList<>();

}
