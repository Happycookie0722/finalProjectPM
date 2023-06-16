package com.example.finalProjectPM.entity;


import com.example.finalProjectPM.constant.ItemSellStatus;
import com.example.finalProjectPM.repository.ItemRepository;
import com.example.finalProjectPM.repository.MemberRepository;
import com.example.finalProjectPM.repository.OrderRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;

@SpringBootTest
@Transactional

class OrderTest {
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    ItemRepository itemRepository;
    @Autowired
    MemberRepository memberRepository;
    @PersistenceContext
    EntityManager em;

    public Item createItem() {
        Item item = new Item();
        item.setItemNm("테스트 상품");
        item.setPrice(10000);
        item.setItemDetail("상세설명");
        item.setItemSellStatus(ItemSellStatus.SELL);
        item.setStockNumber(100);
        return item;
    }
    @Test
    @DisplayName("영속성 전이 테스트")
    public void cascadeTest() {
        Order order = new Order();
        for(int i = 0; i < 3; i++) {
            Item item = this.createItem();
            itemRepository.save(item);
            OrderItem orderItem = new OrderItem();
            orderItem.setItem(item);
            orderItem.setCount(10);
            orderItem.setOrderPrice(10000);
            orderItem.setOrder(order);
            // 아직 영속성 컨텍스트에 저장되지 않았음 order Entity 의 리스트에만 추가되어 있음
            order.getOrderItemList().add(orderItem);
        }
        // order 엔티티를 저장하면서 강제로 flush 호출하여 영속성 콘텍스트에 반영
        orderRepository.saveAndFlush(order);
        em.flush(); // 영속성 상태를 초기화
        Order saveOrder = orderRepository.findById(order.getId()).orElseThrow(EntityNotFoundException::new);
        // assertEuqals(3, saveOrder.getOrderItemList().size());
    }
    public Order createOrder() {
        Order order = new Order();

        for(int i = 0; i < 3; i++) {
            Item item = createItem();
            itemRepository.save(item);
            OrderItem orderItem = new OrderItem();
            orderItem.setItem(item);
            orderItem.setCount(10);
            orderItem.setOrderPrice(10000);
            orderItem.setOrder(order);
            order.getOrderItemList().add(orderItem);
        }
        Member member = new Member();
        member.setName("정민");
        member.setEmail("qhwkal1@naver.com");
        memberRepository.save(member);

        order.setMember(member);
        orderRepository.save(order);
        return order;
    }
    @Test
    @DisplayName("고아객체 제거 테스트")
    public void orphanRemovalTest() {
        Order order = createOrder();
        order.getOrderItemList().remove(0);
        em.flush();
    }

}