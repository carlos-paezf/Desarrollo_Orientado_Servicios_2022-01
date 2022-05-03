package com.usta.p2t4_jwt.services;

import java.util.List;
import java.util.Optional;

import com.usta.p2t4_jwt.models.OrderDetail;
import com.usta.p2t4_jwt.repositories.IOrderDetailRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderDetailService {
    @Autowired
    private IOrderDetailRepository _orderDetailRepository;

    public List<OrderDetail> getAllOrderDetails() {
        return _orderDetailRepository.findAll();
    }

    public Optional<OrderDetail> getOrderDetailById(Long id) {
        return _orderDetailRepository.findById(id);
    }

    public OrderDetail createOrderDetail(OrderDetail orderDetail) {
        return _orderDetailRepository.save(orderDetail);
    }

    public OrderDetail updateOrderDetail(OrderDetail orderDetail) {
        return _orderDetailRepository.save(orderDetail);
    }

    public void deleteOrderDetail(Long id) {
        _orderDetailRepository.deleteById(id);
    }
}
