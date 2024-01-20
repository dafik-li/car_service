package com.solvd.carservice.domain.controller.admin;

import com.solvd.carservice.domain.entity.Client;
import com.solvd.carservice.domain.entity.Cost;
import com.solvd.carservice.domain.entity.Order;
import com.solvd.carservice.domain.view.admin.InterfaceView;
import com.solvd.carservice.service.InterfaceService;
import com.solvd.carservice.service.impl.OrderServiceImpl;
import java.util.Optional;

public class OrderController extends AbstractController<Order> {

    public OrderController(InterfaceView<Order> view, InterfaceService<Order> service) {
        super(view, service);
    }

    public void add() {
        Order order = new Order(
                getDataFromConsole.getDate("date"),
                new Client(
                        getDataFromConsole.getLong("client")),
                new Cost(
                        getDataFromConsole.getLong("cost")));
        service.add(order);
        view.added(order);
    }
    public void retrieveAll() {
        view.showAll();
        for (Order order : service.retrieveAll()) {
            view.show(order);
        }
    }
    public void change() {
        view.update();
        Optional<Order> order = retrieveById();
        String field = getDataFromConsole.getString("select field");
        switch (field) {
            case "date":
                order.get().setDate(getDataFromConsole.getDate("date"));
                break;
        }
        service.change(order, field);
        view.updated(field);
    }
    public Optional<Order> retrieveById() {
        Optional<Order> orderOptional = service.retrieveById(
                (getDataFromConsole.getLong("id")));
        view.showById(orderOptional);
        return orderOptional;
    }
}
