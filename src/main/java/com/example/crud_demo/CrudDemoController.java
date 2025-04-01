package com.example.crud_demo;

import com.example.crud_demo.member.dto.MemberJoinDTO;
import com.example.crud_demo.member.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CrudDemoController {

    @Autowired
    private MemberService memberService;

    @GetMapping("/")
    public String home() {
        return "index";
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
    public String insert(MemberJoinDTO memberJoinDTO) {
        //MemberService > Insert() 메서드 호출
        Integer idx = memberService.insert(memberJoinDTO);

        //Return
        return String.format("Member idx => %s", idx);
    }
}
