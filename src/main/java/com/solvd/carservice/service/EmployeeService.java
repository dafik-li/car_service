package com.solvd.carservice.service;

import com.solvd.carservice.domain.entity.Employee;
import com.solvd.carservice.domain.entity.Service;

import java.util.List;

public interface EmployeeService extends InterfaceService<Employee>{
    void addService(Long employeeId, Long serviceId);
}
