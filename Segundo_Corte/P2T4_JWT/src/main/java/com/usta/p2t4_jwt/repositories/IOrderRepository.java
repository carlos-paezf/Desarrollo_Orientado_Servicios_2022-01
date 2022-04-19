package com.usta.p2t4_jwt.repositories;

import com.usta.p2t4_jwt.models.Order;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IOrderRepository extends JpaRepository<Order, Long> {
    
}
