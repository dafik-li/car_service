package com.solvd.carservice.persistence.impl;

import com.solvd.carservice.domain.Order;
import com.solvd.carservice.persistence.OrderRepository;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

public class OrderRepositoryImpl implements OrderRepository{

    @Override
    public List<Order> getByDate(Date date) {
        return null;
    }

    @Override
    public void create(Order Entity) {

    }

    @Override
    public List<Order> getAll() {
        return null;
    }

    @Override
    public Optional<Order> getById(Long id) {
        return Optional.empty();
    }

    @Override
    public void update(Order Entity) {

    }

    @Override
    public void deleteById(Long id) {

    }
}
