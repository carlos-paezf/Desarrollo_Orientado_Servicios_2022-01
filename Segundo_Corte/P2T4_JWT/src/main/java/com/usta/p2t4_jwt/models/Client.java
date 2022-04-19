package com.usta.p2t4_jwt.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "clients")
public class Client {
    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "client_id")
    private Long clientId;

    @Column(name = "client_name")
    private String clientName;

    @Column(name = "client_last_name")
    private String clientLastName;

    @Column(name = "client_document")
    private String clientDocument;

    @Column(name = "client_phone")
    private Integer clientPhone;

    public Client(Long clientId, String clientName, String clientLastName, String clientDocument, Integer clientPhone) {
        this.clientId = clientId;
        this.clientName = clientName;
        this.clientLastName = clientLastName;
        this.clientDocument = clientDocument;
        this.clientPhone = clientPhone;
    }

    public Client() {
    }

    public static Long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientLastName() {
        return clientLastName;
    }

    public void setClientLastName(String clientLastName) {
        this.clientLastName = clientLastName;
    }

    public String getClientDocument() {
        return clientDocument;
    }

    public void setClientDocument(String clientDocument) {
        this.clientDocument = clientDocument;
    }

    public Integer getClientPhone() {
        return clientPhone;
    }

    public void setClientPhone(Integer clientPhone) {
        this.clientPhone = clientPhone;
    }
}
