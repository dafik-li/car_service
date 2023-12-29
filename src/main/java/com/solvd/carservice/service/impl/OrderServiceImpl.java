package com.solvd.carservice.service.impl;

import com.solvd.carservice.domain.entity.Order;
import com.solvd.carservice.persistence.OrderRepository;
import com.solvd.carservice.persistence.DAOimpl.OrderRepositoryImpl;
import com.solvd.carservice.service.OrderService;
import java.util.List;
import java.util.Optional;

public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    public OrderServiceImpl() {
        this.orderRepository = new OrderRepositoryImpl();
    }

    @Override
    public Order add(Order order) {
        order.setId(null);
        orderRepository.create(order);
        return order;
    }
    @Override
    public List<Order> retrieveAll() {
        return orderRepository.getAll();
    }
    @Override
    public Optional<Order> retrieveById(Long id) {
        return orderRepository.getById(id);
    }
    @Override
    public void change(Order order, String field) {
        orderRepository.update(order, field);
    }
    @Override
    public void removeById(Long id) {
        orderRepository.deleteById(id);
    }
}
