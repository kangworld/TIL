package com.kangworld.springbeginning.service;

import com.kangworld.springbeginning.domain.Member;
import com.kangworld.springbeginning.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
/**
 * @Transactional : 테스트 케이스에서, 테스트를 시작할 때 transaction을 실행하고 db에 데이터를 insert하고 rollback을 함
 *
 * 참고 : DB에 어떤 데이터를 insert한 다음에 commit을 해줘야 최종 반영이 됨
 */
@Transactional
class MemberServiceIntegrationTest {
    @Autowired
    private MemberService memberService;
    @Autowired
    private MemberRepository memberRepository;

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