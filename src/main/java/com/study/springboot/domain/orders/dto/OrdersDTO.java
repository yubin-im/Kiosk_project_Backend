package com.study.springboot.domain.orders.dto;

import com.study.springboot.domain.member.entity.Member;
import com.study.springboot.domain.orderItem.entity.OrderItem;
import com.study.springboot.domain.orders.entity.OrdersStateEnum;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrdersDTO {
    private Long id;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate ordersDate;
    private OrdersStateEnum ordersState;
    private int orderTotalPrice;
    private Member member;
    private List<OrderItem> orderItemList;
}
