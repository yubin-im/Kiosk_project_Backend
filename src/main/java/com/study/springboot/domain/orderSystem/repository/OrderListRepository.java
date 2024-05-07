package com.study.springboot.domain.orderSystem.repository;


import com.study.springboot.domain.orderSystem.OrderList;
import com.study.springboot.enumeration.OrderListStatus;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;

import java.util.List;

@Repository
public interface OrderListRepository extends JpaRepository<OrderList, Long> {
    Page<OrderList> findByOrderListStatus(OrderListStatus status, Pageable pageable);
    List<OrderList> findByOrderListStatus(OrderListStatus status);

    // 주문 금액 통계 - 해당 월
    @Query(value = "SELECT date(OL.ORDER_LIST_TIME) as orderListDate, SUM(OL.ORDER_LIST_TOTAL_PRICE) as totalPrice, COUNT(OL.id) as OrderListCount " +
            "FROM order_list OL " +
            "WHERE YEAR(OL.ORDER_LIST_TIME)= :year AND MONTH(OL.ORDER_LIST_TIME) = :month " +
            "GROUP BY date(OL.ORDER_LIST_TIME)", nativeQuery = true)
    List<Object[]> findOrderMonth(@Param("year")int year, @Param("month")int month);

    // 주문 금액 통계 - 해당 년도
    @Query(value = "SELECT MONTH(OL.ORDER_LIST_TIME) as orderListDate, SUM(OL.ORDER_LIST_TOTAL_PRICE) as totalPrice, COUNT(OL.id) as OrderListCount " +
            "FROM order_list OL " +
            "WHERE YEAR(OL.ORDER_LIST_TIME)= :year " +
            "GROUP BY MONTH(OL.ORDER_LIST_TIME)", nativeQuery = true)
    List<Object[]> findOrderYear(@Param("year")int year);
}
