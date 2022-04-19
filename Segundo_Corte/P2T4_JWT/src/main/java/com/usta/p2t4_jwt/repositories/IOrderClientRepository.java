package com.usta.p2t4_jwt.repositories;

import com.usta.p2t4_jwt.models.OrderClient;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IOrderClientRepository extends JpaRepository<OrderClient, Long> {
    
}
