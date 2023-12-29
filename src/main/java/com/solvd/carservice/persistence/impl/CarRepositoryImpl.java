package com.solvd.carservice.persistence.impl;

import com.solvd.carservice.domain.Car;
import com.solvd.carservice.persistence.CarRepository;
import com.solvd.carservice.persistence.ConnectionPool;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CarRepositoryImpl implements CarRepository {
    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();
    private static final String INSERT_CAR_QUERY = "INSERT INTO cars (brand, model, year) VALUES (?, ?, ?);";
    private static final String DELETE_CAR_QUERY = "DELETE FROM cars WHERE id = ?;";
    private static final String UPDATE_CAR_BRAND_QUERY = "UPDATE cars SET brand = ? WHERE id = ?;";
    private static final String UPDATE_CAR_MODEL_QUERY = "UPDATE cars SET model = ? WHERE id = ?;";
    private static final String UPDATE_CAR_YEAR_QUERY = "UPDATE cars SET year = ? WHERE id = ?;";
    private static final String GET_ALL_QUERY = "SELECT * FROM cars;";
    private static final String GET_BY_ID_QUERY = "SELECT * FROM cars WHERE id = ?;";
    private static final String GET_BY_CAR_BRAND_QUERY = "SELECT * FROM cars where brand = ?;";
    private static final String GET_BY_CAR_MODEL_QUERY = "SELECT * FROM cars where model = ?;";
    private static final String GET_BY_CAR_YEAR_QUERY = "SELECT * FROM cars where year = ?;";

    @Override
    public List<Car> getByBrand(String brand) {
        List<Car> cars;
        Connection connection = CONNECTION_POOL.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_CAR_BRAND_QUERY);
            ResultSet resultSet = preparedStatement.executeQuery();
            cars = mapCars(resultSet);
            while (resultSet.next()) {
                resultSet.getString("brand");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to get car brand", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return cars;
    }
    @Override
    public List<Car> getByModel(String model) {
        List<Car> cars;
        Connection connection = CONNECTION_POOL.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_CAR_MODEL_QUERY);
            ResultSet resultSet = preparedStatement.executeQuery();
            cars = mapCars(resultSet);
            while (resultSet.next()) {
                resultSet.getString("model");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to get car model", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return cars;
    }
    @Override
    public List<Car> getByYear(Integer year) {
        List<Car> cars;
        Connection connection = CONNECTION_POOL.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_CAR_YEAR_QUERY);
            ResultSet resultSet = preparedStatement.executeQuery();
            cars = mapCars(resultSet);
            while (resultSet.next()) {
                resultSet.getString("year");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to get car year", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return cars;
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
            PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_QUERY);
            ResultSet resultSet = preparedStatement.executeQuery();
            cars = mapCars(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException("Unable to get all", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return cars;
    }
    @Override
    public Optional<Car> getById(Long id) {
        Optional<Car> carOptional;
        Connection connection = CONNECTION_POOL.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_ID_QUERY);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            carOptional = Optional.of(
                    new Car(
                            resultSet.getLong(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getInt(4)));
        } catch (SQLException e) {
            throw new RuntimeException("Unable to get id", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return carOptional;
    }
    @Override
    public void update(Car car, String field) {
        Connection connection = CONNECTION_POOL.getConnection();
        String query;
        String value;
        switch (field) {
            case "brand" :
                query = UPDATE_CAR_BRAND_QUERY;
                value = car.getBrand();
                break;
            case "model" :
                query = UPDATE_CAR_MODEL_QUERY;
                value = car.getModel();
                break;
            case "year" :
                query = UPDATE_CAR_YEAR_QUERY;
                value = String.valueOf(car.getYear());
                break;
            default: throw new IllegalStateException("Unexpected value: " + field);
        }
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, value);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Unable to update car " + field, e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }
    @Override
    public void deleteById(Long id) {
        Connection connection = CONNECTION_POOL.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_CAR_QUERY);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Unable to delete car", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }
    public List<Car> mapCars(ResultSet resultSet) {
        List<Car> cars = new ArrayList<>();
        try {
            while (resultSet.next()) {
                Car car = new Car();
                car.setId(resultSet.getLong(1));
                car.setBrand(resultSet.getString(2));
                car.setModel(resultSet.getString(3));
                car.setYear(resultSet.getInt(4));
                cars.add(car);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to map cars", e);
        }
        return cars;
    }
}
