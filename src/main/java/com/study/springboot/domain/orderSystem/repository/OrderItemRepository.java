package com.study.springboot.domain.orderSystem.repository;

import com.study.springboot.domain.orderSystem.OrderItem;
import com.study.springboot.domain.orderSystem.OrderList;
import com.study.springboot.domain.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    // 상품 개수 1개 추가/삭제 위한 OrderItem 엔티티 찾기
    OrderItem findOrderItemByOrderListAndProduct(OrderList orderList, Product product);
    @Query("SELECT oi FROM OrderItem oi " +
            "JOIN oi.orderList ol " +
            "JOIN oi.product p " +
            "WHERE ol.id = :orderListId AND p.id = :productId")
    Optional<OrderItem> findByOrderListIdAndProductId(@Param("orderListId") Long orderListId, @Param("productId") Long productId);
}
