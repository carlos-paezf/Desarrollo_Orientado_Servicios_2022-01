package com.usta.p2t4_jwt.rest_controllers;

import java.util.List;
import java.util.Optional;

import com.usta.p2t4_jwt.models.OrderClient;
import com.usta.p2t4_jwt.services.OrderClientService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/order-client")
public class OrderClientController {
    @Autowired
    private OrderClientService _orderClientService;
    
    @GetMapping(value = "")
    public ResponseEntity<List<OrderClient>> getAllOrderClients() {
        return new ResponseEntity<List<OrderClient>>(_orderClientService.getAllOrderClients(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Optional<OrderClient>> getOrderClientById(@PathVariable("id") Long id) {
        return new ResponseEntity<Optional<OrderClient>>(_orderClientService.getOrderClientById(id), HttpStatus.OK);
    }
}
