package com.solvd.carservice.persistence.jdbcimpl;

import com.solvd.carservice.domain.entity.Car;
import com.solvd.carservice.domain.entity.Detail;
import com.solvd.carservice.persistence.ConnectionPool;
import com.solvd.carservice.persistence.DetailRepository;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DetailRepositoryImpl implements DetailRepository {
    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();
    private static final String INSERT_DETAIL_QUERY = "INSERT INTO details " +
            "(name, price, car_id, in_Stock, delivery_days) VALUES (?, ?, ?, ?, ?);";
    private static final String DELETE_DETAIL_QUERY = "DELETE FROM details WHERE id = ?;";
    private static final String UPDATE_DETAIL_NAME_QUERY = "UPDATE details SET name = ? WHERE id = ?;";
    private static final String UPDATE_DETAIL_PRICE_QUERY = "UPDATE details SET price = ? WHERE id = ?;";
    private static final String UPDATE_DETAIL_IN_STOCK_QUERY = "UPDATE details SET in_Stock = ? WHERE id = ?;";
    private static final String UPDATE_DETAIL_DELIVERY_DAYS_QUERY = "UPDATE details SET delivery_days = ? WHERE id = ?;";
    private static final String GET_ALL_QUERY =
            "SELECT details.id, details.name, details.price, cars.id, cars.brand, cars.model, cars.year, details.in_stock, details.delivery_days " +
            "FROM details " +
            "LEFT JOIN cars ON details.car_id = cars.id;";
    private static final String GET_BY_ID_QUERY = GET_ALL_QUERY.concat("WHERE details.id = ? ");
    private static final String GET_BY_DETAIL_NAME_QUERY = GET_ALL_QUERY.concat("WHERE name = ? ");
    private static final String GET_BY_DETAIL_PRICE_QUERY = GET_ALL_QUERY.concat("WHERE price = ? ");
    private static final String GET_BY_DETAIL_IN_STOCK_QUERY = GET_ALL_QUERY.concat("in_Stock = ? ");
    private static final String GET_BY_DETAIL_DELIVERY_DAYS_QUERY = GET_ALL_QUERY.concat("WHERE delivery_days = ? ");

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
    public List<Detail> getByPrice(Integer price) {
        List<Detail> details;
        Connection connection = CONNECTION_POOL.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_DETAIL_PRICE_QUERY);
            ResultSet resultSet = preparedStatement.executeQuery();
            details = mapDetails(resultSet);
            while (resultSet.next()) {
                resultSet.getString("price");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to get detail price", e);
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
            preparedStatement.setInt(2, detail.getPrice());
            preparedStatement.setLong(3, detail.getCarId().getId());
            preparedStatement.setBoolean(4, detail.getInStock());
            preparedStatement.setInt(5, detail.getDeliveryDays());
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
                    new Detail(
                            resultSet.getLong(1),
                            resultSet.getString(2),
                            resultSet.getInt(3),
                            new Car(
                                    resultSet.getLong(4),
                                    resultSet.getString(5),
                                    resultSet.getString(6),
                                    resultSet.getInt(7)),
                            resultSet.getBoolean(8),
                            resultSet.getInt(9)));
        } catch (SQLException e) {
            throw new RuntimeException("Unable to get id", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return detailOptional;
    }
    @Override
    public void update(Optional<Detail> detail, String field) {
        Connection connection = CONNECTION_POOL.getConnection();
        String query;
        String value;
        switch (field) {
            case "name" :
                query = UPDATE_DETAIL_NAME_QUERY;
                value = detail.get().getName();
                break;
            case "price" :
                query = UPDATE_DETAIL_PRICE_QUERY;
                value = String.valueOf(detail.get().getPrice());
                break;
            case "in_stock" :
                query = UPDATE_DETAIL_IN_STOCK_QUERY;
                value = String.valueOf(detail.get().getInStock());
                break;
            case "delivery_days" :
                query = UPDATE_DETAIL_DELIVERY_DAYS_QUERY;
                value = String.valueOf(detail.get().getDeliveryDays());
                break;
            default: throw new IllegalStateException("Unexpected value: " + field);
        }
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, value);
            preparedStatement.setLong(2, detail.get().getId());
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
                detail.setPrice(resultSet.getInt(3));
                detail.setCarId(
                        new Car(
                                resultSet.getLong(4),
                                resultSet.getString(5),
                                resultSet.getString(6),
                                resultSet.getInt(7)));
                detail.setInStock(resultSet.getBoolean(8));
                detail.setDeliveryDays(resultSet.getInt(9));
                details.add(detail);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to map details", e);
        }
        return details;
    }
}
