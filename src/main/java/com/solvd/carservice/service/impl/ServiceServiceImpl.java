package com.solvd.carservice.service.impl;

import com.solvd.carservice.domain.entity.Service;
import com.solvd.carservice.persistence.ServiceRepository;
import com.solvd.carservice.service.ServiceService;
import com.solvd.carservice.util.SwitcherRepository;
import java.util.List;
import java.util.Optional;

public class ServiceServiceImpl implements ServiceService {
    private final ServiceRepository serviceRepository;
    private static final SwitcherRepository switcherRepository = SwitcherRepository.getInstance();

    public ServiceServiceImpl() {
        this.serviceRepository = switcherRepository.switchRepository(ServiceRepository.class);
    }

    @Override
    public Service add(Service service) {
        service.setId(null);
        serviceRepository.create(service);
        return service;
    }
    @Override
    public List<Service> retrieveAll() {
        return serviceRepository.getAll();
    }
    @Override
    public Optional<Service> retrieveById(Long id) {
        return serviceRepository.getById(id);
    }
    @Override
    public void change(Optional<Service> service, String field) {
        serviceRepository.update(service, field);
    }
    @Override
    public void removeById(Long id) {
        serviceRepository.deleteById(id);
    }
    public void addEmployee(Long employeeId, Long serviceId) {
        serviceRepository.appendEmployee(employeeId, serviceId);
    }
}
