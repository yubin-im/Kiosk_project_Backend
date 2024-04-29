package com.study.springboot.domain.user;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUser is a Querydsl query type for User
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUser extends EntityPathBase<User> {

    private static final long serialVersionUID = 591274817L;

    public static final QUser user = new QUser("user");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final ListPath<com.study.springboot.domain.orderSystem.OrderList, com.study.springboot.domain.orderSystem.QOrderList> orderList = this.<com.study.springboot.domain.orderSystem.OrderList, com.study.springboot.domain.orderSystem.QOrderList>createList("orderList", com.study.springboot.domain.orderSystem.OrderList.class, com.study.springboot.domain.orderSystem.QOrderList.class, PathInits.DIRECT2);

    public final StringPath userId = createString("userId");

    public final DatePath<java.time.LocalDate> userJoinDate = createDate("userJoinDate", java.time.LocalDate.class);

    public final StringPath userName = createString("userName");

    public final NumberPath<Integer> userPoint = createNumber("userPoint", Integer.class);

    public final StringPath userPw = createString("userPw");

    public final EnumPath<com.study.springboot.enumeration.UserRole> userRole = createEnum("userRole", com.study.springboot.enumeration.UserRole.class);

    public QUser(String variable) {
        super(User.class, forVariable(variable));
    }

    public QUser(Path<? extends User> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUser(PathMetadata metadata) {
        super(User.class, metadata);
    }

}

