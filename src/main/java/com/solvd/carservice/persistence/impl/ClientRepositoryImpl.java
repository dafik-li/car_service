package com.solvd.carservice.persistence.impl;

import com.solvd.carservice.domain.Client;
import com.solvd.carservice.persistence.ClientRepository;
import com.solvd.carservice.persistence.ConnectionPool;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ClientRepositoryImpl implements ClientRepository {
    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();
    private static final String INSERT_CLIENT_QUERY = "INSERT INTO clients (name, surname, phone_number, birthday) VALUES (?, ?, ?, ?);";
    private static final String DELETE_CLIENT_QUERY = "DELETE FROM clients WHERE id = ?;";
    private static final String UPDATE_CLIENT_NAME_QUERY = "UPDATE clients SET name = ? WHERE id = ?;";
    private static final String UPDATE_CLIENT_SURNAME_QUERY = "UPDATE clients SET surname = ? WHERE id = ?;";
    private static final String UPDATE_CLIENT_PHONE_NUMBER_QUERY = "UPDATE clients SET phone_number = ? WHERE id = ?;";
    private static final String UPDATE_CLIENT_BIRTHDAY_QUERY = "UPDATE clients SET birthday = ? WHERE id = ?;";
    private static final String GET_ALL_QUERY = "SELECT * FROM clients";
    private static final String GET_BY_ID_QUERY = "SELECT * FROM clients WHERE id = ?;";
    private static final String GET_BY_CLIENT_NAME_QUERY = "SELECT * FROM clients WHERE name = ?;";
    private static final String GET_BY_CLIENT_SURNAME_QUERY = "SELECT * FROM clients WHERE surname = ?;";
    private static final String GET_BY_CLIENT_PHONE_NUMBER_QUERY = "SELECT * FROM clients WHERE phone_number = ?;";
    private static final String GET_BY_CLIENT_BIRTHDAY_QUERY = "SELECT * FROM clients WHERE birthday = ?;";

    @Override
    public List<Client> getByName(String name) {
        List<Client> clients;
        Connection connection = CONNECTION_POOL.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_CLIENT_NAME_QUERY);
            ResultSet resultSet = preparedStatement.executeQuery();
            clients = mapClients(resultSet);
            while (resultSet.next()) {
                resultSet.getString("name");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to get client name", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return clients;
    }
    @Override
    public List<Client> getBySurname(String surname) {
        List<Client> clients;
        Connection connection = CONNECTION_POOL.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_CLIENT_SURNAME_QUERY);
            ResultSet resultSet = preparedStatement.executeQuery();
            clients = mapClients(resultSet);
            while (resultSet.next()) {
                resultSet.getString("surname");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to get client surname", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return clients;
    }
    @Override
    public List<Client> getByPhoneNumber(String phoneNumber) {
        List<Client> clients;
        Connection connection = CONNECTION_POOL.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_CLIENT_PHONE_NUMBER_QUERY);
            ResultSet resultSet = preparedStatement.executeQuery();
            clients = mapClients(resultSet);
            while (resultSet.next()) {
                resultSet.getString("phone_number");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to get client phone number", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return clients;
    }
    @Override
    public List<Client> getByBirthday(Date birthday) {
        List<Client> clients;
        Connection connection = CONNECTION_POOL.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_CLIENT_BIRTHDAY_QUERY);
            ResultSet resultSet = preparedStatement.executeQuery();
            clients = mapClients(resultSet);
            while (resultSet.next()) {
                resultSet.getString("birthday");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to get client birthday", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return clients;
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
    public List<Client> getAll() {
        List<Client> clients;
        Connection connection = CONNECTION_POOL.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_QUERY);
            ResultSet resultSet = preparedStatement.executeQuery();
            clients = mapClients(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException("Unable to get all", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return clients;
    }
    @Override
    public Optional<Client> getById(Long id) {
        Optional<Client> clientOptional;
        Connection connection = CONNECTION_POOL.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_ID_QUERY);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            clientOptional = Optional.of(
                    new Client(
                            resultSet.getLong(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4),
                            resultSet.getDate(5)));
        } catch (SQLException e) {
            throw new RuntimeException("Unable to get id", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return clientOptional;
    }
    @Override
    public void update(Client client, String field) {
        Connection connection = CONNECTION_POOL.getConnection();
        String query;
        String value;
        switch (field) {
            case "name" :
                query = UPDATE_CLIENT_NAME_QUERY;
                value = client.getName();
                break;
            case "surname" :
                query = UPDATE_CLIENT_SURNAME_QUERY;
                value = client.getSurname();
                break;
            case "phone_number" :
                query = UPDATE_CLIENT_PHONE_NUMBER_QUERY;
                value = client.getPhoneNumber();
                break;
            case "birthday" :
                query = UPDATE_CLIENT_BIRTHDAY_QUERY;
                value = String.valueOf(client.getBirthday());
                break;
            default: throw new IllegalStateException("Unexpected value: " + field);
        }
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, value);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Unable to update client " + field, e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
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
    public List<Client> mapClients(ResultSet resultSet) {
        List<Client> clients = new ArrayList<>();
        try {
            while (resultSet.next()) {
                Client client = new Client();
                client.setId(resultSet.getLong(1));
                client.setName(resultSet.getString(2));
                client.setSurname(resultSet.getString(3));
                client.setPhoneNumber(resultSet.getString(4));
                client.setBirthday(resultSet.getDate(5));
                clients.add(client);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to map clients", e);
        }
        return clients;
    }
}
