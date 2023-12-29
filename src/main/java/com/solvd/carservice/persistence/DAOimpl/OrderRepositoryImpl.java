package com.solvd.carservice.persistence.DAOimpl;

import com.solvd.carservice.domain.entity.*;
import com.solvd.carservice.domain.entity.Client;
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
    private static final String GET_ALL_QUERY =
            "SELECT o.id, o.date, cl.id, cl.name, cl.surname, cl.phone_number, cl.birthday, " +
                    "c.id, c.cost, s.id, s.name, s.price, s.hours_to_do, cars.id, cars.brand, cars.model, cars.year, " +
                    "d.id, d.name, com.id, com.name, com.address, det.id, det.name, det.price, det.in_stock, det.delivery_days " +
                    "FROM orders o " +
            "LEFT JOIN clients cl on o.client_id = cl.id " +
            "LEFT JOIN costs c on o.cost_id = c.id " +
            "LEFT JOIN services s on c.service_id = s.id " +
            "LEFT JOIN cars on s.car_id = cars.id " +
            "LEFT JOIN departments d on s.department_id = d.id " +
            "LEFT JOIN companies com on d.company_id = c.id " +
            "LEFT JOIN details det on c.detail_id = det.id;";
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
                    new Order(
                            resultSet.getLong(1),
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
            case "date" :
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
                order.setClientId(
                        new Client(
                                resultSet.getLong(3),
                                resultSet.getString(4),
                                resultSet.getString(5),
                                resultSet.getString(6),
                                resultSet.getDate(7)));
                order.setCostId(
                        new Cost(
                                resultSet.getLong(8),
                                resultSet.getDouble(9),
                                new Service(
                                        resultSet.getLong(10),
                                        resultSet.getString(11),
                                        resultSet.getDouble(12),
                                        resultSet.getInt(13),
                                        new Car(
                                                resultSet.getLong(14),
                                                resultSet.getString(15),
                                                resultSet.getString(16),
                                                resultSet.getInt(17)),
                                        new Department(
                                                resultSet.getLong(18),
                                                resultSet.getString(19),
                                                new Company(
                                                        resultSet.getLong(20),
                                                        resultSet.getString(21),
                                                        resultSet.getString(22)))),
                                new Detail(
                                        resultSet.getLong(23),
                                        resultSet.getString(24),
                                        resultSet.getInt(25),
                                        resultSet.getBoolean(26),
                                        resultSet.getInt(27))));
                orders.add(order);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to map orders", e);
        }
        return orders;
    }
}
