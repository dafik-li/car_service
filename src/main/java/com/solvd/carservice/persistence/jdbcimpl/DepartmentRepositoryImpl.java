package com.solvd.carservice.persistence.jdbcimpl;

import com.solvd.carservice.domain.entity.Company;
import com.solvd.carservice.domain.entity.Department;
import com.solvd.carservice.domain.entity.Employee;
import com.solvd.carservice.domain.entity.Service;
import com.solvd.carservice.persistence.ConnectionPool;
import com.solvd.carservice.persistence.DepartmentRepository;
import java.sql.*;
import java.util.List;
import java.util.Optional;

public class DepartmentRepositoryImpl implements DepartmentRepository {
    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();
    private final MapperService mapperService;
    private final MapperEmployee mapperEmployee;
    private final MapperDepartment mapperDepartment;
    private static final String INSERT_DEPARTMENT_QUERY = "INSERT INTO departments (name, company_id) VALUES (?, ?) ";
    private static final String DELETE_DEPARTMENT_QUERY = "DELETE FROM departments WHERE id = ? ";
    private static final String UPDATE_DEPARTMENT_NAME_QUERY = "UPDATE departments SET name = ? WHERE id = ? ";
    private static final String GET_ALL_QUERY =
            "SELECT d.id, d.name, c.id, c.name, c.address " +
            "FROM departments d " +
            "LEFT JOIN companies c ON d.company_id = c.id ";
    private static final String GET_SERVICES_BY_DEPARTMENT_ID =
            "SELECT services.id, services.name, services.price, services.hours_to_do, cars.id, cars.brand, cars.model, cars.year," +
                    "d.id, d.name, com.id, com.name, com.address " +
            "FROM services " +
            "LEFT JOIN cars ON services.car_id = cars.id " +
            "LEFT JOIN departments d ON services.department_id = d.id " +
            "LEFT JOIN companies com ON d.company_id = com.id " +
            "WHERE d.id = ? ";
    private static final String GET_EMPLOYEES_BY_DEPARTMENT_ID =
            "SELECT e.id, e.name, e.surname, e.age, e.position, e.level, e.salary, e.phone_number, d.id, d.name, com.id, com.name, com.address " +
            "FROM employees e " +
            "LEFT JOIN departments d ON e.department_id = d.id " +
            "LEFT JOIN companies com ON d.company_id = com.id " +
            "WHERE d.id = ? ";
    private static final String GET_BY_ID_QUERY = GET_ALL_QUERY.concat("WHERE d.id = ? ");
    private static final String GET_BY_DEPARTMENT_NAME_QUERY = GET_ALL_QUERY.concat("WHERE name = ? ");

    public DepartmentRepositoryImpl() {
        this.mapperService = new MapperService();
        this.mapperEmployee = new MapperEmployee();
        this.mapperDepartment = new MapperDepartment();
    }
    @Override
    public List<Department> getByName(String name) {
        List<Department> departments;
        Connection connection = CONNECTION_POOL.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_DEPARTMENT_NAME_QUERY);
            ResultSet resultSet = preparedStatement.executeQuery();
            departments = mapperDepartment.map(resultSet);
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
    public List<Service> getServicesByDepartmentId(Department department) {
        List<Service> services;
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_SERVICES_BY_DEPARTMENT_ID)) {
            preparedStatement.setLong(1, department.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            services = mapperService.map(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException("Unable to get employee services", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return services;
    }
    public List<Employee> getEmployeesByDepartmentId(Department department) {
        List<Employee> employees;
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_EMPLOYEES_BY_DEPARTMENT_ID)) {
            preparedStatement.setLong(1, department.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            employees = mapperEmployee.map(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException("Unable to get service employees", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return employees;
    }
    @Override
    public List<Department> getAll() {
        List<Department> departments;
        Connection connection = CONNECTION_POOL.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_QUERY);
            ResultSet resultSet = preparedStatement.executeQuery();
            departments = mapperDepartment.map(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException("Unable to get all", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        for (Department department : departments) {
            department.setServices(getServicesByDepartmentId(department));
        }
        for (Department department : departments) {
            department.setEmployees(getEmployeesByDepartmentId(department));
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
                            new Company(
                                    resultSet.getLong(3),
                                    resultSet.getString(4),
                                    resultSet.getString(5))));
        } catch (SQLException e) {
            throw new RuntimeException("Unable to get id", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        departmentOptional.get().setServices(getServicesByDepartmentId(departmentOptional.get()));
        departmentOptional.get().setEmployees(getEmployeesByDepartmentId(departmentOptional.get()));
        return departmentOptional;
    }
    @Override
    public void update(Optional<Department> department, String field) {
        Connection connection = CONNECTION_POOL.getConnection();
        String query;
        String value;
        switch (field) {
            case "name" :
                query = UPDATE_DEPARTMENT_NAME_QUERY;
                value = department.get().getName();
                break;
            default: throw new IllegalStateException("Unexpected value: " + field);
        }
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, value);
            preparedStatement.setLong(2, department.get().getId());
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
}
