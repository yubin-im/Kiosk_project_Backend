package com.study.springboot.domain.product.entity;

import com.study.springboot.domain.category.entity.Category;
import com.study.springboot.domain.orderItem.entity.OrderItem;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "product")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "product_price")
    private int productPrice;

    @Column(name = "product_img_url")
    private String productImgUrl;

    @Column(name = "product_code")
    private String productCode;

    // product: category = n : 1
    @ManyToOne
    private Category category;

    // product: order_item = 1 : n
    @OneToMany(mappedBy = "product")
    private List<OrderItem> orderItemList = new ArrayList<>();
}
