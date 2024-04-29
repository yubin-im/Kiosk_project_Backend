package com.study.springboot.domain.orderSystem.service;


import com.study.springboot.domain.orderSystem.OrderItem;
import com.study.springboot.domain.orderSystem.OrderList;
import com.study.springboot.domain.orderSystem.repository.OrderItemRepository;
import com.study.springboot.domain.orderSystem.repository.OrderListRepository;
import com.study.springboot.domain.product.Product;
import com.study.springboot.domain.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderItemService {
    private final OrderItemRepository orderItemRepository;
    private final ProductRepository productRepository;
    private final OrderListRepository orderListRepository;

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

    // 장바구니에 상품 추가
    @Transactional
    public OrderItem addProduct(Long productId, Long orderListId) {
        Product product = productRepository.findById(productId).orElse(null);
        OrderList orderList = orderListRepository.findById(orderListId).orElse(null);

        OrderItem orderItem = OrderItem.builder()
                .orderAmount(1)
                .orderPrice(product.getProductPrice())
                .product(product)
                .orderList(orderList)
                .build();

        orderItemRepository.save(orderItem);
        return orderItem;
    }
}
