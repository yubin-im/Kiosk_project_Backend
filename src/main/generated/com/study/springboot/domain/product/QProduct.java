package com.study.springboot.domain.product;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QProduct is a Querydsl query type for Product
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QProduct extends EntityPathBase<Product> {

    private static final long serialVersionUID = 337827747L;

    public static final QProduct product = new QProduct("product");

    public final EnumPath<com.study.springboot.enumeration.CategoryProduct> category = createEnum("category", com.study.springboot.enumeration.CategoryProduct.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath productCode = createString("productCode");

    public final StringPath productImgUrl = createString("productImgUrl");

    public final StringPath productName = createString("productName");

    public final NumberPath<Integer> productPrice = createNumber("productPrice", Integer.class);

    public QProduct(String variable) {
        super(Product.class, forVariable(variable));
    }

    public QProduct(Path<? extends Product> path) {
        super(path.getType(), path.getMetadata());
    }

    public QProduct(PathMetadata metadata) {
        super(Product.class, metadata);
    }

}

