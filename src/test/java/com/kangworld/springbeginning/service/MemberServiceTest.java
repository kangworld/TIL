package com.kangworld.springbeginning.service;

import com.kangworld.springbeginning.domain.Member;
import com.kangworld.springbeginning.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {
    private MemoryMemberRepository memberRepository;
    private MemberService memberService;

    @BeforeEach
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    void join() {
        // given
        Member member = new Member();

        member.setName("kangworld1");

        // when
        Long saveId = memberService.join(member);

        // then
        Member findMember = memberService.findOne(saveId).get();

        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    /**
     * 중복 검사 로직은 어떻게 테스트 할 것인가.
     * <p>
     * 중복 검사 로직은 조건에 따라 throw 예외를 던지고 있음
     */
    @Test
    public void duplicateExceptionTest() {
        // given
        Member member1 = new Member();
        Member member2 = new Member();

        member1.setName("kangworld1");
        member2.setName("kangworld2");

        // when
        memberService.join(member1);
        // #1 : try-catch
//        try {
//            memberService.join(member1);
//            fail();
//        }catch (IllegalArgumentException e){
//            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
//        }

        // #2 : assertThrows 사용
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> {
            memberService.join(member1);
        });

        assertThat(illegalArgumentException.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}