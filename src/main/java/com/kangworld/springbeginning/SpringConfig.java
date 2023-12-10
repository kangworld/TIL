package com.kangworld.springbeginning;

import com.kangworld.springbeginning.aop.TimeTraceAop;
import com.kangworld.springbeginning.repository.MemberRepository;
import com.kangworld.springbeginning.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

@Configuration
public class SpringConfig {
    /**
     * DataSource : JDBC, JDBCTemplate 용도
     * EntityManager : JPA 용도
     */
    private DataSource dataSource;
    private EntityManager em;
    private final MemberRepository memberRepository;

    /**
     * application.properties에 데이터 베이스 연결 설정을 추가하면
     * 스프링이 자체적으로 DataSource 빈을 생성함
     * 따라서 @Autowired로 주입하면 됨
     *
     * @param dataSource
     */
    @Autowired
    public SpringConfig(DataSource dataSource, EntityManager em, MemberRepository memberRepository) {
        this.dataSource = dataSource;
        this.em = em;
        this.memberRepository = memberRepository;
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public TimeTraceAop timeTraceAop() {
        return new TimeTraceAop();
    }

    @Bean
    public MemberRepository memberRepository() {
//        return new MemoryMemberRepository();
//        return new JdbcMemberRepository(dataSource);
//        return new JdbcTemplateMemberRepository(dataSource);
//        return new JpaMemberRepository(em);
        return memberRepository;
    }
}
