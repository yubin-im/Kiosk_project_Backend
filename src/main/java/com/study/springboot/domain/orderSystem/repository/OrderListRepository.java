package com.study.springboot.domain.orderSystem.repository;


import com.study.springboot.domain.orderSystem.OrderList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderListRepository extends JpaRepository<OrderList, Long> {
}
