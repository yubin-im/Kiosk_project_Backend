package com.study.springboot.domain.orderSystem;


import com.study.springboot.domain.user.User;
import com.study.springboot.enumeration.OrderListStatus;
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
    private LocalDateTime orderListTime = LocalDateTime.now();

    @Column
    private Integer orderListTotalPrice;

    @Column
    @Enumerated(EnumType.STRING)
    private OrderListStatus orderListStatus;

    @ManyToOne
    @JoinColumn(name = "user_id")
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
}
