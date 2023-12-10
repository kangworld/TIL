package com.kangworld.springbeginning.repository;

import com.kangworld.springbeginning.domain.Member;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class JdbcTemplateMemberRepository implements MemberRepository{
    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplateMemberRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Member save(Member member) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        // insert할 테이블과 auto generated key 명시
        jdbcInsert.withTableName("member").usingGeneratedKeyColumns("id");

        // key - value
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", member.getName());

        // 테이블 이름과 컬럼 이름과 값만 있으면 insert문을 만들 수 있듯, 필요한 정보만 SimpleJdbcInsert에 넘겨준다.
        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
        member.setId(key.longValue());

        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        List<Member> result = jdbcTemplate.query("select * from member where id = ?", memberRowMapper(), id);

        return result.stream().findAny();
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = jdbcTemplate.query("select * from member where name = ?", memberRowMapper(), name);

        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        List<Member> members = jdbcTemplate.query("select * from member", memberRowMapper());

        return members;
    }

    private RowMapper<Member> memberRowMapper(){
        return (rs, rowNum) -> {
            Member member = new Member();

            member.setId(rs.getLong("id"));
            member.setName(rs.getString("name"));

            return member;
        };
    }
}
