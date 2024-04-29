package com.study.springboot.domain.orderSystem.controller;

import com.study.springboot.domain.orderSystem.OrderList;
import com.study.springboot.domain.orderSystem.service.OrderListService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class OrderListController {
    final private OrderListService orderListService;

    // 식사 장소 선택 후 해당 회원의 order_list(장바구니) 생성
    @PostMapping("/order/place")
    public OrderList userOrderList(@RequestBody Map<String, Long> userIdMap) {
        Long userId = userIdMap.get("userId");
        OrderList orderList = orderListService.userOrderList(userId);

        return orderList;
    }
}
