package com.study.springboot.domain.orderSystem.controller;

import com.study.springboot.domain.orderSystem.OrderList;
import com.study.springboot.domain.orderSystem.dto.PaymentResDto;
import com.study.springboot.domain.orderSystem.dto.SuccessOrderReqDto;
import com.study.springboot.domain.orderSystem.dto.SuccessOrderResDto;
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

    // 결제 화면
    @PostMapping("/order/payment")
    public PaymentResDto payment(@RequestBody Map<String, Long> orderListIdMap) {
        Long orderListId = orderListIdMap.get("orderListId");
        PaymentResDto paymentResDto = orderListService.payment(orderListId);

        return paymentResDto;
    }

    // 주문 완료(주문번호와 고객 적립금 출력) 및 주문 시간과 상태 업데이트
    @PostMapping("/order/submit")
    public SuccessOrderResDto successOrder(@RequestBody SuccessOrderReqDto successOrderReqDto) {
        Long userId = successOrderReqDto.getUserId();
        Long orderListId = successOrderReqDto.getOrderListId();

        SuccessOrderResDto successOrderResDto = orderListService.successOrder(userId, orderListId);

        return successOrderResDto;
    }
}
