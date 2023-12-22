package com.solvd.carservice.persistence.impl;

import com.solvd.carservice.domain.Cost;
import com.solvd.carservice.persistence.CostRepository;

import java.util.List;
import java.util.Optional;

public class CostRepositoryImpl implements CostRepository {

    @Override
    public List<Cost> findByCost(Double cost) {
        return null;
    }

    @Override
    public void create(Cost Entity) {

    }

    @Override
    public List<Cost> findAll() {
        return null;
    }

    @Override
    public Optional<Cost> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public void update(Cost Entity) {

    }

    @Override
    public void deleteById(Long id) {

    }
}
