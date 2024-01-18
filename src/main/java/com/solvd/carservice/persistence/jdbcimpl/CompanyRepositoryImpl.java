package com.solvd.carservice.persistence.jdbcimpl;

import com.solvd.carservice.domain.entity.Company;
import com.solvd.carservice.domain.entity.Department;
import com.solvd.carservice.persistence.CompanyRepository;
import com.solvd.carservice.persistence.ConnectionPool;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CompanyRepositoryImpl extends CompanyRepository {
    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();
    private static final String INSERT_COMPANY_QUERY = "INSERT INTO companies (name, address) VALUES (?, ?) ";
    private static final String DELETE_COMPANY_QUERY = "DELETE FROM companies WHERE id = ? ";
    private static final String UPDATE_COMPANY_NAME_QUERY = "UPDATE companies SET name = ? WHERE id = ? ";
    private static final String UPDATE_COMPANY_ADDRESS_QUERY = "UPDATE companies SET address = ? WHERE id = ? ";
    private static final String GET_ALL_QUERY = "SELECT * FROM companies ";
    private static final String GET_DEPARTMENTS_BY_COMPANY_ID =
            "SELECT d.id, d.name, com.id, com.name, com.address " +
            "FROM departments d " +
            "LEFT JOIN companies com ON d.company_id = com.id " +
            "WHERE com.id = ? ";
    private static final String GET_BY_ID_QUERY = GET_ALL_QUERY.concat("WHERE id = ? ");
    private static final String GET_BY_COMPANY_NAME_QUERY = GET_ALL_QUERY.concat("WHERE name = ? ");
    private static final String GET_BY_COMPANY_ADDRESS_QUERY = GET_ALL_QUERY.concat("WHERE address = ? ");

    @Override
    public List<Company> getByName(String name) {
        List<Company> companies;
        Connection connection = CONNECTION_POOL.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_COMPANY_NAME_QUERY);
            ResultSet resultSet = preparedStatement.executeQuery();
            companies = mapCompanies(resultSet);
            while (resultSet.next()) {
                resultSet.getString("name");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to get company name", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return companies;
    }
    @Override
    public List<Company> getByAddress(String address) {
        List<Company> companies;
        Connection connection = CONNECTION_POOL.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_COMPANY_ADDRESS_QUERY);
            ResultSet resultSet = preparedStatement.executeQuery();
            companies = mapCompanies(resultSet);
            while (resultSet.next()) {
                resultSet.getString("address");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to get company address", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return companies;
    }
    @Override
    public void create(Company company) {
        Connection connection = CONNECTION_POOL.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_COMPANY_QUERY, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, company.getName());
            preparedStatement.setString(2, company.getAddress());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                company.setId(resultSet.getLong(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to create company", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }
    public List<Department> getDepartmentsByCompanyId(Company company) {
        List<Department> departments;
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_DEPARTMENTS_BY_COMPANY_ID)) {
            preparedStatement.setLong(1, company.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            departments = mapperDepartment.map(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException("Unable to get employee services", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return departments;
    }
    @Override
    public List<Company> getAll() {
        List<Company> companies;
        Connection connection = CONNECTION_POOL.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_QUERY);
            ResultSet resultSet = preparedStatement.executeQuery();
            companies = mapCompanies(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException("Unable to get all", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        for (Company company : companies) {
            company.setDepartments(getDepartmentsByCompanyId(company));
        }
        return companies;
    }
    @Override
    public Optional<Company> getById(Long id) {
        Optional<Company> companyOptional;
        Connection connection = CONNECTION_POOL.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_ID_QUERY);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            companyOptional = Optional.of(
                    new Company(
                            resultSet.getLong(1),
                            resultSet.getString(2),
                            resultSet.getString(3)));
        } catch (SQLException e) {
            throw new RuntimeException("Unable to get id", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        companyOptional.get().setDepartments(getDepartmentsByCompanyId(companyOptional.get()));
        return companyOptional;
    }
    @Override
    public void update(Optional<Company> company, String field) {
        Connection connection = CONNECTION_POOL.getConnection();
        String query;
        String value;
        switch (field) {
            case "name" :
                query = UPDATE_COMPANY_NAME_QUERY;
                value = company.get().getName();
                break;
            case "address" :
                query = UPDATE_COMPANY_ADDRESS_QUERY;
                value = company.get().getAddress();
                break;
            default: throw new IllegalStateException("Unexpected value: " + field);
        }
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, value);
            preparedStatement.setLong(2, company.get().getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Unable to update company " + field, e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }
    @Override
    public void deleteById(Long id) {
        Connection connection = CONNECTION_POOL.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_COMPANY_QUERY);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Unable to delete company", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }
    public List<Company> mapCompanies(ResultSet resultSet) {
        List<Company> companies = new ArrayList<>();
        try {
            while (resultSet.next()) {
                Company company = new Company();
                company.setId(resultSet.getLong(1));
                company.setName(resultSet.getString(2));
                company.setAddress(resultSet.getString(3));
                companies.add(company);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to map companies", e);
        }
        return companies;
    }
}
