package com.study.springboot.domain.orderSystem.controller;

import com.study.springboot.datas.Message;
import com.study.springboot.datas.MessageService;
import com.study.springboot.domain.orderSystem.OrderItem;
import com.study.springboot.domain.orderSystem.OrderList;
import com.study.springboot.domain.orderSystem.dto.*;
import com.study.springboot.domain.orderSystem.service.OrderListService;
import com.study.springboot.domain.user.dto.OrderDto;
import com.study.springboot.domain.user.dto.UserOrderReqDto;
import com.study.springboot.enumeration.OrderListStatus;
import com.study.springboot.enumeration.error.StatusCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class OrderListController {
    final private OrderListService orderListService;
    final private MessageService messageService;


    // 식사 장소 선택 후 해당 회원의 order_list(장바구니) 생성
//    @PostMapping("/order/place")
//
//    public ResponseEntity userOrderList(@RequestBody Map<String, Long> userIdMap) {
//        Long userId = userIdMap.get("userId");
//
//        Optional<OrderList> optional = orderListService.userOrderList(userId);
//        if(optional.isPresent()){
//            Message message = messageService.addOrderListSuccessMessage(new OrderListDto(optional.get()));
//            return ResponseEntity.ok(message);
//        }
//
//        Message message = messageService.addOrderListFailedMessage();
//        return ResponseEntity.ok(message);
//    }

    // 결제 화면
//    @PostMapping("/order/payment")
//    public ResponseEntity payment(@RequestBody ) {
//        Long orderListId = orderListIdMap.get("orderListId");
//        Optional<PaymentResDto> optional = orderListService.payment(orderListId);
//        if(optional.isPresent()){
//            Message message = messageService.paymentSuccessMessage(optional.get());
//            return ResponseEntity.ok(message);
//        }
//
//        Message message = messageService.paymentFailedMessage();
//        return ResponseEntity.ok(message);
//    }

    @PostMapping("/order/payment2")
    public ResponseEntity payment(@RequestBody UserOrderReqDto dto) {
        String userId = dto.getUserId();
        List<OrderDto> orderDto = dto.getOrderList();

        Optional<OrderList> optional = orderListService.makeOrderList(orderDto, userId);

        if(optional.isPresent()){
            Message message = messageService.paymentSuccessMessage2();
            return ResponseEntity.ok(message);
        }

        Message message = messageService.paymentFailedMessage();
        return ResponseEntity.ok(message);
    }


    // 주문 완료(주문번호와 고객 적립금 출력) 및 주문 시간과 상태 업데이트
    @PostMapping("/order/submit")
    public ResponseEntity<Message> successOrder(@RequestBody SuccessOrderReqDto successOrderReqDto) {
        Long userId = successOrderReqDto.getUserId();
        Long orderListId = successOrderReqDto.getOrderListId();

        Optional<SuccessOrderResDto> optional = orderListService.orderSuccess(userId, orderListId);
        if(optional.isPresent()){
            Message message = messageService.orderSuccessMessage(optional.get());
            return  ResponseEntity.ok(message);
        }
        Message message = messageService.orderFailedMessage();
        return ResponseEntity.ok(message);

    }

    // 주문 확인 상세 페이지 (주문 상품의 이름, 가격, 개수 및 총 수량, 총 가격 출력)
    @PostMapping("/order/detail")
    public ResponseEntity orderDetail(@RequestBody Map<String, Long> orderListIdMap) {
        Long orderListId = orderListIdMap.get("orderListId");
        Optional<OrderDetailResDto> optional = orderListService.orderDetail(orderListId);
        if(optional.isPresent()){
            Message message = messageService.orderDetailSuccessMessage(optional.get());
            return ResponseEntity.ok(message);
        }

        Message message = messageService.orderListNotFoundMessage();

        return ResponseEntity.ok(message);
    }
}
