package com.study.springboot.domain.orderSystem;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QOrderItems is a Querydsl query type for OrderItems
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QOrderItems extends EntityPathBase<OrderItems> {

    private static final long serialVersionUID = 17857648L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QOrderItems orderItems = new QOrderItems("orderItems");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.study.springboot.domain.member.QMember member;

    public final QOrderList orderList;

    public final com.study.springboot.domain.product.QProduct product;

    public QOrderItems(String variable) {
        this(OrderItems.class, forVariable(variable), INITS);
    }

    public QOrderItems(Path<? extends OrderItems> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QOrderItems(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QOrderItems(PathMetadata metadata, PathInits inits) {
        this(OrderItems.class, metadata, inits);
    }

    public QOrderItems(Class<? extends OrderItems> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new com.study.springboot.domain.member.QMember(forProperty("member")) : null;
        this.orderList = inits.isInitialized("orderList") ? new QOrderList(forProperty("orderList")) : null;
        this.product = inits.isInitialized("product") ? new com.study.springboot.domain.product.QProduct(forProperty("product")) : null;
    }

}

