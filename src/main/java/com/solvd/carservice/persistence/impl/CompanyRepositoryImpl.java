package com.solvd.carservice.persistence.impl;

import com.solvd.carservice.domain.Company;
import com.solvd.carservice.persistence.CompanyRepository;
import com.solvd.carservice.persistence.ConnectionPool;
import java.sql.*;
import java.util.List;
import java.util.Optional;

public class CompanyRepositoryImpl implements CompanyRepository {
    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();
    private static final String INSERT_COMPANY_QUERY = "INSERT INTO companies(name, address) values (?,?);";
    private static final String DELETE_COMPANY_QUERY = "DELETE FROM companies where id = ?;";
    private static final String FIND_ALL_QUERY = "SELECT * FROM companies where name = ?;";
    private static final String FIND_BY_NAME_COMPANY_QUERY = "SELECT * FROM companies where name = ?;";
    private static final String FIND_BY_ADDRESS_COMPANY_QUERY = "SELECT * FROM companies where address = ?;";
    @Override
    public List<Company> findByName(String name) {
        List<Company> companies;
        Connection connection = CONNECTION_POOL.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_NAME_COMPANY_QUERY);
            ResultSet resultSet = preparedStatement.executeQuery();
            companies = mapCompanies(resultSet);
            while (resultSet.next()) {
                resultSet.getString("name");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to find company name", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return companies;
    }

    @Override
    public List<Company> findByAddress(String address) {
        List<Company> companies;
        Connection connection = CONNECTION_POOL.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ADDRESS_COMPANY_QUERY);
            ResultSet resultSet = preparedStatement.executeQuery();
            companies = mapCompanies(resultSet);
            while (resultSet.next()) {
                resultSet.getString("address");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to find company address", e);
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

    @Override
    public List<Company> findAll() {
        List<Company> companies;
        Connection connection = CONNECTION_POOL.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_QUERY);
            ResultSet resultSet = preparedStatement.executeQuery();
            companies = mapCompanies(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException("Unable to find all", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return companies;
    }

    @Override
    public Optional<Company> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public void update(Company company) {

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
}
