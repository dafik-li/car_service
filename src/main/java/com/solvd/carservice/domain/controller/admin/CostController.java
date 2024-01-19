package com.solvd.carservice.domain.controller.admin;

import com.solvd.carservice.domain.entity.Cost;
import com.solvd.carservice.domain.entity.Detail;
import com.solvd.carservice.domain.entity.Order;
import com.solvd.carservice.domain.entity.Service;
import com.solvd.carservice.service.CostService;
import com.solvd.carservice.service.impl.CostServiceImpl;
import java.util.Optional;

public class CostController extends AbstractController {

    public void add() {
        Cost cost = new Cost(
                getDataFromConsole.getDouble("cost"),
                new Service(
                        getDataFromConsole.getLong("service")),
                new Detail(
                        getDataFromConsole.getLong("detail")));
        CostService costService = new CostServiceImpl();
        costService.add(cost);
        viewCost.added(cost);
    }
    public void retrieveAll() {
        viewCost.showAll();
        for (Cost cost : new CostServiceImpl().retrieveAll()) {
            viewCost.show(cost);
            for (Order order : cost.getOrders()) {
                viewOrder.show(order);
            }
        }
    }
    public void change() {
        viewCost.update();
        Optional<Cost> cost = retrieveById();
        CostService costService = new CostServiceImpl();
        String field = getDataFromConsole.getString("select field");
        switch (field) {
            case "cost":
                cost.get().setCost(getDataFromConsole.getDouble("cost"));
                break;
        }
        costService.change(cost, field);
        viewCost.updated(field);
    }
    public Optional<Cost> retrieveById() {
        Optional<Cost> costOptional = new CostServiceImpl().retrieveById(
                (getDataFromConsole.getLong("id")));
        viewCost.showById(costOptional);
        for (Order order : costOptional.get().getOrders()) {
            viewOrder.showById(Optional.ofNullable(order));
        }
        return costOptional;
    }
    public void removeById() {
        viewCost.delete();
        CostService costService = new CostServiceImpl();
        costService.removeById(
                getDataFromConsole.getLong("id"));
        viewCost.successfulDeleted();
    }
}
