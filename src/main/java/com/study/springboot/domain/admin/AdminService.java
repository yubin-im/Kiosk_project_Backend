package com.study.springboot.domain.admin;


import com.querydsl.jpa.impl.JPAQueryFactory;
import com.study.springboot.datas.AdminMessage;
import com.study.springboot.datas.Message;
import com.study.springboot.domain.product.Product;
import com.study.springboot.domain.product.QProduct;
import com.study.springboot.domain.product.dto.ProductDto;
import com.study.springboot.domain.product.dto.RequestProductEditDto;
import com.study.springboot.domain.product.repository.ProductRepository;
import com.study.springboot.domain.product.service.ProductService;
import com.study.springboot.enumeration.ProductCategory;
import com.study.springboot.enumeration.SearchCategory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final ProductRepository productRepository;
    private final ProductService productService;

    private final JPAQueryFactory queryFactory;


    @Transactional(readOnly = true)
    public List<ProductDto> findAllProduct(){
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

    @Transactional(readOnly = true)
    List<Product> findProductsBy(final ProductCategory category, final String searchKeyword, final int page, final int pageSize){
//        List<Sort.Order> sorts = new ArrayList<>();
//        Sort.Order sort = Sort.Order.desc(category.getValue());
//        sorts.add(sort);

//        System.out.println("category = " + category);

//        Pageable pageable = PageRequest.of(page, pageSize, Sort.by(sorts));

//        List<ProductDto> list = productRepository.findAll().stream().map(ProductDto::new).collect(Collectors.toList());

        QProduct product = QProduct.product;
        List<Product> list = queryFactory.selectFrom(product)
                .where(
                        product.category.eq(category)
                                .and(
                                        product.productCode.contains(searchKeyword)
                                                .or(product.productName.contains(searchKeyword))
                                )
                )
                .fetch();

        return list;

    }
}

