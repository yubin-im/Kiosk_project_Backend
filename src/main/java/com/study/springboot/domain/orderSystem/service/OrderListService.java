package com.study.springboot.domain.orderSystem.service;

import com.study.springboot.domain.orderSystem.repository.OrderListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderListService {

    private final OrderListRepository orderListRepository;

}
