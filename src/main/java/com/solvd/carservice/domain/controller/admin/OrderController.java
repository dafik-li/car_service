package com.solvd.carservice.domain.controller.admin;

import com.solvd.carservice.domain.entity.Client;
import com.solvd.carservice.domain.entity.Cost;
import com.solvd.carservice.domain.entity.Order;
import com.solvd.carservice.service.OrderService;
import com.solvd.carservice.service.impl.OrderServiceImpl;
import java.util.Optional;

public class OrderController extends AbstractController {

    public void add() {
        Order order = new Order(
                getDataFromConsole.getDate("date"),
                new Client(
                        getDataFromConsole.getLong("client")),
                new Cost(
                        getDataFromConsole.getLong("cost")));
        OrderService orderService = new OrderServiceImpl();
        orderService.add(order);
        viewOrder.added(order);
    }
    public void retrieveAll() {
        viewOrder.showAll();
        for (Order order : new OrderServiceImpl().retrieveAll()) {
            viewOrder.show(order);
        }
    }
    public void change() {
        viewOrder.update();
        Optional<Order> order = retrieveById();
        OrderService orderService = new OrderServiceImpl();
        String field = getDataFromConsole.getString("select field");
        switch (field) {
            case "date":
                order.get().setDate(getDataFromConsole.getDate("date"));
                break;
        }
        orderService.change(order, field);
        viewOrder.updated(field);
    }
    public Optional<Order> retrieveById() {
        Optional<Order> orderOptional = new OrderServiceImpl().retrieveById(
                (getDataFromConsole.getLong("id")));
        viewOrder.showById(orderOptional);
        return orderOptional;
    }
    public void removeById() {
        viewOrder.delete();
        OrderService orderService = new OrderServiceImpl();
        orderService.removeById(
                getDataFromConsole.getLong("id"));
        viewOrder.successfulDeleted();
    }
}
