package com.solvd.carservice.service;

import com.solvd.carservice.domain.entity.Employee;
import com.solvd.carservice.domain.entity.Service;

public interface EmployeeService extends InterfaceService<Employee>{
    Employee addService(Employee employeeId, Service serviceId);
}
