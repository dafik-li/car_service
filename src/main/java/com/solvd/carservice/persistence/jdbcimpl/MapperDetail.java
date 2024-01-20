package com.solvd.carservice.persistence.jdbcimpl;

import com.solvd.carservice.domain.entity.Car;
import com.solvd.carservice.domain.entity.Detail;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MapperDetail {
    public List<Detail> map(ResultSet resultSet) {
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
