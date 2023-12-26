package com.solvd.carservice.persistence.impl;

import com.solvd.carservice.domain.*;
import com.solvd.carservice.persistence.ConnectionPool;
import com.solvd.carservice.persistence.OrderRepository;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderRepositoryImpl implements OrderRepository{
    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();
    private static final String INSERT_ORDER_QUERY = "INSERT INTO orders (date, client_id, cost_id) VALUES (?, ?, ?);";
    private static final String DELETE_ORDER_QUERY = "DELETE FROM orders WHERE id = ?;";
    private static final String UPDATE_ORDER_QUERY = "UPDATE orders SET date = ? WHERE id = ?;";
    private static final String GET_ALL_QUERY = "SELECT * FROM orders;";
    private static final String GET_BY_ID_QUERY = "SELECT * FROM orders WHERE id = ?;";
    private static final String GET_BY_ORDER_DATE_QUERY = "SELECT * FROM orders WHERE date = ?;";

    @Override
    public List<Order> getByDate(Date date) {
        List<Order> orders;
        Connection connection = CONNECTION_POOL.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_ORDER_DATE_QUERY);
            ResultSet resultSet = preparedStatement.executeQuery();
            orders = mapOrders(resultSet);
            while (resultSet.next()) {
                resultSet.getString("date");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to get order date", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return orders;
    }
    @Override
    public void create(Order order) {
        Connection connection = CONNECTION_POOL.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ORDER_QUERY, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setDate(1, order.getDate());
            preparedStatement.setLong(2, order.getClientId().getId());
            preparedStatement.setLong(3,order.getCostId().getId());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                order.setId(resultSet.getLong(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to create order", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }
    @Override
    public List<Order> getAll() {
        List<Order> orders;
        Connection connection = CONNECTION_POOL.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_QUERY);
            ResultSet resultSet = preparedStatement.executeQuery();
            orders = mapOrders(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException("Unable to get all", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return orders;
    }
    @Override
    public Optional<Order> getById(Long id) {
        Optional<Order> orderOptional;
        Connection connection = CONNECTION_POOL.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_ID_QUERY);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            orderOptional = Optional.of(
                    new Order(resultSet.getLong(1),
                            resultSet.getDate(2),
                            new Client(resultSet.getLong(3)),
                            new Cost(resultSet.getLong(4))));
        } catch (SQLException e) {
            throw new RuntimeException("Unable to get id", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return orderOptional;
    }
    @Override
    public void update(Order order, String field) {
        Connection connection = CONNECTION_POOL.getConnection();
        String query;
        String value;
        switch (field) {
            case "order" :
                query = UPDATE_ORDER_QUERY;
                value = String.valueOf(order.getDate());
                break;
            default: throw new IllegalStateException("Unexpected value: " + field);
        }
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, value);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Unable to update order " + field, e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }
    @Override
    public void deleteById(Long id) {
        Connection connection = CONNECTION_POOL.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ORDER_QUERY);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Unable to delete order", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }
    public List<Order> mapOrders(ResultSet resultSet) {
        List<Order> orders = new ArrayList<>();
        try {
            while (resultSet.next()) {
                Order order = new Order();
                order.setId(resultSet.getLong(1));
                order.setDate(resultSet.getDate(2));
                order.setClientId(new Client(resultSet.getLong(3)));
                order.setCostId(new Cost(resultSet.getLong(4)));
                orders.add(order);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to map orders", e);
        }
        return orders;
    }
}
