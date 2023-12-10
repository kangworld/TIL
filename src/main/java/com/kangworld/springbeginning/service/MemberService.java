package com.kangworld.springbeginning.service;

import com.kangworld.springbeginning.domain.Member;
import com.kangworld.springbeginning.repository.MemberRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * JPA를 사용할 땐, Service에 @Transactional 어노테이션을 붙여줘야한다.
 * JPA를 통한 모든 데이터 변경은 트랜잭션 안에서 실행해야함
 */
@Transactional
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원 가입
     * 같은 이름이 있는 중복 회원 허가 X
     *
     * @param member
     * @return
     */
    public Long join(Member member) {
        validateDuplicateMember(member);
        memberRepository.save(member);

        return member.getId();
    }

    /**
     * 중복 회원 검사 메서드
     * memberName을 보고 중복 검사
     *
     * @param member
     */
    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalArgumentException("이미 존재하는 회원입니다.");
                });
    }

    /**
     * 전체 회원 조회
     *
     * @return
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    /**
     * memberId에 매핑되는 member 반환
     *
     * @param memberId
     * @return
     */
    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
