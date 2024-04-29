package com.study.springboot.domain.orderSystem.repository;


import com.study.springboot.domain.orderSystem.OrderItem;
import com.study.springboot.domain.orderSystem.OrderList;
import com.study.springboot.enumeration.OrderListStatus;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;

@Repository
public interface OrderListRepository extends JpaRepository<OrderList, Long> {
    Page<OrderList> findByOrderListStatus(OrderListStatus status, Pageable pageable);
}
