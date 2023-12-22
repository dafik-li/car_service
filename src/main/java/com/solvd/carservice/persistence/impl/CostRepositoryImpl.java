package com.solvd.carservice.persistence.impl;

import com.solvd.carservice.domain.Cost;
import com.solvd.carservice.persistence.ConnectionPool;
import com.solvd.carservice.persistence.CostRepository;

import java.sql.*;
import java.util.List;
import java.util.Optional;

public class CostRepositoryImpl implements CostRepository {
    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();
    private static final String INSERT_COST_QUERY = "INSERT INTO costs (brand, model, year) values (?, ?, ?);";
    private static final String DELETE_COST_QUERY = "DELETE FROM costs where id = ?;";
    private static final String FIND_ALL_QUERY = "SELECT * FROM costs where cost = ?;";

    @Override
    public List<Cost> findByCost(Double cost) {
        return null;
    }

    @Override
    public void create(Cost cost) {
        Connection connection = CONNECTION_POOL.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_COST_QUERY, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, cost.getCost());
            preparedStatement.setString(2, cost.getSurname());
            preparedStatement.setString(3, cost.getPhoneNumber());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                cost.setId(resultSet.getLong(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to create client", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public List<Cost> findAll() {
        return null;
    }

    @Override
    public Optional<Cost> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public void update(Cost Entity) {

    }

    @Override
    public void deleteById(Long id) {

    }
}
