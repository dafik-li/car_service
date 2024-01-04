package com.solvd.carservice.service.impl;

import com.solvd.carservice.domain.entity.Order;
import com.solvd.carservice.persistence.OrderRepository;
import com.solvd.carservice.service.OrderService;
import com.solvd.carservice.util.SwitcherRepository;
import java.util.List;
import java.util.Optional;

public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private static final SwitcherRepository switcherRepository = SwitcherRepository.getInstance();

    public OrderServiceImpl() {
        this.orderRepository = switcherRepository.switchRepository(OrderRepository.class);
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
    public void change(Optional<Order> order, String field) {
        orderRepository.update(order, field);
    }
    @Override
    public void removeById(Long id) {
        orderRepository.deleteById(id);
    }
}
