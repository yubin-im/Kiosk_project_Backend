package com.study.springboot.domain.user.dto;


import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserOrderReqDto {
    private List<OrderDto> orderList;
    private String userId;
}
