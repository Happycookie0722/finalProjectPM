package com.example.finalProjectPM.repository;


import com.example.finalProjectPM.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
