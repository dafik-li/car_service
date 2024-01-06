package com.solvd.carservice.service.impl;

import com.solvd.carservice.domain.entity.Client;
import com.solvd.carservice.persistence.ClientRepository;
import com.solvd.carservice.service.ClientService;
import com.solvd.carservice.domain.controller.SwitcherRepository;
import java.util.List;
import java.util.Optional;

public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;
    private static final SwitcherRepository switcherRepository = SwitcherRepository.getInstance();

    public ClientServiceImpl() {
        this.clientRepository = switcherRepository.switchRepository(ClientRepository.class);
    }
    @Override
    public Client add(Client client) {
        client.setId(null);
        clientRepository.create(client);
        return client;
    }
    @Override
    public List<Client> retrieveAll() {
        return clientRepository.getAll();
    }
    @Override
    public Optional<Client> retrieveById(Long id) {
        return clientRepository.getById(id);
    }
    @Override
    public void change(Optional<Client> client, String field) {
        clientRepository.update(client, field);
    }
    @Override
    public void removeById(Long id) {
        clientRepository.deleteById(id);
    }
}
