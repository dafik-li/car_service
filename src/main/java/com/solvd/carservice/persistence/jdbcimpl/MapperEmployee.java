package com.solvd.carservice.persistence.jdbcimpl;

import com.solvd.carservice.domain.entity.Company;
import com.solvd.carservice.domain.entity.Department;
import com.solvd.carservice.domain.entity.Employee;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MapperEmployee {
    public List<Employee> mapEmployees(ResultSet resultSet) {
        List<Employee> employees = new ArrayList<>();
        try {
            while (resultSet.next()) {
                Employee employee = new Employee();
                employee.setId(resultSet.getLong(1));
                employee.setName(resultSet.getString(2));
                employee.setSurname(resultSet.getString(3));
                employee.setAge(resultSet.getInt(4));
                employee.setPosition(resultSet.getString(5));
                employee.setLevel(resultSet.getInt(6));
                employee.setSalary(resultSet.getInt(7));
                employee.setPhoneNumber(resultSet.getString(8));
                employee.setDepartmentId(
                        new Department(
                                resultSet.getLong(9),
                                resultSet.getString(10),
                                new Company(
                                        resultSet.getLong(11),
                                        resultSet.getString(12),
                                        resultSet.getString(13))));
                employees.add(employee);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to map employees", e);
        }
        return employees;
    }
}
