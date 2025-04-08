 데이터베이스 연동 과정
 - 어떤 패키지를 생성하고 그 안에서 어떤 자바 클래스 파일들을 만들어야 하는지를 아는 것이 중요
 - 그런 다음 각각의 파일들이 하는 역할과 의미를 아는 것이 중요
 - 용어에 대한 개념 및 전체적인 흐름은 한 번에 이해가 안될 수 있으므로 꾸준히 보고 타이핑 레벨로 해보는게 중요

1. 프로젝트 마우스 우클릭 > Spring > Add Starters => 새로운 의존성(dependency) 추가
 - web
 - thymeleaf
 - mysql database
 - HPA

2. 패키지 생성
    com.example.crud_demo.member.controller
        MemberController.java
    com.example.crud_demo.member.dto
        MemberCreateDTO.java
        MemberReadDTO.java
    com.example.crud_demo.member.entity
        Member.java
        - DB 연동을 코드 레벨에서 처리하는게 가능 => JPA + ORM
        - Java Persistence API / Object Relational Mapping
        - 자바 애플리케이션에서 관계형 데이터베이스를 사용하는 방식을 정의한 인터페이스
        - 기존 DB 연동 방식은 모두 버린다.
        MemberRepository.java
        - JPA 방식으로 DB 연동 처리를 위한 인터펭리스(JpaRepository 상속 => extends)
        - 상속 받은 다음에 사전에 정의된 기본 메서드를 통해 DB 관련 여러 작업(쿼리)들을 수행 => Create/Read/Update/Delete
    com.example.crud_demo.member.service
        MemberService.java
        - 실제 비즈니스 로직이 실행되는 클래스

3. 설정 파일 변경
    application.properties
        여기에 데이터베이스 관련된 설정을 작성해놓으면 그대로 반영(리스타트)
    build.gradle

4. 리스트 구현 순서
    1단계
        Repository.java
        findAll() or FindByIdContains() 메서드 작성
        인자로 Pageable 넣어서 작성
        반환값은 => page<Member>
    2단계
        Controller.java
        DemoController.java
        /member/memberList -> 이 요청에 대한 처리
        MemberService > memberList() 메서드 호출
        반환값을 모델에 담아 뷰로 전달
    3단계
        Service.java
        memberList() 메서드 작성
        pageable 사용하여 페이지 설정에 필요한 여러 설정 값을 세팅 -> 몇 페이지? 몇 개?
        this.memberRepository.findAll(pageable)
    4단계
        View
        뷰페이지 만들어서 전달된 데이터 받아서 반복 출력