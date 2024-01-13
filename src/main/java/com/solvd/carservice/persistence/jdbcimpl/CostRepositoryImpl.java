package com.solvd.carservice.persistence.jdbcimpl;

import com.solvd.carservice.domain.entity.*;
import com.solvd.carservice.persistence.ConnectionPool;
import com.solvd.carservice.persistence.CostRepository;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CostRepositoryImpl implements CostRepository {
    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();
    private static final String INSERT_COST_QUERY = "INSERT INTO costs (cost, service_id, detail_id) VALUES (?, ?, ?);";
    private static final String DELETE_COST_QUERY = "DELETE FROM costs WHERE id = ?;";
    private static final String UPDATE_COST_QUERY = "UPDATE costs SET cost = ? WHERE id = ?;";
    private static final String GET_ALL_QUERY =
            "SELECT c.id, c.cost, s.id, s.name, s.price, s.hours_to_do, cars.id, cars.brand, cars.model, cars.year, " +
                    "d.id, d.name, com.id, com.name, com.address, det.id, det.name, det.price, det.in_stock, det.delivery_days " +
            "FROM costs c " +
            "LEFT JOIN services s ON c.service_id = s.id " +
            "LEFT JOIN cars ON s.car_id = cars.id " +
            "LEFT JOIN departments d ON s.department_id = d.id " +
            "LEFT JOIN companies com ON d.company_id = c.id " +
            "LEFT JOIN details det ON c.detail_id = det.id ";
    private static final String GET_BY_ID_QUERY = GET_ALL_QUERY.concat("WHERE c.id = ? ");
    private static final String GET_BY_COST_COST_QUERY = GET_ALL_QUERY.concat("WHERE cost = ? ");

    @Override
    public List<Cost> getByCost(Double cost) {
        List<Cost> costs;
        Connection connection = CONNECTION_POOL.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_COST_COST_QUERY);
            ResultSet resultSet = preparedStatement.executeQuery();
            costs = mapCosts(resultSet);
            while (resultSet.next()) {
                resultSet.getString("cost");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to get cost cost", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return costs;
    }
    @Override
    public void create(Cost cost) {
        Connection connection = CONNECTION_POOL.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_COST_QUERY, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setDouble(1, cost.getCost());
            preparedStatement.setLong(2,cost.getServiceId().getId());
            preparedStatement.setLong(3,cost.getDetailId().getId());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                cost.setId(resultSet.getLong(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to create cost", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }
    @Override
    public List<Cost> getAll() {
        List<Cost> costs;
        Connection connection = CONNECTION_POOL.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_QUERY);
            ResultSet resultSet = preparedStatement.executeQuery();
            costs = mapCosts(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException("Unable to get all", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return costs;
    }
    @Override
    public Optional<Cost> getById(Long id) {
        Optional<Cost> costOptional;
        Connection connection = CONNECTION_POOL.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_ID_QUERY);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            costOptional = Optional.of(
                    new Cost(
                            resultSet.getLong(1),
                            resultSet.getDouble(2),
                            new Service(
                                    resultSet.getLong(3),
                                    resultSet.getString(4),
                                    resultSet.getDouble(5),
                                    resultSet.getInt(6),
                                    new Car(
                                            resultSet.getLong(7),
                                            resultSet.getString(8),
                                            resultSet.getString(9),
                                            resultSet.getInt(10)),
                                    new Department(
                                            resultSet.getLong(11),
                                            resultSet.getString(12),
                                            new Company(
                                                    resultSet.getLong(13),
                                                    resultSet.getString(14),
                                                    resultSet.getString(15)))),
                            new Detail(
                                    resultSet.getLong(16),
                                    resultSet.getString(17),
                                    resultSet.getInt(18),
                                    new Car(
                                            resultSet.getLong(7),
                                            resultSet.getString(8),
                                            resultSet.getString(9),
                                            resultSet.getInt(10)),
                                    resultSet.getBoolean(19),
                                    resultSet.getInt(20))));
        } catch (SQLException e) {
            throw new RuntimeException("Unable to get id", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return costOptional;
    }
    @Override
    public void update(Optional<Cost> cost, String field) {
        Connection connection = CONNECTION_POOL.getConnection();
        String query;
        String value;
        switch (field) {
            case "cost" :
                query = UPDATE_COST_QUERY;
                value = String.valueOf(cost.get().getCost());
                break;
            default: throw new IllegalStateException("Unexpected value: " + field);
        }
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, value);
            preparedStatement.setLong(2, cost.get().getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Unable to update cost " + field, e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }
    @Override
    public void deleteById(Long id) {
        Connection connection = CONNECTION_POOL.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_COST_QUERY);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Unable to delete cost", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }
    public List<Cost> mapCosts(ResultSet resultSet) {
        List<Cost> costs = new ArrayList<>();
        try {
            while (resultSet.next()) {
                Cost cost = new Cost();
                cost.setId(resultSet.getLong(1));
                cost.setCost(resultSet.getDouble(2));
                cost.setServiceId(
                        new Service(
                                resultSet.getLong(3),
                                resultSet.getString(4),
                                resultSet.getDouble(5),
                                resultSet.getInt(6),
                                new Car(
                                        resultSet.getLong(7),
                                        resultSet.getString(8),
                                        resultSet.getString(9),
                                        resultSet.getInt(10)),
                                new Department(
                                        resultSet.getLong(11),
                                        resultSet.getString(12),
                                        new Company(
                                                resultSet.getLong(13),
                                                resultSet.getString(14),
                                                resultSet.getString(15)))));
                cost.setDetailId(
                        new Detail(
                        resultSet.getLong(16),
                        resultSet.getString(17),
                        resultSet.getInt(18),
                                new Car(
                                        resultSet.getLong(7),
                                        resultSet.getString(8),
                                        resultSet.getString(9),
                                        resultSet.getInt(10)),
                        resultSet.getBoolean(19),
                        resultSet.getInt(20)));
                costs.add(cost);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to map costs", e);
        }
        return costs;
    }
}
