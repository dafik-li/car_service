package com.solvd.carservice.persistence.jdbcimpl;

import com.solvd.carservice.domain.entity.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MapperOrder {
    public List<Order> map(ResultSet resultSet) {
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
                                        new Car(
                                                resultSet.getLong(14),
                                                resultSet.getString(15),
                                                resultSet.getString(16),
                                                resultSet.getInt(17)),
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
