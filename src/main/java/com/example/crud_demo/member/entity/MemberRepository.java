package com.example.crud_demo.member.entity;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Integer> {

    // Email Check
    boolean existByEmail(String email);

    // List
    public Page<Member> findAll(Pageable pageable);
    // Page => 페이징 처리를 위한 기본 클래스
    // Pageable => 인터페이스(페이징 관련)
    // PageRequest => 구현체(구현 클래스) => 몇 페이지 보여줘? 몇 개 출력해줘? 등을 설정 => of() 메서드 사용
    // PageRequest는 Pageable 객체를 간편하게 생성할수 있도록 of() 메서드를 제공
}
