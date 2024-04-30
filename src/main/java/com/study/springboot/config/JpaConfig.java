package com.study.springboot.config;


import com.querydsl.jpa.impl.JPAQueryFactory;
import com.study.springboot.domain.user.User;
import com.study.springboot.domain.orderSystem.OrderItem;
import com.study.springboot.domain.product.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackageClasses = {User.class, Product.class, OrderItem.class})
@Configuration
public class JpaConfig {

    @PersistenceContext
    private EntityManager entityManager;

    @Bean
    public JPAQueryFactory jpaQueryFactory() {
        return new JPAQueryFactory(entityManager);
    }
}
