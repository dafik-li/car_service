package com.solvd.carservice.persistence.impl;

import com.solvd.carservice.domain.Detail;
import com.solvd.carservice.persistence.DetailRepository;
import java.util.List;
import java.util.Optional;

public class DetailRepositoryImpl implements DetailRepository {

    @Override
    public List<Detail> getByName(String name) {
        return null;
    }

    @Override
    public List<Detail> getByInStock(Boolean inStock) {
        return null;
    }

    @Override
    public List<Detail> getByDeliveryDays(Integer deliveryDays) {
        return null;
    }

    @Override
    public void create(Detail Entity) {

    }

    @Override
    public List<Detail> getAll() {
        return null;
    }

    @Override
    public Optional<Detail> getById(Long id) {
        return Optional.empty();
    }

    @Override
    public void update(Detail Entity) {

    }

    @Override
    public void deleteById(Long id) {

    }
}
