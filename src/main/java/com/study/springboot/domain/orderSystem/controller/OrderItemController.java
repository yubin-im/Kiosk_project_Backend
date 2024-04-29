package com.study.springboot.domain.orderSystem.controller;

import com.study.springboot.domain.orderSystem.service.OrderItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderItemController {
    final private OrderItemService orderItemService;

}
