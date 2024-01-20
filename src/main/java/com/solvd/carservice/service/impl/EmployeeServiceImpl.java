package com.solvd.carservice.service.impl;

import com.solvd.carservice.domain.entity.Employee;
import com.solvd.carservice.persistence.EmployeeRepository;
import com.solvd.carservice.service.EmployeeService;
import com.solvd.carservice.domain.controller.SwitcherRepository;
import java.util.List;
import java.util.Optional;

public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private static final SwitcherRepository switcherRepository = SwitcherRepository.getInstance();

    public EmployeeServiceImpl() {
        this.employeeRepository = switcherRepository.switchRepository(EmployeeRepository.class);
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
    public void change(Optional<Employee> employee, String field) {
        employeeRepository.update(employee, field);
    }
    @Override
    public void removeById(Long id) {
        employeeRepository.deleteById(id);
    }
    @Override
    public void addService(Long employeeId, Long serviceId) {
        employeeRepository.appendService(employeeId, serviceId);
    }
}
