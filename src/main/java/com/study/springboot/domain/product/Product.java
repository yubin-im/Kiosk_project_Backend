package com.study.springboot.domain.product;


import com.study.springboot.enumeration.CategoryProduct;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(uniqueConstraints = {
        @UniqueConstraint(name = "ID_PRODUCT_CODE_CONSTRAINT", columnNames = {"id", "product_code"})
})
public class Product {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String productName;

    @Column
    private Integer productPrice;

    @Column(nullable = false)
    private String productCode;

    @Column
    private String productImgUrl;

    @Column
    @Enumerated(EnumType.STRING)
    private CategoryProduct category;

}
