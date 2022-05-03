package com.usta.p2t4_jwt.rest_controllers;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import com.usta.p2t4_jwt.models.Client;
import com.usta.p2t4_jwt.services.ClientService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/clients")
public class ClientController {
    private ClientService _clientService;

    @GetMapping(value = "")
    public ResponseEntity<List<Client>> getAllClients() {
        return ResponseEntity.ok(_clientService.getAllClients());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Optional<Client>> getClientById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(_clientService.getClientById(id));
    }

    @PostMapping(value = "/create")
    public ResponseEntity<Client> createClient(Client client) {
        Client temp = _clientService.createClient(client);
        try {
            return ResponseEntity.created(new URI("api/clients/" + temp.getClientId())).body(temp);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping(value = "/update")
    public ResponseEntity<Client> updateClient(Client client) {
        Client temp = _clientService.updateClient(client);
        try {
            return ResponseEntity.created(new URI("api/clients/" + temp.getClientId())).body(temp);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Client> deleteClient(@PathVariable("id") Long id) {
        _clientService.deleteClient(id);
        return ResponseEntity.ok().build();
    }
}
