package com.usta.p2t4_jwt.services;

import java.util.List;
import java.util.Optional;

import com.usta.p2t4_jwt.models.OrderClient;
import com.usta.p2t4_jwt.repositories.IOrderClientRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderClientService {
    @Autowired
    private IOrderClientRepository _orderClientRepository;

    public List<OrderClient> getAllOrderClients() {
        return _orderClientRepository.findAll();
    }

    public Optional<OrderClient> getOrderClientById(Long id) {
        return _orderClientRepository.findById(id);
    }

    public OrderClient createOrderClient(OrderClient orderClient) {
        return _orderClientRepository.save(orderClient);
    }

    public OrderClient updateOrderClient(OrderClient orderClient) {
        return _orderClientRepository.save(orderClient);
    }

    public void deleteOrderClient(Long id) {
        _orderClientRepository.deleteById(id);
    }
}
