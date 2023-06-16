package com.example.finalProjectPM.entity;

import com.example.finalProjectPM.repository.CartRepository;
import com.example.finalProjectPM.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest // Spring context 를 로드하여 테스트 환경 설정
// 트랜잭션 : 데이터 베이스의 논리적인 작업 단위 , 여러 개의 절차를 진행 하는 도중에 하나라도 실패하면 롤백된다.
@Transactional // test 에서는 트랜잭션이 성공하더라도 롤백된다.
@Slf4j // 로깅 데이터를 처리하기 위해서 사용
@TestPropertySource(locations = "classpath:application-test.properties")
class CartTest {
    @Autowired // 스프링 컨테이너에서 해당 빈으로 등록된 객체를 의존성 주입 받는다. 싱글톤과 같음
               // 싱글톤은 객체처럼 생성을 하지만 단 하나의 객체만 생성할 수 있고 값을 저장하지 않고,
               // static 처럼 재사용을 할 때 사용한다.
    CartRepository cartRepository;
    @Autowired
    MemberRepository memberRepository;

    @PersistenceContext // JPA의 EntityManager 를 사용하기 위해서 주입 받음
    // JPA를 통해 데이터베이스에 접근하고 관리할 수 있습니다.
    EntityManager em;

    // 회원 엔티티 생성
    public Member createMemberInfo() {
        Member member = new Member();
        member.setUserId("lovein6114");
        member.setPassword("dlehdgus");
        member.setName("곰돌이사육사");
//        member.setJoinTime(LocalDateTime.now());
        return member;
    }

    @Test
    @DisplayName("장바구니 회원 매핑 조회 테스트")
    public void findCartAndMemberTest() {
        Member member = createMemberInfo();

        // 회원 정보를 가진 객체를 하나 저장
        memberRepository.save(member);

        Cart cart = new Cart();
//        cart.setCartName("오늘의 구매 목록");
        cart.setMember(member);
        cartRepository.save(cart);

        em.flush(); // 트랜잭션이 끝날 때 데이터 베이스에 반영
        em.clear(); // 영속성 컨텍스트를 비움. 자바에서 버퍼를 비우는 것과 같음

        Cart saveCart = cartRepository.findById(cart.getId()).orElseThrow(EntityNotFoundException::new);
        System.out.println("회원 아이디 : " + member.getUserId());
        System.out.println("회원 아이디 : " + cart.getMember().getUserId());
    }


}