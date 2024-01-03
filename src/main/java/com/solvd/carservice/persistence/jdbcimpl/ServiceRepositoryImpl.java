package com.solvd.carservice.persistence.jdbcimpl;

import com.solvd.carservice.domain.entity.*;
import com.solvd.carservice.persistence.ConnectionPool;
import com.solvd.carservice.persistence.ServiceRepository;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ServiceRepositoryImpl implements ServiceRepository {
    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();
    private static final String INSERT_SERVICE_QUERY = "INSERT INTO services " +
            "(name, price, hours_to_do, car_id, department_id) VALUES (?, ?, ?, ?, ?);";
    private static final String INSERT_EMPLOYEE_SERVICE_QUERY = "INSERT INTO employee_services (service_id, employee_id) VALUES (?, ?);";
    private static final String DELETE_SERVICE_QUERY = "DELETE FROM services WHERE id = ?;";
    private static final String UPDATE_SERVICE_NAME_QUERY = "UPDATE services SET name = ? WHERE id = ?;";
    private static final String UPDATE_SERVICE_PRICE_QUERY = "UPDATE services SET price = ? WHERE id = ?;";
    private static final String UPDATE_SERVICE_HOURS_TO_DO_QUERY = "UPDATE services SET hours_to_do = ? WHERE id = ?;";
    private static final String GET_ALL_QUERY =
            "SELECT services.id, services.name, services.price, services.hours_to_do, " +
                    "e.id, e.name, e.surname, e.age, e.position, e.level, e.salary, e.phone_number, " +
                    "cars.id, cars.brand, cars.model, cars.year, d.id, d.name, com.id, com.name, com.address " +
            "FROM services " +
            "LEFT JOIN employee_services es ON es.service_id = services.id " +
            "LEFT JOIN employees e ON es.employee_id = e.id " +
            "LEFT JOIN cars ON services.car_id = cars.id " +
            "LEFT JOIN departments d ON services.department_id = d.id " +
            "LEFT JOIN companies com ON d.company_id = com.id ";
    private static final String GET_BY_ID_QUERY = GET_ALL_QUERY.concat("WHERE services.id = ? ");
    private static final String GET_BY_SERVICE_NAME_QUERY = GET_ALL_QUERY.concat("WHERE name = ? ");
    private static final String GET_BY_SERVICE_PRICE_QUERY = GET_ALL_QUERY.concat("WHERE price = ? ");
    private static final String GET_BY_SERVICE_HOURS_TODO_QUERY = GET_ALL_QUERY.concat("WHERE hours_to_do = ? ");

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
    public List<Service> getByHoursToDo(Integer hoursToDo) {
        List<Service> services;
        Connection connection = CONNECTION_POOL.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_SERVICE_HOURS_TODO_QUERY);
            ResultSet resultSet = preparedStatement.executeQuery();
            services = mapServices(resultSet);
            while (resultSet.next()) {
                resultSet.getString("hours_to_do");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to get service hours to do", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return services;
    }
    public void appendEmployee(Long employeeId, Long serviceId) {
        Connection connection = CONNECTION_POOL.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_EMPLOYEE_SERVICE_QUERY);
            preparedStatement.setLong(1, serviceId);
            preparedStatement.setLong(2, employeeId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Unable to create employee-services", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }
    @Override
    public void create(Service service) {
        Connection connection = CONNECTION_POOL.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SERVICE_QUERY, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, service.getName());
            preparedStatement.setDouble(2, service.getPrice());
            preparedStatement.setInt(3, service.getHoursToDo());
            preparedStatement.setLong(4, service.getCarId().getId());
            preparedStatement.setLong(5, service.getDepartmentId().getId());
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
                    new Service(
                            resultSet.getLong(1),
                            resultSet.getString(2),
                            resultSet.getDouble(3),
                            resultSet.getInt(4),
                            new Car(
                                    resultSet.getLong(5),
                                    resultSet.getString(6),
                                    resultSet.getString(7),
                                    resultSet.getInt(8)),
                            new Department(
                                    resultSet.getLong(9),
                                    resultSet.getString(10),
                                    new Company(
                                            resultSet.getLong(11),
                                            resultSet.getString(12),
                                            resultSet.getString(13)))));
        } catch (SQLException e) {
            throw new RuntimeException("Unable to get id", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return serviceOptional;
    }
    @Override
    public void update(Optional<Service> service, String field) {
        Connection connection = CONNECTION_POOL.getConnection();
        String query;
        String value;
        switch (field) {
            case "name" :
                query = UPDATE_SERVICE_NAME_QUERY;
                value = service.get().getName();
                break;
            case "price" :
                query = UPDATE_SERVICE_PRICE_QUERY;
                value = String.valueOf(service.get().getPrice());
                break;
            case "hours_to_do" :
                query = UPDATE_SERVICE_HOURS_TO_DO_QUERY;
                value = String.valueOf(service.get().getHoursToDo());
                break;
            default: throw new IllegalStateException("Unexpected value: " + field);
        }
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, value);
            preparedStatement.setLong(2, service.get().getId());
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
    public ArrayList<Service> mapServices(ResultSet resultSet) {
        ArrayList<Service> services = new ArrayList<>();
        try {
            while (resultSet.next()) {
                Service service = new Service();
                service.setId(resultSet.getLong(1));
                service.setName(resultSet.getString(2));
                service.setPrice(resultSet.getDouble(3));
                service.setHoursToDo(resultSet.getInt(4));
                service.setEmployees(
                        new Employee(
                                resultSet.getLong(5),
                                resultSet.getString(6),
                                resultSet.getString(7),
                                resultSet.getInt(8),
                                resultSet.getString(9),
                                resultSet.getInt(10),
                                resultSet.getInt(11),
                                resultSet.getString(12)));
                service.setCarId(
                        new Car(
                                resultSet.getLong(13),
                                resultSet.getString(14),
                                resultSet.getString(15),
                                resultSet.getInt(16)));
                service.setDepartmentId(
                        new Department(
                                resultSet.getLong(17),
                                resultSet.getString(18),
                                new Company(
                                        resultSet.getLong(19),
                                        resultSet.getString(20),
                                        resultSet.getString(21))));
                services.add(service);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to map services", e);
        }
        return services;
    }
}
