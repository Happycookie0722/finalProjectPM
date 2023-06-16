package com.example.finalProjectPM.entity;

import com.example.finalProjectPM.constant.ItemSellStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity // JPA에 Entity 클래스라는걸 지정한다.
// Entity 클래스는 반드시 기본키를 지정하는 어노테이션을 지정해야함
// 기본 키는 Long 타입이나 int 타입으로 사용할 것을 권장한다.
// Entity 어노테이션은 DB 테이블로 생성되어야 할 클래스라고 명시하는것
@Table(name="item")
@Getter @Setter @ToString
public class Item {
    @Id // 해당 필드가 기본 키임을 지정하는 어노테이션
    @Column(name = "item_id") // 실제로 DB에 테이블 생성할 때 컬럼 이름을 item_id 로 지정하는 것
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id; // Primary Key

    @Column(nullable = false, length = 50) // notNull, 길이 제한
    private String itemNm; // 상품명

    @Column(nullable = false)
    private int price; // 가격

    @Column(nullable = false)
    private int stockNumber; // 재고수량

    @Lob // 상세 설명 같이 길이제한이 255자 이상인 긴 문자열을 저장해야 할 때 사용
    @Column(nullable = false)
    private String itemDetail; // 상품 상세설명

    @Enumerated(EnumType.STRING) // String 은 enum 에 넣은 이름을 넣는다는 의미, ORDINAL 은 순서적인 숫자값을 넣는다는 의미
    private ItemSellStatus itemSellStatus; // 상품 판매 상태
    private LocalDateTime regTime; // 등록 시간
    private LocalDateTime updateTime; // 수정 시간
}
