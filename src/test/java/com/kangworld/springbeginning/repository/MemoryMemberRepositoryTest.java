package com.kangworld.springbeginning.repository;

import com.kangworld.springbeginning.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach(){
        repository.clearStore();
    }

    @Test
    public void save(){
        Member member = new Member();

        member.setName("spring");
        repository.save(member);

        Optional<Member> optionalMember = repository.findById(member.getId());
        Member result = optionalMember.get();

        assertEquals(member, result);
        assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("kangworld1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("kangworld2");
        repository.save(member2);

        Member result = repository.findByName("kangworld1").get();
        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("kangworld1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("kangworld2");
        repository.save(member2);

        List<Member> members = repository.findAll();

        assertThat(members.size()).isEqualTo(2);
    }
}
