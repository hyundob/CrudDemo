package com.example.crud_demo.member.service;

import com.example.crud_demo.member.dto.MemberJoinDTO;
import com.example.crud_demo.member.entity.Member;
import com.example.crud_demo.member.entity.MemberRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    //DB:Insert
    public Integer insert(MemberJoinDTO memberJoinDTO) {
        //Member 타입의 객체 생성
        Member member = new Member();

        //setter
        member.setId(memberJoinDTO.getId());
        member.setPw(memberJoinDTO.getPw());

        //DB:Save
        this.memberRepository.save(member);

        //Return
        return member.getIdx();

    }
}
