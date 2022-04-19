package com.usta.p2t4_jwt.repositories;

import com.usta.p2t4_jwt.models.Product;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductRepository extends JpaRepository<Product, Long> {
    
}
