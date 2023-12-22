package com.solvd.carservice.persistence.impl;

import com.solvd.carservice.domain.Department;
import com.solvd.carservice.persistence.DepartmentRepository;

import java.util.List;
import java.util.Optional;

public class DepartmentRepositoryImpl implements DepartmentRepository {

    @Override
    public List<Department> findByName(String name) {
        return null;
    }

    @Override
    public void create(Department Entity) {

    }

    @Override
    public List<Department> findAll() {
        return null;
    }

    @Override
    public Optional<Department> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public void update(Department Entity) {

    }

    @Override
    public void deleteById(Long id) {

    }
}
