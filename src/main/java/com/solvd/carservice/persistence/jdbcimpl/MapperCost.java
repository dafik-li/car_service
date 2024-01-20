package com.solvd.carservice.persistence.jdbcimpl;

import com.solvd.carservice.domain.entity.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MapperCost {
    public List<Cost> map(ResultSet resultSet) {
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
