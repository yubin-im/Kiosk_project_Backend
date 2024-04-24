package com.study.springboot.domain.member;

import jakarta.persistence.*;

@Entity
@Table(name = "member")
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
