package com.study.springboot.domain.orders.entity;

import lombok.Getter;

@Getter
public enum OrdersStateEnum {
    COMPLETED("결제완료"),
    PREPARING("상품준비"),
    READY("수령대기"),
    RECEIVED("수령완료");

    private String ordersState;

    OrdersStateEnum(String ordersState) {
        this.ordersState = ordersState;
    }
}
