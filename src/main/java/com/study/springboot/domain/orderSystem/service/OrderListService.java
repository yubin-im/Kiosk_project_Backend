package com.study.springboot.domain.orderSystem.service;

import com.study.springboot.datas.Message;
import com.study.springboot.domain.orderSystem.OrderItem;
import com.study.springboot.domain.orderSystem.OrderList;
import com.study.springboot.domain.orderSystem.dto.OrderDetailItemDto;
import com.study.springboot.domain.orderSystem.dto.OrderDetailResDto;
import com.study.springboot.domain.orderSystem.dto.PaymentResDto;
import com.study.springboot.domain.orderSystem.dto.SuccessOrderResDto;
import com.study.springboot.domain.orderSystem.repository.OrderItemRepository;
import com.study.springboot.domain.orderSystem.repository.OrderListRepository;
import com.study.springboot.domain.product.Product;
import com.study.springboot.domain.product.repository.ProductRepository;
import com.study.springboot.domain.user.User;
import com.study.springboot.domain.user.repository.UserRepository;
import com.study.springboot.enumeration.OrderListStatus;
import com.study.springboot.enumeration.error.StatusCode;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderListService {

    private final OrderListRepository orderListRepository;
    private final UserRepository userRepository;
    private final OrderItemRepository orderItemRepository;


    // 식사 장소 선택 후 해당 회원의 order_list(장바구니) 생성
    @Transactional
    public Message userOrderList(Long userId) {
        OrderList orderList = OrderList.builder()
                .orderListTime(LocalDateTime.now())
                .orderListStatus(OrderListStatus.PREPARING)
                .orderListTotalPrice(0)
                // 비회원이면 null로 들어가도록
                .user(userRepository.findById(userId).orElse(null))
                .build();

        orderListRepository.save(orderList);

        // Message에 추가
        Message message = Message.builder()
                .status(StatusCode.ORDER_LIST_CREATE_SUCCESS)
                .code(StatusCode.ORDER_LIST_CREATE_SUCCESS.getValue())
                .message("장바구니가 생성되었습니다!")
                .result(orderList)
                .build();

        return message;
    }

    // 결제 화면
    @Transactional
    public Message payment(Long orderListId) {
        OrderList orderList = orderListRepository.findById(orderListId).orElse(null);
        List<OrderItem> orderItemList = orderItemRepository.findOrderItemsByOrderList(orderList);

        // 총 수량 계산
        Integer orderItemTotalAmount = 0;
        for(int i = 0; i < orderItemList.size(); i++) {
            orderItemTotalAmount += orderItemList.get(i).getOrderAmount();
        }

        // 총 금액
        Integer orderListTotalPrice = orderList.getOrderListTotalPrice();

        PaymentResDto paymentResDto = PaymentResDto.builder()
                .orderItemTotalAmount(orderItemTotalAmount)
                .orderListTotalPrice(orderListTotalPrice)
                .build();

        // Message에 추가
        Message message = Message.builder()
                .status(StatusCode.ORDER_LIST_PAYMENT_SUCCESS)
                .code(StatusCode.ORDER_LIST_PAYMENT_SUCCESS.getValue())
                .message("결제가 정상적으로 진행되었습니다!")
                .result(paymentResDto)
                .build();

        return message;
    }

    // 주문 완료(주문번호와 고객 적립금 출력) 및 주문 시간과 상태 업데이트
    @Transactional
    public Message successOrder(Long userId, Long orderListId) {
        User user = userRepository.findById(userId).orElse(null);
        OrderList orderList = orderListRepository.findById(orderListId).orElse(null);

        // 주문 시간과 상태 업데이트
        orderList.updateTimeAndStatus(LocalDateTime.now(), OrderListStatus.COMPLETED);
        orderListRepository.save(orderList);

        // 구매금액의 1% 적립
        Integer reserves = (int) (orderList.getOrderListTotalPrice() * 0.01);
        user.updateUserPoint(user.getUserPoint() + reserves);
        userRepository.save(user);

        // 주문번호와 고객 적립금 출력
        SuccessOrderResDto successOrderResDto = SuccessOrderResDto.builder()
                .userPoint(user.getUserPoint())
                .orderListId(orderList.getId())
                .build();

        // Message에 추가
        Message message = Message.builder()
                .status(StatusCode.ORDER_LIST_SUCCESS)
                .code(StatusCode.ORDER_LIST_SUCCESS.getValue())
                .message("주문이 정상적으로 완료되었습니다!")
                .result(successOrderResDto)
                .build();

        return message;
    }

    // 주문 확인 상세 페이지 (주문 상품의 이름, 가격, 개수 및 총 수량, 총 가격 출력)
    @Transactional
    public Message orderDetail(Long orderListId) {
        OrderList orderList = orderListRepository.findById(orderListId).orElse(null);
        List<OrderItem> orderItemList = orderItemRepository.findOrderItemsByOrderList(orderList);

        // 주문 상품 이름, 가격, 개수를 리스트에 담기
        List<OrderDetailItemDto> orderDetailItemDtoList = new ArrayList<>();

        for(int i = 0; i < orderItemList.size(); i++) {
            OrderItem orderItem = orderItemList.get(i);
            Product product = orderItem.getProduct();

            OrderDetailItemDto orderDetailItemDto = OrderDetailItemDto.builder()
                    .productName(product.getProductName())
                    .orderPrice(orderItem.getOrderPrice())
                    .orderAmount(orderItem.getOrderAmount())
                    .build();

            orderDetailItemDtoList.add(orderDetailItemDto);
        }

        // 총 수량 계산
        Integer orderListTotalAmount = 0;
        for(int i = 0; i < orderItemList.size(); i++) {
            orderListTotalAmount += orderItemList.get(i).getOrderAmount();
        }

        // 총 금액
        Integer orderListTotalPrice = orderList.getOrderListTotalPrice();

        OrderDetailResDto orderDetailResDto = OrderDetailResDto.builder()
                .orderDetailItemDtoList(orderDetailItemDtoList)
                .orderListTotalAmount(orderListTotalAmount)
                .orderListTotalPrice(orderListTotalPrice)
                .build();

        // Message에 추가
        Message message = Message.builder()
                .status(StatusCode.ORDER_LIST_CHECK_SUCCESS)
                .code(StatusCode.ORDER_LIST_CHECK_SUCCESS.getValue())
                .message("주문 상세 조회를 성공했습니다!")
                .result(orderDetailResDto)
                .build();

        return message;
    }


}
