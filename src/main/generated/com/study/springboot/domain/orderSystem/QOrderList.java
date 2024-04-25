package com.study.springboot.domain.orderSystem;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QOrderList is a Querydsl query type for OrderList
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QOrderList extends EntityPathBase<OrderList> {

    private static final long serialVersionUID = 1663223278L;

    public static final QOrderList orderList = new QOrderList("orderList");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final ListPath<OrderItems, QOrderItems> orderItems = this.<OrderItems, QOrderItems>createList("orderItems", OrderItems.class, QOrderItems.class, PathInits.DIRECT2);

    public QOrderList(String variable) {
        super(OrderList.class, forVariable(variable));
    }

    public QOrderList(Path<? extends OrderList> path) {
        super(path.getType(), path.getMetadata());
    }

    public QOrderList(PathMetadata metadata) {
        super(OrderList.class, metadata);
    }

}

