package com.study.springboot.domain.orderSystem.service;


import com.study.springboot.domain.orderSystem.OrderItem;
import com.study.springboot.domain.orderSystem.repository.OrderItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderItemService {
    private final OrderItemRepository orderItemRepository;

    // 상품 개수 1개 추가
    @Transactional
    public Integer addAmount(Long orderListId, Long productId) {
        OrderItem orderItem = orderItemRepository.findByOrderListIdAndProductId(orderListId, productId).orElse(null);

        Integer newOrderAmount = orderItem.getOrderAmount() + 1;
        orderItem.updateOrderAmount(newOrderAmount);
        orderItemRepository.save(orderItem);

        return newOrderAmount;
    }

    // 상품 개수 1개 삭제
    @Transactional
    public Integer removeAmount(Long orderListId, Long productId) {
        OrderItem orderItem = orderItemRepository.findByOrderListIdAndProductId(orderListId, productId).orElse(null);

        Integer newOrderAmount = orderItem.getOrderAmount() - 1;
        orderItem.updateOrderAmount(newOrderAmount);
        orderItemRepository.save(orderItem);

        return newOrderAmount;
    }

}
