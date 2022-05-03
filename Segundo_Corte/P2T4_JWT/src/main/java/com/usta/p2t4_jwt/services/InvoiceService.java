package com.usta.p2t4_jwt.services;

import java.util.List;
import java.util.Optional;

import com.usta.p2t4_jwt.models.Invoice;
import com.usta.p2t4_jwt.repositories.IInvoiceRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InvoiceService {
    @Autowired
    private IInvoiceRepository _invoiceRepository;

    public List<Invoice> getAllInvoices() {
        return _invoiceRepository.findAll();
    }

    public Optional<Invoice> getInvoiceById(Long id) {
        return _invoiceRepository.findById(id);
    }

    public Invoice createInvoice(Invoice invoice) {
        return _invoiceRepository.save(invoice);
    }

    public Invoice updateInvoice(Invoice invoice) {
        return _invoiceRepository.save(invoice);
    }

    public void deleteInvoice(Long id) {
        _invoiceRepository.deleteById(id);
    }
}
