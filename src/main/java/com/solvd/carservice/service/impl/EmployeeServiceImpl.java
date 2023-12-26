package com.solvd.carservice.service.impl;

import com.solvd.carservice.domain.Employee;
import com.solvd.carservice.persistence.EmployeeRepository;
import com.solvd.carservice.persistence.impl.EmployeeRepositoryImpl;
import com.solvd.carservice.service.EmployeeService;
import java.util.List;
import java.util.Optional;

public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl() {
        this.employeeRepository = new EmployeeRepositoryImpl();
    }
    @Override
    public Employee add(Employee employee) {
        employee.setId(null);
        employeeRepository.create(employee);
        return employee;
    }
    @Override
    public List<Employee> retrieveAll() {
        return employeeRepository.getAll();
    }
    @Override
    public Optional<Employee> retrieveById(Long id) {
        return employeeRepository.getById(id);
    }
    @Override
    public void change(Employee employee, String field) {
        employeeRepository.update(employee, field);
    }
    @Override
    public void removeById(Long id) {
        employeeRepository.deleteById(id);
    }
}
