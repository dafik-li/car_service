package com.solvd.carservice.service;

import com.solvd.carservice.domain.entity.Service;

public interface ServiceService extends InterfaceService<Service>{
    void addEmployee(Long employeeId, Long serviceId);
}
