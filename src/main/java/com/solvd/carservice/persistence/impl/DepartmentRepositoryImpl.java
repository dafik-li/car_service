package com.solvd.carservice.persistence.impl;

import com.solvd.carservice.domain.Department;
import com.solvd.carservice.persistence.DepartmentRepository;
import java.util.List;
import java.util.Optional;

public class DepartmentRepositoryImpl implements DepartmentRepository {

    @Override
    public List<Department> getByName(String name) {
        return null;
    }

    @Override
    public void create(Department Entity) {

    }

    @Override
    public List<Department> getAll() {
        return null;
    }

    @Override
    public Optional<Department> getById(Long id) {
        return Optional.empty();
    }

    @Override
    public void update(Department Entity) {

    }

    @Override
    public void deleteById(Long id) {

    }
}
