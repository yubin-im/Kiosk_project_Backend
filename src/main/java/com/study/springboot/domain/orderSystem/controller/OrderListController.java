package com.study.springboot.domain.orderSystem.controller;

import com.study.springboot.datas.Message;
import com.study.springboot.domain.orderSystem.OrderList;
import com.study.springboot.domain.orderSystem.dto.OrderDetailResDto;
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
    public Message userOrderList(@RequestBody Map<String, String> userIdMap) {
        String userId = userIdMap.get("userId");
        Message message = orderListService.userOrderList(userId);

        return message;
    }

    // 결제 화면
    @PostMapping("/order/payment")
    public Message payment(@RequestBody Map<String, Long> orderListIdMap) {
        Long orderListId = orderListIdMap.get("orderListId");
        Message message = orderListService.payment(orderListId);

        return message;
    }

    // 주문 완료(주문번호와 고객 적립금 출력) 및 주문 시간과 상태 업데이트
    @PostMapping("/order/submit")
    public Message successOrder(@RequestBody SuccessOrderReqDto successOrderReqDto) {
        String userId = successOrderReqDto.getUserId();
        Long orderListId = successOrderReqDto.getOrderListId();

        Message message = orderListService.successOrder(userId, orderListId);

        return message;
    }

    // 주문 확인 상세 페이지 (주문 상품의 이름, 가격, 개수 및 총 수량, 총 가격 출력)
    @PostMapping("/order/detail")
    public Message orderDetail(@RequestBody Map<String, Long> orderListIdMap) {
        Long orderListId = orderListIdMap.get("orderListId");
        Message message = orderListService.orderDetail(orderListId);

        return message;
    }
}
