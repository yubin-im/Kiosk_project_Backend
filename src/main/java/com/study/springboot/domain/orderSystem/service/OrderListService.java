package com.study.springboot.domain.orderSystem.service;

import com.study.springboot.datas.Message;
import com.study.springboot.domain.orderSystem.OrderItem;
import com.study.springboot.domain.orderSystem.OrderList;
import com.study.springboot.domain.orderSystem.dto.*;
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
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderListService {

    private final OrderListRepository orderListRepository;
    private final UserRepository userRepository;
    private final OrderItemRepository orderItemRepository;
    private final ProductRepository productRepository;


    // 식사 장소 선택 후 해당 회원의 order_list(장바구니) 생성
    @Transactional
    public Optional<OrderList> userOrderList(Long userId) {
        OrderList orderList = OrderList.builder()
                .orderListTime(LocalDateTime.now())
                .orderListStatus(OrderListStatus.PREPARING)
                .orderListTotalPrice(0)
                // 비회원이면 null로 들어가도록
                .user(userRepository.findByUserId(userId).orElse(null))
                .build();

        OrderList result = orderListRepository.save(orderList);

        return Optional.ofNullable(result);

    }

    // 결제 화면
    @Transactional
    public Optional<PaymentResDto> payment(Long orderListId) {
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

        return Optional.ofNullable(paymentResDto);

    }

    // 주문 완료(주문번호와 고객 적립금 출력) 및 주문 시간과 상태 업데이트
    @Transactional
    public Optional<SuccessOrderResDto> orderSuccess(Long userId, Long orderListId) {
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


        return Optional.ofNullable(successOrderResDto);

    }

    // 주문 확인 상세 페이지 (주문 상품의 이름, 가격, 개수 및 총 수량, 총 가격 출력)
    @Transactional
    public Optional<OrderDetailResDto> orderDetail(Long orderListId) {
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

        return Optional.ofNullable(orderDetailResDto);

    }

    @Transactional
    public Optional<OrderListDto> addProduct(Long orderListId, Long productId){
        Optional<OrderList> optional = orderListRepository.findById(orderListId);
        Optional<Product> productOptional = productRepository.findById(productId);
        if(optional.isEmpty() || productOptional.isEmpty()){
            return Optional.empty();
        }

        OrderList list = optional.get();
        Product product = productOptional.get();
        list = list.addProduct(product);

        return Optional.of(new OrderListDto(list));
    }


}
