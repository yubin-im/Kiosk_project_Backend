package com.study.springboot.domain.orderSystem.repository;

import com.study.springboot.domain.orderSystem.OrderItem;
import com.study.springboot.domain.orderSystem.OrderList;
import com.study.springboot.domain.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    // 상품 개수 1개 추가/삭제
    OrderItem findOrderItemByOrderListAndProduct(OrderList orderList, Product product);
}
