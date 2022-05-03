package com.usta.p2t4_jwt.services;

import java.util.List;
import java.util.Optional;

import com.usta.p2t4_jwt.models.Order;
import com.usta.p2t4_jwt.repositories.IOrderRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    @Autowired
    private IOrderRepository _orderRepository;

    public List<Order> getAllOrders() {
        return _orderRepository.findAll();
    }

    public Optional<Order> getOrderById(Long id) {
        return _orderRepository.findById(id);
    }

    public Order createOrder(Order order) {
        return _orderRepository.save(order);
    }

    public Order updateOrder(Order order) {
        return _orderRepository.save(order);
    }

    public void deleteOrder(Long id) {
        _orderRepository.deleteById(id);
    }
}
