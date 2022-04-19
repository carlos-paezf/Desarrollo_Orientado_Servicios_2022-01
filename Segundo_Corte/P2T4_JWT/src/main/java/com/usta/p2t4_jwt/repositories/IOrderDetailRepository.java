package com.usta.p2t4_jwt.repositories;

import com.usta.p2t4_jwt.models.OrderDetail;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IOrderDetailRepository extends JpaRepository<OrderDetail, Long> {
    
}
