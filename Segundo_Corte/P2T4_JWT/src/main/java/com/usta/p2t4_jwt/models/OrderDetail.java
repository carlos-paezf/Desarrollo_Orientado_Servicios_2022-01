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
@Table(name = "order_detail")
public class OrderDetail {
    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_detail_id")
    private Long orderDetailId;

    @JoinColumn(name = "order_id", referencedColumnName = "order_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Order orderId;

    @Column(name = "order_detail_address")
    private String orderDetailAddress;

    @Column(name = "order_detail_total")
    private Float orderDetailTotal;

    public OrderDetail(Long orderDetailId, Order orderId, String orderDetailAddress, Float orderDetailTotal) {
        this.orderDetailId = orderDetailId;
        this.orderId = orderId;
        this.orderDetailAddress = orderDetailAddress;
        this.orderDetailTotal = orderDetailTotal;
    }

    public OrderDetail() {
    }

    public static Long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getOrderDetailId() {
        return orderDetailId;
    }

    public void setOrderDetailId(Long orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    public Order getOrderId() {
        return orderId;
    }

    public void setOrderId(Order orderId) {
        this.orderId = orderId;
    }

    public String getOrderDetailAddress() {
        return orderDetailAddress;
    }

    public void setOrderDetailAddress(String orderDetailAddress) {
        this.orderDetailAddress = orderDetailAddress;
    }

    public Float getOrderDetailTotal() {
        return orderDetailTotal;
    }

    public void setOrderDetailTotal(Float orderDetailTotal) {
        this.orderDetailTotal = orderDetailTotal;
    }
}
