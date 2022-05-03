package com.usta.p2t4_jwt.models;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "invoices")
public class Invoice {
    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "invoice_id")
    private Long invoiceId;

    @JoinColumn(name = "product_id", referencedColumnName = "product_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Product productId;

    @Column(name = "invoice_total")
    private Integer invoiceTotal;

    @Column(name = "invoice_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date invoiceDate;

    public Invoice(Long invoiceId, Product productId, Integer invoiceTotal, Date invoiceDate) {
        this.invoiceId = invoiceId;
        this.productId = productId;
        this.invoiceTotal = invoiceTotal;
        this.invoiceDate = invoiceDate;
    }

    public Invoice() {
    }

    public static Long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(Long invoiceId) {
        this.invoiceId = invoiceId;
    }

    public Product getProductId() {
        return productId;
    }

    public void setProductId(Product productId) {
        this.productId = productId;
    }

    public Integer getInvoiceTotal() {
        return invoiceTotal;
    }

    public void setInvoiceTotal(Integer invoiceTotal) {
        this.invoiceTotal = invoiceTotal;
    }

    public Date getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }
}
