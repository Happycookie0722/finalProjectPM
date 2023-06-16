package com.example.finalProjectPM.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Table(name = "cart")
@Getter @Setter @ToString
@Entity
public class Cart {
    @Id
    @Column(name = "cart_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne // 회원 엔티티와 일대일 매핑
    @JoinColumn(name = "member_id") // 멤버 테이블의 member_id PK 를 가져온다
    private Member member; // member_id 컬럼을 가져오기 위해 member 테이블 객체를 생성

    @Column(name = "cart_name")
    private String cart;

}
