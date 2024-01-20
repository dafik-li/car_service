package com.solvd.carservice.persistence.jdbcimpl;

import com.solvd.carservice.domain.entity.Company;
import com.solvd.carservice.domain.entity.Department;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MapperDepartment {
    public List<Department> map(ResultSet resultSet) {
        List<Department> departments = new ArrayList<>();
        try {
            while (resultSet.next()) {
                Department department = new Department();
                department.setId(resultSet.getLong(1));
                department.setName(resultSet.getString(2));
                department.setCompanyId(
                        new Company(
                                resultSet.getLong(3),
                                resultSet.getString(4),
                                resultSet.getString(5)));
                departments.add(department);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to map departments", e);
        }
        return departments;
    }
}
