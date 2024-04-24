package com.study.springboot.domain.category.entity;

import com.study.springboot.domain.product.entity.Product;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "category")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "category_name")
    private String categoryName;

    // Category: Product = 1 : n
    @OneToMany(mappedBy = "category")
    private List<Product> productList = new ArrayList<>();
}
