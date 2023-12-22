package com.solvd.carservice.persistence.impl;

import com.solvd.carservice.domain.Employee;
import com.solvd.carservice.persistence.EmployeeRepository;
import java.util.List;
import java.util.Optional;

public class EmployeeRepositoryImpl implements EmployeeRepository {

    @Override
    public List<Employee> getByName(String name) {
        return null;
    }

    @Override
    public List<Employee> getBySurname(String surname) {
        return null;
    }

    @Override
    public List<Employee> getByAge(Integer age) {
        return null;
    }

    @Override
    public List<Employee> getByPosition(String position) {
        return null;
    }

    @Override
    public List<Employee> getByLevel(Integer level) {
        return null;
    }

    @Override
    public List<Employee> getBySalary(Integer salary) {
        return null;
    }

    @Override
    public List<Employee> getByPhoneNumber(String phoneNumber) {
        return null;
    }

    @Override
    public void create(Employee Entity) {

    }

    @Override
    public List<Employee> getAll() {
        return null;
    }

    @Override
    public Optional<Employee> getById(Long id) {
        return Optional.empty();
    }

    @Override
    public void update(Employee Entity) {

    }

    @Override
    public void deleteById(Long id) {

    }
}
