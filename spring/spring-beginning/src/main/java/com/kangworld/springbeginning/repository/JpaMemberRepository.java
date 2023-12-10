package com.kangworld.springbeginning.repository;

import com.kangworld.springbeginning.domain.Member;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository {
    /**
     * JPA는 EntityManager로 모든것이 동작함
     * <p>
     * build.gradle에 data-jpa 라이브러리를 추가하면 spring boot가 자동으로 EntityManager를 빈으로 관리함
     */
    private final EntityManager em;

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        em.persist(member);

        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);

        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> members = em.createQuery("select m from Member as m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();

        return members.stream().findAny();
    }

    /**
     * findAll과 같은 PK가 아닌 컬럼으로 조회를 하고싶다면 JPQL을 사용해야함
     * <p>
     * JPQL : 테이블 대상으로 쿼리를 날리는게 아닌 X, 객체(Entity)를 대상으로 쿼리를 날리면 SQL로 번역됨
     *
     * @return
     */
    @Override
    public List<Member> findAll() {
        return em.createQuery("select m from Member as m", Member.class)
                .getResultList();
    }
}
