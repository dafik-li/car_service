package com.solvd.carservice.persistence.impl;

import com.solvd.carservice.domain.Service;
import com.solvd.carservice.persistence.ServiceRepository;

import java.util.List;
import java.util.Optional;

public class ServiceRepositoryImpl implements ServiceRepository {

    @Override
    public List<Service> findByName(String name) {
        return null;
    }

    @Override
    public List<Service> findByPrice(Double price) {
        return null;
    }

    @Override
    public void create(Service Entity) {

    }

    @Override
    public List<Service> findAll() {
        return null;
    }

    @Override
    public Optional<Service> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public void update(Service Entity) {

    }

    @Override
    public void deleteById(Long id) {

    }
}
