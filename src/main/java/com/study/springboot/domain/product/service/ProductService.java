package com.study.springboot.domain.product.service;


import com.study.springboot.datas.AdminMessage;
import com.study.springboot.datas.Message;
import com.study.springboot.domain.product.Product;
import com.study.springboot.domain.product.dto.ProductDto;
import com.study.springboot.domain.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
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





}
