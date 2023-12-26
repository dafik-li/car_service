package com.solvd.carservice.persistence.impl;

import com.solvd.carservice.domain.*;
import com.solvd.carservice.persistence.ConnectionPool;
import com.solvd.carservice.persistence.DetailRepository;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DetailRepositoryImpl implements DetailRepository {
    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();
    private static final String INSERT_DETAIL_QUERY = "INSERT INTO details (name, car_id, in_Stock, delivery_days) VALUES (?, ?, ?, ?);";
    private static final String DELETE_DETAIL_QUERY = "DELETE FROM details WHERE id = ?;";
    private static final String UPDATE_DETAIL_NAME_QUERY = "UPDATE details SET name = ? WHERE id = ?;";
    private static final String UPDATE_DETAIL_IN_STOCK_QUERY = "UPDATE details SET in_Stock = ? WHERE id = ?;";
    private static final String UPDATE_DETAIL_DELIVERY_DAYS_QUERY = "UPDATE details SET delivery_days = ? WHERE id = ?;";
    private static final String GET_ALL_QUERY = "SELECT * FROM details;";
    private static final String GET_BY_ID_QUERY = "SELECT * FROM details WHERE id = ?;";
    private static final String GET_BY_DETAIL_NAME_QUERY = "SELECT * FROM details WHERE name = ?;";
    private static final String GET_BY_DETAIL_IN_STOCK_QUERY = "SELECT * FROM details WHERE in_Stock = ?;";
    private static final String GET_BY_DETAIL_DELIVERY_DAYS_QUERY = "SELECT * FROM details WHERE delivery_days = ?;";

    @Override
    public List<Detail> getByName(String name) {
        List<Detail> details;
        Connection connection = CONNECTION_POOL.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_DETAIL_NAME_QUERY);
            ResultSet resultSet = preparedStatement.executeQuery();
            details = mapDetails(resultSet);
            while (resultSet.next()) {
                resultSet.getString("name");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to get detail name", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return details;
    }
    @Override
    public List<Detail> getByInStock(Boolean inStock) {
        List<Detail> details;
        Connection connection = CONNECTION_POOL.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_DETAIL_IN_STOCK_QUERY);
            ResultSet resultSet = preparedStatement.executeQuery();
            details = mapDetails(resultSet);
            while (resultSet.next()) {
                resultSet.getString("in_stock");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to get detail in stock", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return details;
    }
    @Override
    public List<Detail> getByDeliveryDays(Integer deliveryDays) {
        List<Detail> details;
        Connection connection = CONNECTION_POOL.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_DETAIL_DELIVERY_DAYS_QUERY);
            ResultSet resultSet = preparedStatement.executeQuery();
            details = mapDetails(resultSet);
            while (resultSet.next()) {
                resultSet.getString("delivery_days");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to get detail delivery days", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return details;
    }
    @Override
    public void create(Detail detail) {
        Connection connection = CONNECTION_POOL.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_DETAIL_QUERY, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, detail.getName());
            preparedStatement.setLong(2, detail.getCarId().getId());
            preparedStatement.setBoolean(3, detail.getInStock());
            preparedStatement.setInt(4, detail.getDeliveryDays());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                detail.setId(resultSet.getLong(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to create detail", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }
    @Override
    public List<Detail> getAll() {
        List<Detail> details;
        Connection connection = CONNECTION_POOL.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_QUERY);
            ResultSet resultSet = preparedStatement.executeQuery();
            details = mapDetails(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException("Unable to get all", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return details;
    }
    @Override
    public Optional<Detail> getById(Long id) {
        Optional<Detail> detailOptional;
        Connection connection = CONNECTION_POOL.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_ID_QUERY);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            detailOptional = Optional.of(
                    new Detail(resultSet.getLong(1),
                            resultSet.getString(2),
                            new Car(resultSet.getLong(3)),
                            resultSet.getBoolean(4),
                            resultSet.getInt(5)));
        } catch (SQLException e) {
            throw new RuntimeException("Unable to get id", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return detailOptional;
    }
    @Override
    public void update(Detail detail, String field) {
        Connection connection = CONNECTION_POOL.getConnection();
        String query;
        String value;
        switch (field) {
            case "name" :
                query = UPDATE_DETAIL_NAME_QUERY;
                value = detail.getName();
                break;
            case "in_stock" :
                query = UPDATE_DETAIL_IN_STOCK_QUERY;
                value = String.valueOf(detail.getInStock());
                break;
            case "delivery_days" :
                query = UPDATE_DETAIL_DELIVERY_DAYS_QUERY;
                value = String.valueOf(detail.getDeliveryDays());
                break;
            default: throw new IllegalStateException("Unexpected value: " + field);
        }
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, value);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Unable to update detail " + field, e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }
    @Override
    public void deleteById(Long id) {
        Connection connection = CONNECTION_POOL.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_DETAIL_QUERY);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Unable to delete detail", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }
    public List<Detail> mapDetails(ResultSet resultSet) {
        List<Detail> details = new ArrayList<>();
        try {
            while (resultSet.next()) {
                Detail detail = new Detail();
                detail.setId(resultSet.getLong(1));
                detail.setName(resultSet.getString(2));
                detail.setCarId(new Car(resultSet.getLong(3)));
                detail.setInStock(resultSet.getBoolean(4));
                detail.setDeliveryDays(resultSet.getInt(5));
                details.add(detail);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to map details", e);
        }
        return details;
    }
}
