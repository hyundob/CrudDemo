package com.example.crud_demo.member.dto;

import com.example.crud_demo.member.entity.Member;

public class MemberUpdateUiDTO {

    // Field
    private Integer idx;
    private String id;
    private Integer pw;

    // fromMember
    public void fromMember(Member member) {
        this.idx = member.getIdx();
        this.id = member.getId();
        this.pw = member.getPw();
    }

    // makeMember
    public static MemberUpdateUiDTO makeMember(Member member) {
        // MemberUpdateUiDTO 객체 생성
        MemberUpdateUiDTO memberUpdateUiDTO = new MemberUpdateUiDTO();
        memberUpdateUiDTO.fromMember(member);

        return memberUpdateUiDTO;
    }

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

    public Integer getPw() {
        return pw;
    }

    public void setPw(Integer pw) {
        this.pw = pw;
    }
}
