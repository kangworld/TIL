package com.kangworld.springbeginning.domain;

import lombok.Data;

import javax.persistence.*;

@Data
// @Entity : JPA가 관리하는 Entity
@Entity
public class Member {
    // @Id : PK 매핑
    // @GeneratedValue : Identity 전략 매핑 (sequence 같은 컬림)
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
}
