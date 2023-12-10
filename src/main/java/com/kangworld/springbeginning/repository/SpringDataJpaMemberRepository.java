package com.kangworld.springbeginning.repository;

import com.kangworld.springbeginning.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * JpaRepository<T, ID> : T는 entity, ID는 entity의 PK 타입
 *
 * 인터페이스만 있는데 구현체는 어디있는가? : 만약 인터페이스가 JpaRepository 상속받고있으면
 * Spring Data Jpa가 자동으로 구현체를 만들고 빈에 등록한다.
 */
public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {
    /**
     * Spring Data JPA가 기본적인 CRUD 메서드는 제공하지만
     * findByName과 같은 특수한 상황에 사용되는 메서드는 어떻게 해야할까?
     *
     * findBy(컬럼명)으로 추상 메서드를 선언하면 Spring Data JPA는
     * JPQL select m from Member as m where m.name = ?을 짜고 이후에 SQL로 번역돼서 쿼리문이 실행됨
     *
     * @param name
     * @return
     */
    @Override
    Optional<Member> findByName(String name);
}
