package com.study.springboot.domain.product.service;


import com.study.springboot.datas.Message;
import com.study.springboot.domain.orderSystem.OrderItem;
import com.study.springboot.domain.orderSystem.OrderList;
import com.study.springboot.domain.orderSystem.repository.OrderListRepository;
import com.study.springboot.domain.product.Product;
import com.study.springboot.domain.product.dto.*;
import com.study.springboot.domain.product.repository.ProductRepository;
import com.study.springboot.enumeration.ProductCategory;
import com.study.springboot.enumeration.error.StatusCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final OrderListRepository orderListRepository;


    @Transactional(readOnly = true)
    public List<ProductDto> findAll(){
        List<Product> products = productRepository.findAll();
        return products.stream().map(ProductDto::new).collect(Collectors.toList());
    }


    @Transactional(readOnly = true)
    public Optional<Product> findByCode(String code){
        return productRepository.findProductByProductCode(code);
    }

    // 제품 9개 랜덤 추천 기능 (함께 즐기면 더욱 좋습니다!)
    @Transactional
    public List<RecommendProductDto> recommendProduct() {
        List<Product> productList = productRepository.findAll();
        List<RecommendProductDto> randomProducts = new ArrayList<>();
        Random random = new Random();

        // 랜덤으로 9개의 제품 출력
        for (int i = 0; i < 9; i++) {
            int randomIndex = random.nextInt(productList.size());
            Product product = productList.get(randomIndex);

            RecommendProductDto recommendProductDto = RecommendProductDto.builder()
                    .productCode(product.getProductCode())
                    .productName(product.getProductName())
                    .productPrice(product.getProductPrice())
                    .productImgUrl(product.getProductImgUrl())
                    .build();
            randomProducts.add(recommendProductDto);
        }

        // Message에 추가


        return randomProducts;
    }

    // 메인 화면- 카테고리 별 제품 전체 출력(페이징 9개씩), 사용자 이름 출력, 총가격 및 총수량 출력
    @Transactional
    public Optional<ProductsResDto> getProductsByCategory(ProductCategory category, Pageable pageable, Long orderListId) {
        Page<Product> products = productRepository.findByCategory(category, pageable);
        OrderList orderList = orderListRepository.findById(orderListId).orElse(null);
        if(orderList == null){
            return Optional.empty();
        }
        List<OrderItem> orderItemList = orderList.getOrderItems();

        // 카테고리별 9개씩 제품 출력
        List<ProductsByCategoryDto> productDtos = products.getContent().stream()
                .map(product -> new ProductsByCategoryDto(
                        product.getProductCode(),
                        product.getProductName(),
                        product.getProductPrice(),
                        product.getProductImgUrl()))
                .collect(Collectors.toList());

        // 사용자 이름 출력
        String userName = orderList.getUser().getUserName();

        // 총 수량 계산
        Integer orderListTotalAmount = 0;
        for(int i = 0; i < orderItemList.size(); i++) {
            orderListTotalAmount += orderItemList.get(i).getOrderAmount();
        }

        // 총 금액
        Integer orderListTotalPrice = orderList.getOrderListTotalPrice();


        ProductsResDto productsResDto = ProductsResDto.builder()
                .productDtos(productDtos)
                .userName(userName)
                .orderListTotalAmount(orderListTotalAmount)
                .orderListTotalPrice(orderListTotalPrice)
                .build();

        return Optional.ofNullable(productsResDto);
    }

    // 메인 화면- 카테고리 별 제품 전체 출력(페이징 9개씩), 부가 기능없이 제품만 출력하는 메소드
    @Transactional
    public List<ProductsByCategoryDto> onlyGetProductsByCategory(ProductCategory category, Pageable pageable) {
        System.out.println("cat : " + category);
        Page<Product> products = productRepository.findByCategory(category, pageable);

        // 카테고리별 9개씩 제품 출력
        List<ProductsByCategoryDto> productDtos = products.getContent().stream()
                .map(product -> new ProductsByCategoryDto(
                        product.getProductCode(),
                        product.getProductName(),
                        product.getProductPrice(),
                        product.getProductImgUrl()))
                .collect(Collectors.toList());


        productDtos.stream().forEach(o->{
            System.out.println(o.getProductName() );
        });
        return productDtos;

    }


}
