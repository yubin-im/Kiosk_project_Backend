package com.study.springboot.domain.orderSystem.service;


import com.study.springboot.domain.orderSystem.repository.OrderItemsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderItemsService {
    private final OrderItemsRepository orderItemsRepository;
}
