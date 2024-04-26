package com.study.springboot.domain.member.repository;

import com.study.springboot.domain.member.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MemberRepository extends JpaRepository<User, Long> {
}
