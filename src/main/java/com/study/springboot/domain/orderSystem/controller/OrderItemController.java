package com.study.springboot.domain.orderSystem.controller;

import com.study.springboot.domain.orderSystem.OrderItem;
import com.study.springboot.domain.orderSystem.dto.AddProductReqDto;
import com.study.springboot.domain.orderSystem.dto.AmountControlReqDto;
import com.study.springboot.domain.orderSystem.service.OrderItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class OrderItemController {
    final private OrderItemService orderItemService;

    // 상품 개수 1개 추가
    @PostMapping("/order/addAmount")
    public Map<String, Integer> addAmount(@RequestBody AmountControlReqDto amountControlReqDto) {
        Integer orderAmount = orderItemService.addAmount(amountControlReqDto.getOrderListId(), amountControlReqDto.getProductId());

        Map<String, Integer> response = new HashMap<>();
        response.put("orderAmount", orderAmount);
        return response;
    }

    // 상품 개수 1개 삭제
    @PostMapping("/order/removeAmount")
    public Map<String, Integer> removeAmount(@RequestBody AmountControlReqDto amountControlReqDto) {
        Integer orderAmount = orderItemService.removeAmount(amountControlReqDto.getOrderListId(), amountControlReqDto.getProductId());

        Map<String, Integer> response = new HashMap<>();
        response.put("orderAmount", orderAmount);
        return response;
    }

    // 장바구니에 상품 추가
    @PostMapping("/order/addProduct")
    public OrderItem addProduct(@RequestBody AddProductReqDto addProductReqDto) {
        Long productId = addProductReqDto.getProductId();
        Long orderListId = addProductReqDto.getOrderListId();

        OrderItem orderItem = orderItemService.addProduct(productId, orderListId);

        return orderItem;
    }

}
