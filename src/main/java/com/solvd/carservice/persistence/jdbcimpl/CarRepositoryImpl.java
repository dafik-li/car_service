package com.solvd.carservice.persistence.jdbcimpl;

import com.solvd.carservice.domain.entity.Car;
import com.solvd.carservice.domain.entity.Detail;
import com.solvd.carservice.domain.entity.Service;
import com.solvd.carservice.persistence.CarRepository;
import com.solvd.carservice.persistence.ConnectionPool;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CarRepositoryImpl implements CarRepository {
    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();
    private final MapperService mapperService;
    private final MapperDetail mapperDetail;
    private static final String INSERT_CAR_QUERY = "INSERT INTO cars (brand, model, year) VALUES (?, ?, ?);";
    private static final String DELETE_CAR_QUERY = "DELETE FROM cars WHERE id = ?;";
    private static final String UPDATE_CAR_BRAND_QUERY = "UPDATE cars SET brand = ? WHERE id = ?;";
    private static final String UPDATE_CAR_MODEL_QUERY = "UPDATE cars SET model = ? WHERE id = ?;";
    private static final String UPDATE_CAR_YEAR_QUERY = "UPDATE cars SET year = ? WHERE id = ?;";
    private static final String GET_ALL_QUERY = "SELECT * FROM cars;";
    private static final String GET_SERVICES_BY_CAR_ID =
            "SELECT services.id, services.name, services.price, services.hours_to_do, cars.id, cars.brand, cars.model, cars.year, " +
                    "e.id, e.name, e.surname, e.age, e.position, e.level, e.salary, e.phone_number, " +
                    "d.id, d.name, com.id, com.name, com.address " +
            "FROM services " +
            "LEFT JOIN employee_services es ON es.service_id = services.id " +
            "LEFT JOIN employees e ON es.employee_id = e.id " +
            "LEFT JOIN cars ON services.car_id = cars.id " +
            "LEFT JOIN departments d ON services.department_id = d.id " +
            "LEFT JOIN companies com ON d.company_id = com.id " +
            "WHERE cars.id = ? ";
    private static final String GET_DETAILS_BY_CAR_ID =
            "SELECT details.id, details.name, details.price, cars.id, cars.brand, cars.model, cars.year, details.in_stock, details.delivery_days " +
            "FROM details " +
            "LEFT JOIN cars ON details.car_id = cars.id " +
            "WHERE cars.id = ? ";
    private static final String GET_BY_ID_QUERY = "SELECT * FROM cars WHERE id = ?;";
    private static final String GET_BY_CAR_BRAND_QUERY = "SELECT * FROM cars where brand = ?;";
    private static final String GET_BY_CAR_MODEL_QUERY = "SELECT * FROM cars where model = ?;";
    private static final String GET_BY_CAR_YEAR_QUERY = "SELECT * FROM cars where year = ?;";

    public CarRepositoryImpl() {
        this.mapperService = new MapperService();
        this.mapperDetail = new MapperDetail();
    }
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
    public List<Service> getServicesByCarId(Car car) {
        List<Service> services;
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_SERVICES_BY_CAR_ID)) {
            preparedStatement.setLong(1, car.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            services = mapperService.map(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException("Unable to get employee services", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return services;
    }
    @Override
    public List<Detail> getDetailByCarId(Car car) {
        List<Detail> details;
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_DETAILS_BY_CAR_ID)) {
            preparedStatement.setLong(1, car.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            details = mapperDetail.map(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException("Unable to get employee services", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return details;
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
        for (Car car : cars) {
            car.setServices(getServicesByCarId(car));
        }
        for (Car car : cars) {
            car.setDetails(getDetailByCarId(car));
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
        carOptional.get().setServices(getServicesByCarId(carOptional.get()));
        carOptional.get().setDetails(getDetailByCarId(carOptional.get()));
        return carOptional;
    }
    @Override
    public void update(Optional<Car> car, String field) {
        Connection connection = CONNECTION_POOL.getConnection();
        String query;
        String value;
        switch (field) {
            case "brand" :
                query = UPDATE_CAR_BRAND_QUERY;
                value = car.get().getBrand();
                break;
            case "model" :
                query = UPDATE_CAR_MODEL_QUERY;
                value = car.get().getModel();
                break;
            case "year" :
                query = UPDATE_CAR_YEAR_QUERY;
                value = String.valueOf(car.get().getYear());
                break;
            default: throw new IllegalStateException("Unexpected value: " + field);
        }
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, value);
            preparedStatement.setLong(2, car.get().getId());
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
