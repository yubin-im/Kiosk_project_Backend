package com.study.springboot.domain.product.service;


import com.study.springboot.datas.AdminMessage;
import com.study.springboot.datas.Message;
import com.study.springboot.domain.product.Product;
import com.study.springboot.domain.product.dto.ProductDto;
import com.study.springboot.domain.product.dto.RecommendProductDto;
import com.study.springboot.domain.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
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
                    .id(product.getId())
                    .productName(product.getProductName())
                    .productPrice(product.getProductPrice())
                    .productImgUrl(product.getProductImgUrl())
                    .build();
            randomProducts.add(recommendProductDto);
        }

        return randomProducts;
    }

}
