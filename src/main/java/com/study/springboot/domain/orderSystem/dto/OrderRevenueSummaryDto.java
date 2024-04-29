package com.study.springboot.domain.orderSystem.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Getter
public class OrderRevenueSummaryDto {
    private LocalDate orderListDate;
    private Double totalPrice;

    @Override
    public String toString() {
        return "OrderRevenueSummaryDto{" +
                "orderListDate=" + orderListDate +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
