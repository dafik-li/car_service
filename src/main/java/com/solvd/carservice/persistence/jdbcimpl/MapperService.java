package com.solvd.carservice.persistence.jdbcimpl;

import com.solvd.carservice.domain.entity.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MapperService {
    public List<Service> mapServices(ResultSet resultSet) {
        List<Service> services = new ArrayList<>();
        try {
            while (resultSet.next()) {
                Service service = new Service();
                service.setId(resultSet.getLong(1));
                service.setName(resultSet.getString(2));
                service.setPrice(resultSet.getDouble(3));
                service.setHoursToDo(resultSet.getInt(4));
                service.setCarId(
                        new Car(
                                resultSet.getLong(5),
                                resultSet.getString(6),
                                resultSet.getString(7),
                                resultSet.getInt(8)));
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
