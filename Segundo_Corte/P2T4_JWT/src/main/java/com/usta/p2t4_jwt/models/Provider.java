package com.usta.p2t4_jwt.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "providers")
public class Provider {
    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "provider_id")
    private Long providerId;

    @Column(name = "provider_name")
    private String providerName;

    @Column(name = "provider_nit")
    private String providerNit;

    @Column(name = "provider_address")
    private String providerAddress;

    public Provider(Long providerId, String providerName, String providerNit, String providerAddress) {
        this.providerId = providerId;
        this.providerName = providerName;
        this.providerNit = providerNit;
        this.providerAddress = providerAddress;
    }

    public Provider() {
    }

    public static Long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getProviderId() {
        return providerId;
    }

    public void setProviderId(Long providerId) {
        this.providerId = providerId;
    }

    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }

    public String getProviderNit() {
        return providerNit;
    }

    public void setProviderNit(String providerNit) {
        this.providerNit = providerNit;
    }

    public String getProviderAddress() {
        return providerAddress;
    }

    public void setProviderAddress(String providerAddress) {
        this.providerAddress = providerAddress;
    }
}
