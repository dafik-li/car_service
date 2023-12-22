package com.solvd.carservice.persistence.impl;

import com.solvd.carservice.domain.Client;
import com.solvd.carservice.persistence.ClientRepository;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

public class ClientRepositoryImpl implements ClientRepository {
    @Override
    public List<Client> findByName(String name) {
        return null;
    }

    @Override
    public List<Client> findBySurname(String surname) {
        return null;
    }

    @Override
    public List<Client> findByPhoneNumber(String phoneNumber) {
        return null;
    }

    @Override
    public List<Client> findByBirthday(Date birthday) {
        return null;
    }

    @Override
    public void create(Client Entity) {

    }

    @Override
    public List<Client> findAll() {
        return null;
    }

    @Override
    public Optional<Client> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public void update(Client Entity) {

    }

    @Override
    public void deleteById(Long id) {

    }
}
