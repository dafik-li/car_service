package com.solvd.carservice.persistence.impl;

import com.solvd.carservice.domain.Company;
import com.solvd.carservice.domain.Department;
import com.solvd.carservice.domain.Employee;
import com.solvd.carservice.domain.Service;
import com.solvd.carservice.persistence.ConnectionPool;
import com.solvd.carservice.persistence.EmployeeRepository;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EmployeeRepositoryImpl implements EmployeeRepository {
    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();
    private static final String INSERT_EMPLOYEE_QUERY = "INSERT INTO employees " +
            "(name, surname, age, position, level, salary, phone_number, department_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
    private static final String INSERT_EMPLOYEE_SERVICE_QUERY = "INSERT INTO employee_services (employee_id, service_id) VALUES (?, ?);";
    private static final String DELETE_EMPLOYEE_QUERY = "DELETE FROM employees WHERE id = ?;";
    private static final String UPDATE_EMPLOYEE_NAME_QUERY = "UPDATE employees SET name = ? WHERE id = ?;";
    private static final String UPDATE_EMPLOYEE_SURNAME_QUERY = "UPDATE employees SET surname = ? WHERE id = ?;";
    private static final String UPDATE_EMPLOYEE_AGE_QUERY = "UPDATE employees SET age = ? WHERE id = ?;";
    private static final String UPDATE_EMPLOYEE_POSITION_QUERY = "UPDATE employees SET position = ? WHERE id = ?;";
    private static final String UPDATE_EMPLOYEE_LEVEL_QUERY = "UPDATE employees SET level = ? WHERE id = ?;";
    private static final String UPDATE_EMPLOYEE_SALARY_QUERY = "UPDATE employees SET salary = ? WHERE id = ?;";
    private static final String UPDATE_EMPLOYEE_PHONE_NUMBER_QUERY = "UPDATE employees SET phone_number = ? WHERE id = ?;";
    private static final String GET_ALL_QUERY =
            "SELECT e.id, e.name, e.surname, e.age, e.position, e.level, e.salary, e.phone_number, d.id, d.name, c.id, c.name, c.address FROM employees e " +
            "LEFT JOIN departments d on e.department_id = d.id " +
            "LEFT JOIN companies c on d.company_id = c.id;";
    private static final String GET_BY_ID_QUERY = "SELECT * FROM employees WHERE id = ?;";
    private static final String GET_BY_EMPLOYEE_NAME_QUERY = "SELECT * FROM employees WHERE name = ?;";
    private static final String GET_BY_EMPLOYEE_SURNAME_QUERY = "SELECT * FROM employees WHERE surname = ?;";
    private static final String GET_BY_EMPLOYEE_AGE_QUERY = "SELECT * FROM employees WHERE age = ?;";
    private static final String GET_BY_EMPLOYEE_POSITION_QUERY = "SELECT * FROM employees WHERE position = ?;";
    private static final String GET_BY_EMPLOYEE_LEVEL_QUERY = "SELECT * FROM employees WHERE level = ?;";
    private static final String GET_BY_EMPLOYEE_SALARY_QUERY = "SELECT * FROM employees WHERE salary = ?;";
    private static final String GET_BY_EMPLOYEE_PHONE_NUMBER_QUERY = "SELECT * FROM employees WHERE phone_number = ?;";

    @Override
    public List<Employee> getByName(String name) {
        List<Employee> employees;
        Connection connection = CONNECTION_POOL.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_EMPLOYEE_NAME_QUERY);
            ResultSet resultSet = preparedStatement.executeQuery();
            employees = mapEmployees(resultSet);
            while (resultSet.next()) {
                resultSet.getString("name");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to get employee name", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return employees;
    }
    @Override
    public List<Employee> getBySurname(String surname) {
        List<Employee> employees;
        Connection connection = CONNECTION_POOL.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_EMPLOYEE_SURNAME_QUERY);
            ResultSet resultSet = preparedStatement.executeQuery();
            employees = mapEmployees(resultSet);
            while (resultSet.next()) {
                resultSet.getString("surname");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to get employee surname", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return employees;
    }
    @Override
    public List<Employee> getByAge(Integer age) {
        List<Employee> employees;
        Connection connection = CONNECTION_POOL.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_EMPLOYEE_AGE_QUERY);
            ResultSet resultSet = preparedStatement.executeQuery();
            employees = mapEmployees(resultSet);
            while (resultSet.next()) {
                resultSet.getString("age");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to get employee age", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return employees;
    }
    @Override
    public List<Employee> getByPosition(String position) {
        List<Employee> employees;
        Connection connection = CONNECTION_POOL.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_EMPLOYEE_POSITION_QUERY);
            ResultSet resultSet = preparedStatement.executeQuery();
            employees = mapEmployees(resultSet);
            while (resultSet.next()) {
                resultSet.getString("position");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to get employee position", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return employees;
    }
    @Override
    public List<Employee> getByLevel(Integer level) {
        List<Employee> employees;
        Connection connection = CONNECTION_POOL.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_EMPLOYEE_LEVEL_QUERY);
            ResultSet resultSet = preparedStatement.executeQuery();
            employees = mapEmployees(resultSet);
            while (resultSet.next()) {
                resultSet.getString("level");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to get employee level", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return employees;
    }
    @Override
    public List<Employee> getBySalary(Integer salary) {
        List<Employee> employees;
        Connection connection = CONNECTION_POOL.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_EMPLOYEE_SALARY_QUERY);
            ResultSet resultSet = preparedStatement.executeQuery();
            employees = mapEmployees(resultSet);
            while (resultSet.next()) {
                resultSet.getString("salary");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to get employee salary", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return employees;
    }
    @Override
    public List<Employee> getByPhoneNumber(String phoneNumber) {
        List<Employee> employees;
        Connection connection = CONNECTION_POOL.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_EMPLOYEE_PHONE_NUMBER_QUERY);
            ResultSet resultSet = preparedStatement.executeQuery();
            employees = mapEmployees(resultSet);
            while (resultSet.next()) {
                resultSet.getString("phone_number");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to get employee phone number", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return employees;
    }
    @Override
    public void appendService(Employee employee, Service service) {
        Connection connection = CONNECTION_POOL.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_EMPLOYEE_SERVICE_QUERY);
            preparedStatement.setLong(1, employee.getId());
            preparedStatement.setLong(2, service.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Unable to create employee-service", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }
    @Override
    public void create(Employee employee) {
        Connection connection = CONNECTION_POOL.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_EMPLOYEE_QUERY, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, employee.getName());
            preparedStatement.setString(2, employee.getSurname());
            preparedStatement.setInt(3,employee.getAge());
            preparedStatement.setString(4, employee.getPosition());
            preparedStatement.setInt(5,employee.getLevel());
            preparedStatement.setInt(6,employee.getSalary());
            preparedStatement.setString(7, employee.getPhoneNumber());
            preparedStatement.setLong(8,employee.getDepartmentId().getId());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                employee.setId(resultSet.getLong(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to create employee", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }
    @Override
    public List<Employee> getAll() {
        List<Employee> employees;
        Connection connection = CONNECTION_POOL.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_QUERY);
            ResultSet resultSet = preparedStatement.executeQuery();
            employees = mapEmployees(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException("Unable to get all", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return employees;
    }
    @Override
    public Optional<Employee> getById(Long id) {
        Optional<Employee> employeeOptional;
        Connection connection = CONNECTION_POOL.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_ID_QUERY);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            employeeOptional = Optional.of(
                    new Employee(
                            resultSet.getLong(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getInt(4),
                            resultSet.getString(5),
                            resultSet.getInt(6),
                            resultSet.getInt(7),
                            resultSet.getString(8),
                            new Department(
                                    resultSet.getLong(9))));
        } catch (SQLException e) {
            throw new RuntimeException("Unable to get id", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return employeeOptional;
    }
    @Override
    public void update(Employee employee, String field) {
        Connection connection = CONNECTION_POOL.getConnection();
        String query;
        String value;
        switch (field) {
            case "name" :
                query = UPDATE_EMPLOYEE_NAME_QUERY;
                value = employee.getName();
                break;
            case "surname" :
                query = UPDATE_EMPLOYEE_SURNAME_QUERY;
                value = employee.getSurname();
                break;
            case "age" :
                query = UPDATE_EMPLOYEE_AGE_QUERY;
                value = String.valueOf(employee.getAge());
                break;
            case "position" :
                query = UPDATE_EMPLOYEE_POSITION_QUERY;
                value = employee.getPosition();
                break;
            case "level" :
                query = UPDATE_EMPLOYEE_LEVEL_QUERY;
                value = String.valueOf(employee.getLevel());
                break;
            case "salary" :
                query = UPDATE_EMPLOYEE_SALARY_QUERY;
                value = String.valueOf(employee.getSalary());
                break;
            case "phone_number" :
                query = UPDATE_EMPLOYEE_PHONE_NUMBER_QUERY;
                value = employee.getPhoneNumber();
                break;
            default: throw new IllegalStateException("Unexpected value: " + field);
        }
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, value);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Unable to update employee " + field, e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }
    @Override
    public void deleteById(Long id) {
        Connection connection = CONNECTION_POOL.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_EMPLOYEE_QUERY);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Unable to delete employee", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }
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
