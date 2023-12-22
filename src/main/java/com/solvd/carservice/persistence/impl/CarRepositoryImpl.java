package com.solvd.carservice.persistence.impl;

import com.solvd.carservice.domain.Car;
import com.solvd.carservice.persistence.CarRepository;
import com.solvd.carservice.persistence.ConnectionPool;
import java.sql.*;
import java.util.List;
import java.util.Optional;

public class CarRepositoryImpl implements CarRepository {
    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance(10);
    private static final String INSERT_CAR_QUERY = "INSERT INTO cars(brand, model, year) values (?, ?, ?);";
    private static final String DELETE_CAR_QUERY = "DELETE FROM cars where id = ?;";
    private static final String FIND_ALL_QUERY = "SELECT * FROM cars where name = ?;";
    private static final String FIND_BY_BRAND_QUERY = "SELECT * FROM cars where brand = ?;";
    private static final String FIND_BY_MODEL_QUERY = "SELECT * FROM cars where model = ?;";
    private static final String FIND_BY_YEAR_QUERY = "SELECT * FROM cars where year = ?;";

    @Override
    public List<Car> getByBrand(String brand) {
        return null;
    }

    @Override
    public List<Car> getByModel(String model) {
        return null;
    }

    @Override
    public List<Car> getByYear(Integer year) {
        return null;
    }

    @Override
    public void create(Car car) {
        Connection connection = CONNECTION_POOL.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CAR_QUERY, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, car.getBrand());
            preparedStatement.setString(2, car.getModel());
            preparedStatement.setInt(3, car.getYear());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                car.setId(resultSet.getLong(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to create car", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public List<Car> getAll() {
        List<Car> cars;
        Connection connection = CONNECTION_POOL.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_QUERY);
            ResultSet resultSet = preparedStatement.executeQuery();
            cars = mapCars(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException("Unable to find all", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return cars;
    }

    @Override
    public Optional<Car> getById(Long id) {
        return Optional.empty();
    }

    @Override
    public void update(Car car) {

    }

    @Override
    public void deleteById(Long id) {
        Connection connection = CONNECTION_POOL.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_CAR_QUERY);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Unable to delete company", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }
}
