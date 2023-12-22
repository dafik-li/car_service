package com.solvd.carservice.persistence.impl;

import com.solvd.carservice.domain.Client;
import com.solvd.carservice.persistence.ClientRepository;
import com.solvd.carservice.persistence.ConnectionPool;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

public class ClientRepositoryImpl implements ClientRepository {
    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();
    private static final String INSERT_CLIENT_QUERY = "INSERT INTO cars(brand, model, year) values (?, ?, ?);";
    private static final String DELETE_CLIENT_QUERY = "DELETE FROM cars where id = ?;";
    private static final String FIND_ALL_QUERY = "SELECT * FROM cars where name = ?;";
    private static final String FIND_BY_BRAND_QUERY = "SELECT * FROM cars where brand = ?;";
    private static final String FIND_BY_MODEL_QUERY = "SELECT * FROM cars where model = ?;";
    private static final String FIND_BY_YEAR_QUERY = "SELECT * FROM cars where year = ?;";
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
