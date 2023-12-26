package com.solvd.carservice.service.impl;

import com.solvd.carservice.domain.Service;
import com.solvd.carservice.persistence.ServiceRepository;
import com.solvd.carservice.persistence.impl.ServiceRepositoryImpl;
import com.solvd.carservice.service.ServiceService;
import java.util.List;
import java.util.Optional;

public class ServiceServiceImpl implements ServiceService {
    private final ServiceRepository serviceRepository;

    public ServiceServiceImpl() {
        this.serviceRepository = new ServiceRepositoryImpl();
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
    public void change(Service service, String field) {
        serviceRepository.update(service, field);
    }
    @Override
    public void removeById(Long id) {
        serviceRepository.deleteById(id);
    }
}
