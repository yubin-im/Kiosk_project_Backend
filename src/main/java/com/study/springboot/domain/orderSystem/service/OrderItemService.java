package com.study.springboot.domain.orderSystem.service;


import com.study.springboot.datas.Message;
import com.study.springboot.domain.orderSystem.OrderItem;
import com.study.springboot.domain.orderSystem.OrderList;
import com.study.springboot.domain.orderSystem.dto.AmountControlResDto;
import com.study.springboot.domain.orderSystem.repository.OrderItemRepository;
import com.study.springboot.domain.orderSystem.repository.OrderListRepository;
import com.study.springboot.domain.product.Product;
import com.study.springboot.domain.product.repository.ProductRepository;
import com.study.springboot.enumeration.error.StatusCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderItemService {
    private final OrderItemRepository orderItemRepository;
    private final ProductRepository productRepository;
    private final OrderListRepository orderListRepository;

    // 상품 개수 1개 추가 및 총 상품 가격 변경
    @Transactional
    public Message addAmount(Long orderListId, Long productId) {
        OrderItem orderItem = orderItemRepository.findByOrderListIdAndProductId(orderListId, productId).orElse(null);
        Product product = productRepository.findById(productId).orElse(null);

        // 상품 개수 1개 추가
        Integer newOrderAmount = orderItem.getOrderAmount() + 1;
        orderItem.updateOrderAmount(newOrderAmount);

        // 상품 총 가격 변경
        Integer newOrderPrice = product.getProductPrice() * newOrderAmount;
        orderItem.updateOrderPrice(newOrderPrice);
        orderItemRepository.save(orderItem);

        AmountControlResDto amountControlResDto = AmountControlResDto.builder()
                .orderAmount(newOrderAmount)
                .orderPrice(newOrderPrice)
                .build();

        // Message에 추가
        Message message = Message.builder()
                .status(StatusCode.PRODUCT_EDIT_SUCCESS)
                .code(StatusCode.PRODUCT_EDIT_SUCCESS.getValue())
                .message("상품 수량이 변경되었습니다!")
                .result(amountControlResDto)
                .build();

        return message;
    }

    // 상품 개수 1개 삭제 및 총 상품 가격 변경
    @Transactional
    public Message removeAmount(Long orderListId, Long productId) {
        OrderItem orderItem = orderItemRepository.findByOrderListIdAndProductId(orderListId, productId).orElse(null);
        Product product = productRepository.findById(productId).orElse(null);

        // 상품 개수 1개 삭제
        Integer newOrderAmount = orderItem.getOrderAmount() - 1;
        orderItem.updateOrderAmount(newOrderAmount);

        // 상품 총 가격 변경
        Integer newOrderPrice = product.getProductPrice() * newOrderAmount;
        orderItem.updateOrderPrice(newOrderPrice);
        orderItemRepository.save(orderItem);

        AmountControlResDto amountControlResDto = AmountControlResDto.builder()
                .orderAmount(newOrderAmount)
                .orderPrice(newOrderPrice)
                .build();

        // Message에 추가
        Message message = Message.builder()
                .status(StatusCode.PRODUCT_EDIT_SUCCESS)
                .code(StatusCode.PRODUCT_EDIT_SUCCESS.getValue())
                .message("상품 수량이 변경되었습니다!")
                .result(amountControlResDto)
                .build();

        return message;
    }

    // 장바구니에 상품 추가
    @Transactional
    public Message addProduct(Long productId, Long orderListId) {
        Product product = productRepository.findById(productId).orElse(null);
        OrderList orderList = orderListRepository.findById(orderListId).orElse(null);

        OrderItem orderItem = OrderItem.builder()
                .orderAmount(1)
                .orderPrice(product.getProductPrice())
                .product(product)
                .orderList(orderList)
                .build();

        orderItemRepository.save(orderItem);

        // Message에 추가
        Message message = Message.builder()
                .status(StatusCode.PRODUCT_ADD_SUCCESS)
                .code(StatusCode.PRODUCT_ADD_SUCCESS.getValue())
                .message("장바구니에 상품이 추가되었습니다!")
                .result(orderItem)
                .build();

        return message;
    }

    // 장바구니에 상품 삭제
    @Transactional
    public Message removeProduct(Long orderListId, Long productId) {
        OrderItem orderItem = orderItemRepository.findByOrderListIdAndProductId(orderListId, productId).orElse(null);
        orderItemRepository.delete(orderItem);

        // Message에 추가
        Message message = Message.builder()
                .status(StatusCode.PRODUCT_REMOVE_SUCCESS)
                .code(StatusCode.PRODUCT_REMOVE_SUCCESS.getValue())
                .message("상품 삭제가 완료되었습니다!")
                .result("상품 삭제가 완료되었습니다!")
                .build();

        return message;
    }
}
