package com.example.crud_demo;

import com.example.crud_demo.member.dto.MemberJoinDTO;
import com.example.crud_demo.member.entity.Member;
import com.example.crud_demo.member.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.NoSuchElementException;

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

    // @PathVariable 애너테이션
    // ID를 통한 Member 타입의 객체 조회 => Read
    // REST API 프로젝트 => 주소체계 => http://localhost:8080/api/member/1
//    @GetMapping("/member/read/{idx}")
//    @ResponseBody
//    public Integer read(@PathVariable Integer idx) {
//        return idx;
//    }

    //여러개 처리
//    @GetMapping("/member/read/{idx}/{id}")
//    @ResponseBody
//    public String findByIdxAndId(@PathVariable("idx") Integer idx, @PathVariable("id") String id) {
//        return "{ idx : "+idx+", id : "+id+" }";
//    }
    // Model vs ModelAndView
    // Model => 데이터
    // View  => 보여지는 페이지
    // 둘다 컨트롤러에서 뷰로 데이터를 전달
    // Model과 ModelAndView 둘 다 스프링부트 프레임워크에서 MVC 패턴을 구현하는 데 있어서 중요하게 사용됨
    // 일단 Model 보다 ModelAndView가 더 확장성을 가지고 있음

    // Model
    // - 뷰에 표시할 데이터를 저장하는 객체
    // - 컨트롤러에서 Model 객체를 선언하고 데이터를 추가하면 뷰에서 해당 데이터에 접근하여 사용
    // - addAttribute() : Model 객체에 데이터를 추가할 때 사용
    // - 장점 : 사용하기 쉽다. 간결. 간단한 경우에 사용하기 좋음
    // - 단점 : 뷰에 표시할 데이터만 저장하고, 뷰를 직접 제어하기는 어렵다. (뷰 이름 설정을 X)

    // ModelAndView
    // - Model 객체와 View 이름을 함께 저장하는 객체
    // - 컨트롤러에서 ModelAndView 객체를 반환 => Model 객체에 데이터 저장 => 뷰에 전달 => 해당 이름의 뷰를 화면에 렌더링
    // - addObject() : Model 객체에 데이터를 추가하는데 사용
    // - setViewName() : 뷰 이름을 지정하는데 사용
    // - 장점 : Model 객체와 View 이름을 한꺼번에 설정, 뷰 이름을 직접 지정(직접 제어가 가능, 이럴때 사용)
    // - 단점 : Model 보다 코드가 복잡

    @GetMapping("/member/intro1")
    public String intro1(Model model) {
        model.addAttribute("msg", "모델을 이용해서 데이터를 추가하고 뷰로 이동합니다.");
        model.addAttribute("msg2", "두 번째 데이터를 추가합니다.");
        return "member/intro";
    }

    @GetMapping("/member/intro2")
    public ModelAndView intro2() {
        ModelAndView mav = new ModelAndView();
        mav.addObject("msg", "모델앤뷰를 이용해서 데이터를 추가하고 뷰 일므을 지정한 후 해당 뷰 페이지로 이동");
        mav.addObject("msg2", "두번째");
        mav.setViewName("member/intro");

        return mav;
    }

    // DB::Read
    @GetMapping("/member/read/{idx}")
    public ModelAndView read(@PathVariable("idx") Integer idx) {
        ModelAndView mav = new ModelAndView();
        try {
            // MemberService > read() 메서드 호출
            Member member = this.memberService.read(idx);
            //데이터 추가
            mav.addObject("member", member);
            mav.setViewName("member/read");
        } catch (NoSuchElementException ex) {
            mav.setStatus(HttpStatus.UNPROCESSABLE_ENTITY);
            mav.addObject("msg", "요청한 엔티티 객체 정보가 없습니다");
            mav.setViewName("member/error");
        }
        // Return
        return mav;
    }
}
