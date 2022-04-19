package com.usta.p2t4_jwt.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "order_client")
public class OrderClient {
    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_client_id")
    private Long orderClientId;

    @JoinColumn(name = "order_id", referencedColumnName = "order_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Order orderId;

    @JoinColumn(name = "client_id", referencedColumnName = "client_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Client clientId;

    @Column(name = "order_client_description")
    private String orderClientDescription;

    public OrderClient(Long orderClientId, Order orderId, Client clientId, String orderClientDescription) {
        this.orderClientId = orderClientId;
        this.orderId = orderId;
        this.clientId = clientId;
        this.orderClientDescription = orderClientDescription;
    }

    public OrderClient() {
    }

    public static Long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getOrderClientId() {
        return orderClientId;
    }

    public void setOrderClientId(Long orderClientId) {
        this.orderClientId = orderClientId;
    }

    public Order getOrderId() {
        return orderId;
    }

    public void setOrderId(Order orderId) {
        this.orderId = orderId;
    }

    public Client getClientId() {
        return clientId;
    }

    public void setClientId(Client clientId) {
        this.clientId = clientId;
    }

    public String getOrderClientDescription() {
        return orderClientDescription;
    }

    public void setOrderClientDescription(String orderClientDescription) {
        this.orderClientDescription = orderClientDescription;
    }
}
