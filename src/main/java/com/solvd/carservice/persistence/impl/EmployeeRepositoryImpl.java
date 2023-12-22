package com.solvd.carservice.persistence.impl;

import com.solvd.carservice.domain.Employee;
import com.solvd.carservice.persistence.EmployeeRepository;

import java.util.List;
import java.util.Optional;

public class EmployeeRepositoryImpl implements EmployeeRepository {

    @Override
    public List<Employee> findByName(String name) {
        return null;
    }

    @Override
    public List<Employee> findBySurname(String surname) {
        return null;
    }

    @Override
    public List<Employee> findByAge(Integer age) {
        return null;
    }

    @Override
    public List<Employee> findByPosition(String position) {
        return null;
    }

    @Override
    public List<Employee> findByLevel(Integer level) {
        return null;
    }

    @Override
    public List<Employee> findBySalary(Integer salary) {
        return null;
    }

    @Override
    public List<Employee> findByPhoneNumber(String phoneNumber) {
        return null;
    }

    @Override
    public void create(Employee Entity) {

    }

    @Override
    public List<Employee> findAll() {
        return null;
    }

    @Override
    public Optional<Employee> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public void update(Employee Entity) {

    }

    @Override
    public void deleteById(Long id) {

    }
}
