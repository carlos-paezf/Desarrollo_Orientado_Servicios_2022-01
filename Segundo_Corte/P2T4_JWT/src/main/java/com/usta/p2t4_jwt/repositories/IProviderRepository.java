package com.usta.p2t4_jwt.repositories;

import com.usta.p2t4_jwt.models.Provider;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IProviderRepository extends JpaRepository<Provider, Long> {
    
}
