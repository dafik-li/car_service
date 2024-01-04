package com.solvd.carservice.service.impl;

import com.solvd.carservice.domain.entity.Department;
import com.solvd.carservice.persistence.DepartmentRepository;
import com.solvd.carservice.service.DepartmentService;
import com.solvd.carservice.util.SwitcherRepository;
import java.util.List;
import java.util.Optional;

public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository departmentRepository;
    private static final SwitcherRepository switcherRepository = SwitcherRepository.getInstance();

    public DepartmentServiceImpl() {
        this.departmentRepository = switcherRepository.switchRepository(DepartmentRepository.class);
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
    public void change(Optional<Department> department, String field) {
        departmentRepository.update(department, field);
    }
    @Override
    public void removeById(Long id) {
        departmentRepository.deleteById(id);
    }
}
