package com.study.springboot.domain.member;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QMember is a Querydsl query type for Member
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMember extends EntityPathBase<Member> {

    private static final long serialVersionUID = -76063199L;

    public static final QMember member = new QMember("member1");

    public final EnumPath<com.study.springboot.enumeration.CategoryProduct> category = createEnum("category", com.study.springboot.enumeration.CategoryProduct.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath memberId = createString("memberId");

    public final DatePath<java.time.LocalDate> memberJoinDate = createDate("memberJoinDate", java.time.LocalDate.class);

    public final NumberPath<Integer> memberPoint = createNumber("memberPoint", Integer.class);

    public final StringPath memberPw = createString("memberPw");

    public QMember(String variable) {
        super(Member.class, forVariable(variable));
    }

    public QMember(Path<? extends Member> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMember(PathMetadata metadata) {
        super(Member.class, metadata);
    }

}

