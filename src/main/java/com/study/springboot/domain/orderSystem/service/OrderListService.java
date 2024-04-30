package com.study.springboot.domain.orderSystem.service;

import com.study.springboot.domain.orderSystem.dto.*;
import com.study.springboot.domain.orderSystem.OrderItem;
import com.study.springboot.domain.orderSystem.OrderList;
import com.study.springboot.domain.orderSystem.dto.OrderListDto;
import com.study.springboot.domain.orderSystem.dto.OrderListUpdateDto;
import com.study.springboot.domain.orderSystem.dto.PaymentResDto;
import com.study.springboot.domain.orderSystem.dto.SuccessOrderResDto;
import com.study.springboot.domain.orderSystem.repository.OrderItemRepository;
import com.study.springboot.domain.orderSystem.repository.OrderListRepository;
import com.study.springboot.domain.product.Product;
import com.study.springboot.domain.product.repository.ProductRepository;
import com.study.springboot.domain.user.User;
import com.study.springboot.domain.user.repository.UserRepository;
import com.study.springboot.enumeration.OrderListStatus;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderListService {

    private final OrderListRepository orderListRepository;
    private final UserRepository userRepository;
    private final OrderItemRepository orderItemRepository;

    /*
    주문 목록 조회
     */
    @Transactional
    public List<OrderListDto> getOrderList(String type, String text, int page){
        PageRequest pageRequest = PageRequest.of(page, 5, Sort.by("orderListTime").descending());

        Page<OrderListDto> orderListDtoPage=null;
        if(type==null){
            orderListDtoPage = orderListRepository.findAll(pageRequest).map(OrderListDto::new);
        } else if(type.equals("status")){
            OrderListStatus status = OrderListStatus.valueOf(text.toUpperCase());
            orderListDtoPage = orderListRepository.findByOrderListStatus(status, pageRequest).map(OrderListDto::new);
        }

        return orderListDtoPage.getContent();
    }

    /*
    주문 상세 조회
     */
    public OrderList getOrder(Long id){
        OrderList orderList = orderListRepository.findById(id).orElseThrow(()->new IllegalArgumentException("주문이 존재하지 않습니다."));
        System.out.println(orderList.toString());
        return orderList;
    }

    /*
    주문 삭제
     */
    @Transactional
    public boolean deleteOrderList(Long id){
        OrderList orderList = orderListRepository.findById(id).orElseThrow(()->new IllegalArgumentException("주문이 존재하지 않습니다."));

        if(orderList==null){
            return false;
        } else{
            orderListRepository.delete(orderList);
            return true;
        }
    }

    /*
    주문 수정
     */
    @Transactional
    public boolean updateOrderList(Long id, OrderListUpdateDto dto){
        OrderList orderList = orderListRepository.findById(id).get();
        System.out.println(dto.getOrderListStatus());
        if(orderList==null || id!=dto.getId()){
            return false;
        } else {
            orderList.update(dto.getOrderListTime(),dto.getOrderListTotalPrice(), OrderListStatus.valueOf(dto.getOrderListStatus()));
            return true;
        }
    }


    /*
    주문 수입 통계
     */
    public OrderRevenueListDto getOrderRevenue(String type, int year, int month){

        List<OrderRevenueResponseDto> result = new ArrayList<>();
        List<Object[]> summary = null;

        if(type.equals("month")){
            // 일별 통계
            summary = orderListRepository.findOrderMonth(year, month);
        } else if(type.equals("year")){
            // 월별 통계
            summary = orderListRepository.findOrderYear(year);
        }

        for(Object[] dto : summary){
            OrderRevenueResponseDto resDto = OrderRevenueResponseDto.builder()
                    .orderListDate(dto[0].toString())
                    .orderListTotalPrice(dto[1].toString())
                    .build();
            result.add(resDto);
        }

        return OrderRevenueListDto.builder()
                .type(type)
                .year(year)
                .month(month)
                .OrderRevenueList(result)
                .build();
    }

    /*
    주문 통계 - 날짜별 주문 수 조회
     */
    public OrderCountListDto getOrderCount(String type, int year, int month){

        List<OrderCountResponseDto> result = new ArrayList<>();
        List<Object[]> summary = null;

        if(type.equals("month")){
            // 일별 통계
            summary = orderListRepository.findOrderMonth(year, month);
        } else if(type.equals("year")){
            // 월별 통계
            summary = orderListRepository.findOrderYear(year);
        }

        for(Object[] dto : summary){
            OrderCountResponseDto resDto = OrderCountResponseDto.builder()
                    .orderListDate(dto[0].toString())
                    .orderListCount(dto[2].toString())
                    .build();
            result.add(resDto);
        }

        return OrderCountListDto.builder()
                .type(type)
                .year(year)
                .month(month)
                .OrderCountList(result)
                .build();
    }

    // 식사 장소 선택 후 해당 회원의 order_list(장바구니) 생성
    @Transactional
    public OrderList userOrderList(Long userId) {
        OrderList orderList = OrderList.builder()
                .orderListTime(LocalDateTime.now())
                .orderListStatus(OrderListStatus.PREPARING)
                .orderListTotalPrice(0)
                // 비회원이면 null로 들어가도록
                .user(userRepository.findById(userId).orElse(null))
                .build();

        orderListRepository.save(orderList);
        return orderList;
    }

    // 결제 화면
    @Transactional
    public PaymentResDto payment(Long orderListId) {
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

        return paymentResDto;
    }

    // 주문 완료(주문번호와 고객 적립금 출력) 및 주문 시간과 상태 업데이트
    @Transactional
    public SuccessOrderResDto successOrder(Long userId, Long orderListId) {
        User user = userRepository.findById(userId).orElse(null);
        OrderList orderList = orderListRepository.findById(orderListId).orElse(null);

        // 주문 시간과 상태 업데이트
        orderList.updateTimeAndStatus(LocalDateTime.now(), OrderListStatus.COMPLETED);
        orderListRepository.save(orderList);

        // 주문번호와 고객 적립금 출력
        SuccessOrderResDto successOrderResDto = SuccessOrderResDto.builder()
                .userPoint(user.getUserPoint())
                .orderListId(orderList.getId())
                .build();

        return successOrderResDto;
    }

    // 주문 확인 상세 페이지 (주문 상품의 이름, 가격, 개수 및 총 수량, 총 가격 출력)
    @Transactional
    public OrderDetailResDto orderDetail(Long orderListId) {
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

        return orderDetailResDto;
    }


}
