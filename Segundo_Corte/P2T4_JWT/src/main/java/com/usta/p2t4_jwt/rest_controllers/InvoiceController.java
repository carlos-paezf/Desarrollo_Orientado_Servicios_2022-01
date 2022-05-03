package com.usta.p2t4_jwt.rest_controllers;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import com.usta.p2t4_jwt.models.Invoice;
import com.usta.p2t4_jwt.services.InvoiceService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/invoices")
public class InvoiceController {
    private InvoiceService _invoiceService;

    public InvoiceController(InvoiceService invoiceService) {
        _invoiceService = invoiceService;
    }

    @GetMapping(value = "")
    public ResponseEntity<List<Invoice>> getAllInvoices() {
        return ResponseEntity.ok(_invoiceService.getAllInvoices());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Optional<Invoice>> getInvoiceById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(_invoiceService.getInvoiceById(id));
    }

    @PostMapping(value = "/create")
    public ResponseEntity<Invoice> createInvoice(Invoice invoice) {
        Invoice temp = _invoiceService.createInvoice(invoice);
        try {
            return ResponseEntity.created(new URI("api/invoices/" + temp.getInvoiceId())).body(temp);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping(value = "/update")
    public ResponseEntity<Invoice> updateInvoice(Invoice invoice) {
        Invoice temp = _invoiceService.updateInvoice(invoice);
        try {
            return ResponseEntity.created(new URI("api/invoices/" + temp.getInvoiceId())).body(temp);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Invoice> deleteInvoice(@PathVariable("id") Long id) {
        _invoiceService.deleteInvoice(id);
        return ResponseEntity.ok().build();
    }
}
