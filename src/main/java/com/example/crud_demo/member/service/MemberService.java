package com.example.crud_demo.member.service;

import com.example.crud_demo.member.dto.MemberJoinDTO;
import com.example.crud_demo.member.dto.MemberUpdateDTO;
import com.example.crud_demo.member.dto.MemberUpdateUiDTO;
import com.example.crud_demo.member.entity.Member;
import com.example.crud_demo.member.entity.MemberRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    //DB:Insert
    public void insert(MemberJoinDTO memberJoinDTO) {
        //Member 타입의 객체 생성
        Member member = new Member();

        //setter
        member.setId(memberJoinDTO.getId());
        member.setPw(memberJoinDTO.getPw());

        //DB:Save
        this.memberRepository.save(member);

        //Return

    }

    // DB::Read
    public Member read(Integer idx) {
        // 조회 및 Return
        return this.memberRepository.findById(idx).orElseThrow();
    }

    // DB::Update UI (사용자가 요청한 임의의 회원에 대한 정보를 DB에 가져와서 업데이트 폼에 출력하는 용도)
    // DB::Update (실제 DB 에서 업데이트 처리)
    public MemberUpdateUiDTO updateUi(Integer idx) throws NoSuchElementException {
        // DB::Find
        Member member = this.memberRepository.findById(idx).orElseThrow();

        // Return
        return MemberUpdateUiDTO.makeMember(member);
    }

    // DB::Update
    public void update(MemberUpdateDTO memberUpdateDTO) throws NoSuchElementException {
        // DB::Find
        Member member = this.memberRepository.findById(memberUpdateDTO.getIdx()).orElseThrow();

        // DB 에서 가져온 member 정보를 사용자가 수정한 값으로 업데이트 처리 => change() 호출
        member = memberUpdateDTO.change(member);

        // DB::Save
        this.memberRepository.save(member);
    }

    // DB::Delete
    public String delete(int idx, String userPw) throws NoSuchElementException {

        // DB::Find
        Member member = this.memberRepository.findById(idx).orElseThrow();

        //사용자가 입력한 비번과 DB에 입력된 비번과 비교해서 일치하면 삭제
        if(!userPw.equals(member.getPw())) {
            // System.out.println("비밀번호가 틀립니다.");

            return "/member/delete-fail";
        } else {
            // System.out.println("비밀번호가 맞습니다.");

            //삭제
            this.memberRepository.delete(member);

            return "/member/delete-success";
        }
    }

    public Page<Member> memberList(int page) {
        //페이지 번호가 없으면
        if (page != 0) {
            page -= 1;
        }

        // 정렬 기준 적용
        Sort sort = Sort.by(Order.desc("idx"));

        // 페이지어블 변수 및 페이지 설정
        Pageable pageable = PageRequest.of(page, 3, sort);

        // Return
        return this.memberRepository.findAll(pageable);
    }
}
