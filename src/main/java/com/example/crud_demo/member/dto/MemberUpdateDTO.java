package com.example.crud_demo.member.dto;

import com.example.crud_demo.member.entity.Member;

public class MemberUpdateDTO {
    // Field
    private Integer idx;
    private String id;
    private String pw;

    // Change Method
    public Member change(Member member) {

        // Setter 이용해서 전달된 member 값을(DB 에서 가져온 값) memberUpdateDTO(폼에서 수정해서 넘긴 값)으로 변경
        member.setId(this.id);
        member.setPw(this.pw);

        return member;
    }
    // setter and Getter
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
}
