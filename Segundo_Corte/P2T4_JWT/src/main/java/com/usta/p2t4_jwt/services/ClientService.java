package com.usta.p2t4_jwt.services;

import java.util.List;
import java.util.Optional;

import com.usta.p2t4_jwt.models.Client;
import com.usta.p2t4_jwt.repositories.IClientRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    @Autowired
    private IClientRepository _clientRepository;

    public List<Client> getAllClients() {
        return _clientRepository.findAll();
    }

    public Optional<Client> getClientById(Long id) {
        return _clientRepository.findById(id);
    }

    public Client createClient(Client client) {
        return _clientRepository.save(client);
    }

    public Client updateClient(Client client) {
        return _clientRepository.save(client);
    }

    public void deleteClient(Long id) {
        _clientRepository.deleteById(id);
    }
}
