package com.solvd.carservice.persistence.impl;

import com.solvd.carservice.domain.*;
import com.solvd.carservice.persistence.ConnectionPool;
import com.solvd.carservice.persistence.DepartmentRepository;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DepartmentRepositoryImpl implements DepartmentRepository {
    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();
    private static final String INSERT_DEPARTMENT_QUERY = "INSERT INTO departments (name, company_id) VALUES (?, ?);";
    private static final String DELETE_DEPARTMENT_QUERY = "DELETE FROM departments WHERE id = ?;";
    private static final String UPDATE_DEPARTMENT_NAME_QUERY = "UPDATE departments SET name = ? WHERE id = ?;";
    private static final String GET_ALL_QUERY =
            "SELECT d.id, d.name, c.id, c.name, c.address FROM departments d LEFT JOIN companies c on d.company_id = c.id;";
    private static final String GET_BY_ID_QUERY = "SELECT * FROM departments WHERE id = ?;";
    private static final String GET_BY_DEPARTMENT_NAME_QUERY = "SELECT * FROM departments WHERE name = ?;";

    @Override
    public List<Department> getByName(String name) {
        List<Department> departments;
        Connection connection = CONNECTION_POOL.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_DEPARTMENT_NAME_QUERY);
            ResultSet resultSet = preparedStatement.executeQuery();
            departments = mapDepartments(resultSet);
            while (resultSet.next()) {
                resultSet.getString("name");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to get department name", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return departments;
    }
    @Override
    public void create(Department department) {
        Connection connection = CONNECTION_POOL.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_DEPARTMENT_QUERY, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, department.getName());
            preparedStatement.setLong(2, department.getCompanyId().getId());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                department.setId(resultSet.getLong(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to create department", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }
    @Override
    public List<Department> getAll() {
        List<Department> departments;
        Connection connection = CONNECTION_POOL.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_QUERY);
            ResultSet resultSet = preparedStatement.executeQuery();
            departments = mapDepartments(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException("Unable to get all", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return departments;
    }
    @Override
    public Optional<Department> getById(Long id) {
        Optional<Department> departmentOptional;
        Connection connection = CONNECTION_POOL.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_ID_QUERY);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            departmentOptional = Optional.of(
                    new Department(resultSet.getLong(1),
                            resultSet.getString(2),
                            new Company(resultSet.getLong(3))));
        } catch (SQLException e) {
            throw new RuntimeException("Unable to get id", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return departmentOptional;
    }
    @Override
    public void update(Department department, String field) {
        Connection connection = CONNECTION_POOL.getConnection();
        String query;
        String value;
        switch (field) {
            case "name" :
                query = UPDATE_DEPARTMENT_NAME_QUERY;
                value = department.getName();
                break;
            default: throw new IllegalStateException("Unexpected value: " + field);
        }
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, value);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Unable to update department " + field, e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }
    @Override
    public void deleteById(Long id) {
        Connection connection = CONNECTION_POOL.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_DEPARTMENT_QUERY);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Unable to delete department", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }
    public List<Department> mapDepartments(ResultSet resultSet) {
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
