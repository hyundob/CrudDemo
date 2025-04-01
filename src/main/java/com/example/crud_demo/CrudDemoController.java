package com.example.crud_demo;

import com.example.crud_demo.member.dto.MemberJoinDTO;
import com.example.crud_demo.member.entity.Member;
import com.example.crud_demo.member.entity.MemberRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CrudDemoController {

    @Autowired
    private MemberRepository memberRepository;

    public CrudDemoController(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @GetMapping("/member/memberJoin")
    public String hello() {
        return "member/memberJoin";
    }

    @GetMapping("/test")
    @ResponseBody
    public String test(HttpServletRequest req) {
        return req.getParameter("userId");
    }

    @PostMapping("/member/memberJoinOk")
    @ResponseBody
    public MemberJoinDTO insert(MemberJoinDTO memberJoinDTO) {

        //Member 타입의 객체 생성
        Member member = new Member();

        //Setter
        member.setId(memberJoinDTO.getId());
        member.setPw(memberJoinDTO.getPw());

        //DB:save
        memberRepository.save(member);
        return memberJoinDTO;
    }
}
