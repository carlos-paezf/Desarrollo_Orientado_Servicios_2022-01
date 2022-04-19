package com.usta.p2t4_jwt.repositories;

import com.usta.p2t4_jwt.models.Client;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IClientRepository extends JpaRepository<Client, Long> {
    
}
