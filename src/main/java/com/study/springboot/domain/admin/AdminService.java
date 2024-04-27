package com.study.springboot.domain.admin;


import com.study.springboot.datas.AdminMessage;
import com.study.springboot.datas.Message;
import com.study.springboot.domain.product.Product;
import com.study.springboot.domain.product.dto.ProductDto;
import com.study.springboot.domain.product.dto.RequestProductEditDto;
import com.study.springboot.domain.product.repository.ProductRepository;
import com.study.springboot.domain.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final ProductRepository productRepository;
    private final ProductService productService;


    @Transactional(readOnly = true)
    public List<ProductDto> findAll(){
        return productRepository.findAll().stream().map(ProductDto::new).collect(Collectors.toList());
    }


    @Transactional
    public Message setRemoveProductMessage(String productCode, String productName){
        Optional<Product> optional = productService.findByCode(productCode);

        //존재하지 않는 코드
        if(!optional.isPresent()){
            Message message = AdminMessage.productNotFoundMessage();
            return message;
        }


        Product product = optional.get();

        //코드명과 코드번호 불일치
        if(!product.getProductName().equals(productName)){
            Message message = AdminMessage.productCodeMisMatchMessage();
            return message;
        }

        //삭제 성공
        productRepository.delete(product);
        Message message = AdminMessage.productRemoveSuccessMessage();
        return message;
    }

    @Transactional(readOnly = true)
    Optional<Product> findProductByCode(String code){
        return productRepository.findProductByProductCode(code);
    }


    @Transactional
    Message editProduct(RequestProductEditDto dto){
        System.out.println("PC: " + dto.getProductCode());
        Optional<Product> optional = productRepository.findProductByProductCode(dto.getProductCode());

        if(!optional.isPresent()){
            return AdminMessage.productNotFoundMessage();
        }

        Product product = optional.get();
        Long id = product.getId();

        product = dto.toEntity(id);
        productRepository.save(product);

        return AdminMessage.productEditSuccess();

    }
}
