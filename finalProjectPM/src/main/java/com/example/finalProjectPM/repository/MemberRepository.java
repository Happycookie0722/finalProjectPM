package com.example.finalProjectPM.repository;

import com.example.finalProjectPM.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    // NullPointException 을 방지하기 위해 Optional 사용
//    값이 있을 수도 있고 없을 수도 있는 상황을 처리하기 위해 사용
    Optional<Member> findByEmail(String email);

    // findByEmail 로 찾은 email 이 맞는지 확인
    boolean existsByEmail(String email);
}
