package com.solvd.carservice.persistence.jdbcimpl;

import com.solvd.carservice.domain.entity.Company;
import com.solvd.carservice.domain.entity.Department;
import com.solvd.carservice.domain.entity.Employee;
import com.solvd.carservice.domain.entity.Service;
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
            "SELECT e.id, e.name, e.surname, e.age, e.position, e.level, e.salary, e.phone_number, " +
                    "services.id, services.name, services.price, services.hours_to_do, d.id, d.name, c.id, c.name, c.address " +
            "FROM employees e " +
            "LEFT JOIN employee_services es ON es.employee_id = e.id " +
            "LEFT JOIN services ON es.service_id = services.id " +
            "LEFT JOIN departments d ON e.department_id = d.id " +
            "LEFT JOIN companies c ON d.company_id = c.id ";
    private static final String GET_BY_ID_QUERY = GET_ALL_QUERY.concat("WHERE e.id = ? ");
    private static final String GET_BY_EMPLOYEE_NAME_QUERY = GET_ALL_QUERY.concat("WHERE name = ? ");
    private static final String GET_BY_EMPLOYEE_SURNAME_QUERY = GET_ALL_QUERY.concat("WHERE surname = ? ");
    private static final String GET_BY_EMPLOYEE_AGE_QUERY = GET_ALL_QUERY.concat("WHERE age = ? ");
    private static final String GET_BY_EMPLOYEE_POSITION_QUERY = GET_ALL_QUERY.concat("WHERE position = ? ");
    private static final String GET_BY_EMPLOYEE_LEVEL_QUERY = GET_ALL_QUERY.concat("WHERE level = ? ");
    private static final String GET_BY_EMPLOYEE_SALARY_QUERY = GET_ALL_QUERY.concat("WHERE salary = ? ");
    private static final String GET_BY_EMPLOYEE_PHONE_NUMBER_QUERY = GET_ALL_QUERY.concat("WHERE phone_number = ? ");

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
    public void appendService(Long employeeId, Long serviceId) {
        Connection connection = CONNECTION_POOL.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_EMPLOYEE_SERVICE_QUERY);
            preparedStatement.setLong(1, employeeId);
            preparedStatement.setLong(2, serviceId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Unable to create employee-services", e);
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
            preparedStatement.setInt(3, employee.getAge());
            preparedStatement.setString(4, employee.getPosition());
            preparedStatement.setInt(5, employee.getLevel());
            preparedStatement.setInt(6, employee.getSalary());
            preparedStatement.setString(7, employee.getPhoneNumber());
            preparedStatement.setLong(8, employee.getDepartmentId().getId());
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
                            new Service(
                                    resultSet.getLong(9),
                                    resultSet.getString(10),
                                    resultSet.getDouble(11),
                                    resultSet.getInt(12),
                                    new Department(
                                            resultSet.getLong(13),
                                            resultSet.getString(14),
                                            new Company(
                                                    resultSet.getLong(15),
                                                    resultSet.getString(16),
                                                    resultSet.getString(17))))));
        } catch (SQLException e) {
            throw new RuntimeException("Unable to get id", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return employeeOptional;
    }
    @Override
    public void update(Optional<Employee> employee, String field) {
        Connection connection = CONNECTION_POOL.getConnection();
        String query;
        String value;
        switch (field) {
            case "name":
                query = UPDATE_EMPLOYEE_NAME_QUERY;
                value = employee.get().getName();
                break;
            case "surname":
                query = UPDATE_EMPLOYEE_SURNAME_QUERY;
                value = employee.get().getSurname();
                break;
            case "age":
                query = UPDATE_EMPLOYEE_AGE_QUERY;
                value = String.valueOf(employee.get().getAge());
                break;
            case "position":
                query = UPDATE_EMPLOYEE_POSITION_QUERY;
                value = employee.get().getPosition();
                break;
            case "level":
                query = UPDATE_EMPLOYEE_LEVEL_QUERY;
                value = String.valueOf(employee.get().getLevel());
                break;
            case "salary":
                query = UPDATE_EMPLOYEE_SALARY_QUERY;
                value = String.valueOf(employee.get().getSalary());
                break;
            case "phone_number":
                query = UPDATE_EMPLOYEE_PHONE_NUMBER_QUERY;
                value = employee.get().getPhoneNumber();
                break;
            default: throw new IllegalStateException("Unexpected value: " + field);
        }
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, value);
            preparedStatement.setLong(2, employee.get().getId());
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
                employee.setService(
                        new Service(
                                resultSet.getLong(9),
                                resultSet.getString(10),
                                resultSet.getDouble(11),
                                resultSet.getInt(12),
                                new Department(
                                        resultSet.getLong(13),
                                        resultSet.getString(14),
                                        new Company(
                                                resultSet.getLong(15),
                                                resultSet.getString(16),
                                                resultSet.getString(17)))));
                employees.add(employee);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to map employees", e);
        }
        return employees;
    }
}
