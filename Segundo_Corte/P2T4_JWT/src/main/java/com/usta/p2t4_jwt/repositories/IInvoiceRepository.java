package com.usta.p2t4_jwt.repositories;

import com.usta.p2t4_jwt.models.Invoice;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IInvoiceRepository extends JpaRepository<Invoice, Long> {
    
}
