package com.study.springboot.domain.orderSystem.service;

import com.study.springboot.domain.orderSystem.OrderList;
import com.study.springboot.domain.orderSystem.dto.OrderListDto;
import com.study.springboot.domain.orderSystem.dto.OrderListUpdateDto;
import com.study.springboot.domain.orderSystem.repository.OrderListRepository;
import com.study.springboot.enumeration.OrderListStatus;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

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

}
