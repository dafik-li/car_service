package com.solvd.carservice.domain.controller.admin;

import com.solvd.carservice.domain.entity.Cost;
import com.solvd.carservice.domain.entity.Detail;
import com.solvd.carservice.domain.entity.Order;
import com.solvd.carservice.domain.entity.Service;
import com.solvd.carservice.domain.view.admin.InterfaceView;
import com.solvd.carservice.domain.view.admin.ViewOrder;
import com.solvd.carservice.service.CostService;
import com.solvd.carservice.service.InterfaceService;
import com.solvd.carservice.service.impl.CostServiceImpl;
import java.util.Optional;

public class CostController extends AbstractController<Cost> {
    private final ViewOrder viewOrder;

    public CostController(InterfaceView<Cost> view, InterfaceService<Cost> service) {
        super(view, service);
        this.viewOrder = new ViewOrder();
    }
    public void add() {
        Cost cost = new Cost(
                getDataFromConsole.getDouble("cost"),
                new Service(
                        getDataFromConsole.getLong("service")),
                new Detail(
                        getDataFromConsole.getLong("detail")));
        service.add(cost);
        view.added(cost);
    }
    public void retrieveAll() {
        view.showAll();
        for (Cost cost : service.retrieveAll()) {
            view.show(cost);
            for (Order order : cost.getOrders()) {
                viewOrder.show(order);
            }
        }
    }
    public void change() {
        view.update();
        Optional<Cost> cost = retrieveById();
        String field = getDataFromConsole.getString("select field");
        switch (field) {
            case "cost":
                cost.get().setCost(getDataFromConsole.getDouble("cost"));
                break;
        }
        service.change(cost, field);
        view.updated(field);
    }
    public Optional<Cost> retrieveById() {
        Optional<Cost> costOptional = service.retrieveById(
                (getDataFromConsole.getLong("id")));
        view.showById(costOptional);
        for (Order order : costOptional.get().getOrders()) {
            viewOrder.showById(Optional.ofNullable(order));
        }
        return costOptional;
    }
}
