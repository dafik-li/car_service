package com.solvd.carservice.service.impl;

import com.solvd.carservice.domain.entity.Cost;
import com.solvd.carservice.persistence.CostRepository;
import com.solvd.carservice.service.CostService;
import com.solvd.carservice.util.SwitcherRepository;
import java.util.List;
import java.util.Optional;

public class CostServiceImpl implements CostService {
    private final CostRepository costRepository;
    private static final SwitcherRepository switcherRepository = SwitcherRepository.getInstance();

    public CostServiceImpl() {
        this.costRepository = switcherRepository.switchRepository(CostRepository.class);
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
    public void change(Optional<Cost> cost, String field) {
        costRepository.update(cost, field);
    }
    @Override
    public void removeById(Long id) {
        costRepository.deleteById(id);
    }
}
