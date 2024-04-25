package com.study.springboot.domain.orderSystem.repository;

import com.study.springboot.domain.orderSystem.OrderItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface OrderItemsRepository extends JpaRepository<OrderItems, Long> {
}
