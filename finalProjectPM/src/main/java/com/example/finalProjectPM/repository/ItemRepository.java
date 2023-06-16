package com.example.finalProjectPM.repository;

import com.example.finalProjectPM.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

// JpaRepository 는 첫 번째에는 entity 타입 클래스를 넣어주고, 두 번째는 기본 키를 넣는다
// 기본적인 CRUD 및 페이징 처리를 위한 메소드가 정의 되어 있어 사용 가능
@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findByItemNm(String itemNm);

    // OR 조건
    List<Item> findByItemNmOrItemDetail(String itemNm, String itemDetail);

    // Lessthan : 입력 받은 가격보다 작은 상품을 반환 받음
    List<Item> findByPriceLessThan(Integer price);

    // OrderBy : OrderBy + 속성명(필드이름) + Asc or Desc
    List<Item> findByPriceLessThanOrderByPriceDesc(Integer price);

    // Between : 범위 검색
    List<Item> findByPriceBetween(Integer min, Integer max);

    // 부분 문자열 검색
    List<Item> findByItemNmContaining(String keyWord);

    // JPQL로 쿼리 작성하기
    @Query("select i from Item i where i.itemDetail like %:itemDetail% order by i.price desc")
    List<Item> findByItemDetail(@Param("itemDetail") String itemDetail);

    // NativeSQL 쿼리로 작성
    @Query(value = "select * from item i where i.item_detail like %:itemDetail% order by i.price desc",
        nativeQuery = true)
    List<Item> findByItemDetailByNative(@Param("itemDetail") String itemDetail);
}
