package com.solvd.carservice.persistence.impl;

import com.solvd.carservice.domain.Client;
import com.solvd.carservice.persistence.ClientRepository;
import com.solvd.carservice.persistence.ConnectionPool;

import java.sql.*;
import java.util.List;
import java.util.Optional;

public class ClientRepositoryImpl implements ClientRepository {
    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();
    private static final String INSERT_CLIENT_QUERY = "INSERT INTO clients (brand, model, year) values (?, ?, ?);";
    private static final String DELETE_CLIENT_QUERY = "DELETE FROM clients where id = ?;";
    private static final String FIND_ALL_QUERY = "SELECT * FROM clients where name = ?;";
    private static final String FIND_BY_NAME_QUERY = "SELECT * FROM clients where name = ?;";
    private static final String FIND_BY_SURNAME_QUERY = "SELECT * FROM clients where surname = ?;";
    private static final String FIND_BY_PHONE_NUMBER_QUERY = "SELECT * FROM clients where phone_number = ?;";
    private static final String FIND_BY_BIRTHDAY_QUERY = "SELECT * FROM clients where birthday = ?;";
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
    public void create(Client client) {
        Connection connection = CONNECTION_POOL.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CLIENT_QUERY, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, client.getName());
            preparedStatement.setString(2, client.getSurname());
            preparedStatement.setString(3, client.getPhoneNumber());
            preparedStatement.setDate(4, client.getBirthday());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                client.setId(resultSet.getLong(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to create client", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
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
        Connection connection = CONNECTION_POOL.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_CLIENT_QUERY);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Unable to delete client", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }
}
