package com.study.springboot.domain.orderSystem.service;

import com.study.springboot.domain.orderSystem.dto.*;
import com.study.springboot.domain.orderSystem.OrderList;
import com.study.springboot.domain.orderSystem.repository.OrderListRepository;
import com.study.springboot.enumeration.OrderListStatus;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderListService {

    private final OrderListRepository orderListRepository;

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
}
