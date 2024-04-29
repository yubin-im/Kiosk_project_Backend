package com.study.springboot.domain.orderSystem.service;


import com.study.springboot.domain.orderSystem.OrderItem;
import com.study.springboot.domain.orderSystem.OrderList;
import com.study.springboot.domain.orderSystem.repository.OrderItemRepository;
import com.study.springboot.domain.product.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderItemService {
    private final OrderItemRepository orderItemRepository;

    // 상품 개수 1개 추가
    @Transactional
    public Integer addAmount(OrderList orderList, Product product) {
        OrderItem orderItem = orderItemRepository.findOrderItemByOrderListAndProduct(orderList, product);

        Integer orderAmount = orderItem.getOrderAmount() + 1;
        return orderAmount;
    }

    // 상품 개수 1개 삭제
    @Transactional
    public Integer removeAmount(OrderList orderList, Product product) {
        OrderItem orderItem = orderItemRepository.findOrderItemByOrderListAndProduct(orderList, product);

        Integer orderAmount = orderItem.getOrderAmount() - 1;
        return orderAmount;
    }

}
