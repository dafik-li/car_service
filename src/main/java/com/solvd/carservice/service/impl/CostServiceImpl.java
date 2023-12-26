package com.solvd.carservice.service.impl;

import com.solvd.carservice.domain.Cost;
import com.solvd.carservice.persistence.CostRepository;
import com.solvd.carservice.persistence.impl.CostRepositoryImpl;
import com.solvd.carservice.service.CostService;
import java.util.List;
import java.util.Optional;

public class CostServiceImpl implements CostService {
    private final CostRepository costRepository;

    public CostServiceImpl() {
        this.costRepository = new CostRepositoryImpl();
    }
    @Override
    public Cost add(Cost cost) {
        cost.setId(null);
        costRepository.create(cost);
        return cost;
    }
    @Override
    public List<Cost> retrieveAll() {
        return costRepository.getAll();
    }
    @Override
    public Optional<Cost> retrieveById(Long id) {
        return costRepository.getById(id);
    }
    @Override
    public void change(Cost cost, String field) {
        costRepository.update(cost, field);
    }
    @Override
    public void removeById(Long id) {
        costRepository.deleteById(id);
    }
}
