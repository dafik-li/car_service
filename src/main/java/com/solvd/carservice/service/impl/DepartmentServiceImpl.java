package com.solvd.carservice.service.impl;

import com.solvd.carservice.domain.Department;
import com.solvd.carservice.persistence.DepartmentRepository;
import com.solvd.carservice.persistence.impl.DepartmentRepositoryImpl;
import com.solvd.carservice.service.DepartmentService;
import java.util.List;
import java.util.Optional;

public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository departmentRepository;

    public DepartmentServiceImpl() {
        this.departmentRepository = new DepartmentRepositoryImpl();
    }
    @Override
    public Department add(Department department) {
        department.setId(null);
        departmentRepository.create(department);
        return department;
    }
    @Override
    public List<Department> retrieveAll() {
        return departmentRepository.getAll();
    }
    @Override
    public Optional<Department> retrieveById(Long id) {
        return departmentRepository.getById(id);
    }
    @Override
    public void change(Department department, String field) {
        departmentRepository.update(department, field);
    }
    @Override
    public void removeById(Long id) {
        departmentRepository.deleteById(id);
    }
}
