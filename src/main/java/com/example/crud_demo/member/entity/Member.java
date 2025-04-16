package com.example.crud_demo.member.entity;

import jakarta.persistence.*;

@Entity
public class Member {
    /**
     * Member.java 의 역할
     * - 데이터 베이스 연동을 할 때 아주 중요한 역할을 담당하는 파일
     * - DB 연동을 코드 레벨에서 처리하도록 해주는 파일 => JPA + ORM
     * - JPA : Java Persistence API
     * - ORM : Object Relational Mapping
     * - JPA 기술 => 데이터베이스 연동을 하는 방식 중 하나
     * - ORM 기술 => 데이터베이스 테이블을 프로그래밍 언어에서 사용하는 객체와 같이다루는 기술
     * - 위 2개의 기술을 같이 사용하면 DB 연동을 마치 프로그래밍 언어에서 객체 다루듯이 처리하는게 가능
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // Field
    private Integer idx;

    @Column(length = 16)
    private String id;

    @Column(length = 16)
    private String pw;

    @Column(unique = true)
    private String email;

    // Setter and Getter
    public Integer getIdx() {
        return idx;
    }

    public void setIdx(Integer idx) {
        this.idx = idx;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
