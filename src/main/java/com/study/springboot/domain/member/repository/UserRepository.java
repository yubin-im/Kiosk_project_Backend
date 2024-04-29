package com.study.springboot.domain.member.repository;

import com.study.springboot.domain.member.User;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Pageable;
import java.util.List;


@Repository
public interface MemberRepository extends JpaRepository<User, Long> {
    Page<User> findByUserIdContains(String text, Pageable pageable);
    Page<User> findByUserNameContains(String text, Pageable pageable);
    Optional<User> findByUserId(String userId);

}
