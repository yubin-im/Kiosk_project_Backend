package com.study.springboot.domain.orderSystem.repository;

import com.study.springboot.domain.orderSystem.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
