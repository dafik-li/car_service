package com.solvd.carservice.service;

import com.solvd.carservice.domain.entity.Employee;
import com.solvd.carservice.domain.entity.Service;
import java.util.List;

public interface ServiceService extends InterfaceService<Service>{
    void addEmployee(Long employeeId, Long serviceId);
    List<Service> retrieveByCar(Long carId);
    List<Employee> retrieveEmployeesByServiceId(Service service);
}
