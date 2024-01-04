package com.solvd.carservice.service;

import com.solvd.carservice.domain.entity.Employee;

public interface EmployeeService extends InterfaceService<Employee>{
    void addService(Long employeeId, Long serviceId);
}
