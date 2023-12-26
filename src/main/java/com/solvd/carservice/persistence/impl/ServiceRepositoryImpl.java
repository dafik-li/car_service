package com.solvd.carservice.persistence.impl;

import com.solvd.carservice.domain.Car;
import com.solvd.carservice.domain.Department;
import com.solvd.carservice.domain.Service;
import com.solvd.carservice.persistence.ConnectionPool;
import com.solvd.carservice.persistence.ServiceRepository;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ServiceRepositoryImpl implements ServiceRepository {
    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();
    private static final String INSERT_SERVICE_QUERY = "INSERT INTO services (name, price, car_id, department_id) VALUES (?, ?, ?, ?);";
    private static final String DELETE_SERVICE_QUERY = "DELETE FROM services WHERE id = ?;";
    private static final String UPDATE_SERVICE_NAME_QUERY = "UPDATE services SET name = ? WHERE id = ?;";
    private static final String UPDATE_SERVICE_PRICE_QUERY = "UPDATE services SET price = ? WHERE id = ?;";
    private static final String GET_ALL_QUERY = "SELECT * FROM services;";
    private static final String GET_BY_ID_QUERY = "SELECT * FROM services WHERE id = ?;";
    private static final String GET_BY_SERVICE_NAME_QUERY = "SELECT * FROM services WHERE name = ?;";
    private static final String GET_BY_SERVICE_PRICE_QUERY = "SELECT * FROM services WHERE price = ?;";
    private static final String GET_BY_SERVICE_DAYSTODO_QUERY = "SELECT * FROM services WHERE days_to_do = ?;";

    @Override
    public List<Service> getByName(String name) {
        List<Service> services;
        Connection connection = CONNECTION_POOL.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_SERVICE_NAME_QUERY);
            ResultSet resultSet = preparedStatement.executeQuery();
            services = mapServices(resultSet);
            while (resultSet.next()) {
                resultSet.getString("name");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to get service name", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return services;
    }
    @Override
    public List<Service> getByPrice(Double price) {
        List<Service> services;
        Connection connection = CONNECTION_POOL.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_SERVICE_PRICE_QUERY);
            ResultSet resultSet = preparedStatement.executeQuery();
            services = mapServices(resultSet);
            while (resultSet.next()) {
                resultSet.getString("price");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to get service price", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return services;
    }
    @Override
    public List<Service> getByDaysToDo(Double price) {
        List<Service> services;
        Connection connection = CONNECTION_POOL.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_SERVICE_DAYSTODO_QUERY);
            ResultSet resultSet = preparedStatement.executeQuery();
            services = mapServices(resultSet);
            while (resultSet.next()) {
                resultSet.getString("days_to_do");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to get service days to do", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return services;
    }
    @Override
    public void create(Service service) {
        Connection connection = CONNECTION_POOL.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SERVICE_QUERY, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, service.getName());
            preparedStatement.setDouble(2, service.getPrice());
            preparedStatement.setLong(3, service.getCarId().getId());
            preparedStatement.setLong(4, service.getDepartmentId().getId());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                service.setId(resultSet.getLong(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to create service", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }
    @Override
    public List<Service> getAll() {
        List<Service> services;
        Connection connection = CONNECTION_POOL.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_QUERY);
            ResultSet resultSet = preparedStatement.executeQuery();
            services = mapServices(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException("Unable to get all", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return services;
    }
    @Override
    public Optional<Service> getById(Long id) {
        Optional<Service> serviceOptional;
        Connection connection = CONNECTION_POOL.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_ID_QUERY);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            serviceOptional = Optional.of(
                    new Service(resultSet.getLong(1),
                            resultSet.getString(2),
                            resultSet.getDouble(3),
                            resultSet.getDouble(4),
                            new Car(resultSet.getLong(5)),
                            new Department(resultSet.getLong(6))));
        } catch (SQLException e) {
            throw new RuntimeException("Unable to get id", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return serviceOptional;
    }
    @Override
    public void update(Service service, String field) {
        Connection connection = CONNECTION_POOL.getConnection();
        String query;
        String value;
        switch (field) {
            case "name" :
                query = UPDATE_SERVICE_NAME_QUERY;
                value = service.getName();
                break;
            case "in_stock" :
                query = UPDATE_SERVICE_PRICE_QUERY;
                value = String.valueOf(service.getPrice());
                break;
            default: throw new IllegalStateException("Unexpected value: " + field);
        }
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, value);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Unable to update service " + field, e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }
    @Override
    public void deleteById(Long id) {
        Connection connection = CONNECTION_POOL.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_SERVICE_QUERY);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Unable to delete service", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }
    public List<Service> mapServices(ResultSet resultSet) {
        List<Service> services = new ArrayList<>();
        try {
            while (resultSet.next()) {
                Service service = new Service();
                service.setId(resultSet.getLong(1));
                service.setName(resultSet.getString(2));
                service.setPrice(resultSet.getDouble(3));
                service.setCarId(new Car(resultSet.getLong(4)));
                service.setDepartmentId(new Department(resultSet.getLong(5)));
                services.add(service);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to map services", e);
        }
        return services;
    }
}
