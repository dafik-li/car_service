package com.solvd.carservice.domain.controller.admin;

import com.solvd.carservice.domain.entity.Car;
import com.solvd.carservice.domain.entity.Cost;
import com.solvd.carservice.domain.entity.Detail;
import com.solvd.carservice.service.DetailService;
import com.solvd.carservice.service.impl.DetailServiceImpl;
import java.util.Optional;

public class DetailController extends AbstractController {

    public void add() {
        Detail detail = new Detail(
                getDataFromConsole.getString("name"),
                getDataFromConsole.getInteger("price"),
                new Car(
                        getDataFromConsole.getLong("car")),
                getDataFromConsole.getBoolean("in stock"),
                getDataFromConsole.getInteger("delivery days"));
        DetailService detailService = new DetailServiceImpl();
        detailService.add(detail);
        viewDetail.added(detail);
    }
    public void retrieveAll() {
        viewDetail.showAll();
        for (Detail detail : new DetailServiceImpl().retrieveAll()) {
            viewDetail.show(detail);
            for (Cost cost : detail.getCosts()) {
                viewCost.show(cost);
            }
        }
    }
    public void change() {
        viewDetail.update();
        Optional<Detail> detail = retrieveById();
        DetailService detailService = new DetailServiceImpl();
        String field = getDataFromConsole.getString("select field");
        switch (field) {
            case "name":
                detail.get().setName(getDataFromConsole.getString("name"));
                break;
            case "price":
                detail.get().setPrice(getDataFromConsole.getInteger("price"));
                break;
            case "in_stock":
                detail.get().setInStock(getDataFromConsole.getBoolean("in_stock"));
                break;
            case "delivery_days":
                detail.get().setDeliveryDays(getDataFromConsole.getInteger("delivery_days"));
                break;
        }
        detailService.change(detail, field);
        viewDetail.updated(field);
    }
    public Optional<Detail> retrieveById() {
        Optional<Detail> detailOptional = new DetailServiceImpl().retrieveById(
                (getDataFromConsole.getLong("id")));
        viewDetail.showById(detailOptional);
        for (Cost cost : detailOptional.get().getCosts()) {
            viewCost.showById(Optional.ofNullable(cost));
        }
        return detailOptional;
    }
    public void removeById() {
        viewDetail.delete();
        DetailService detailService = new DetailServiceImpl();
        detailService.removeById(
                getDataFromConsole.getLong("id"));
        viewDetail.successfulDeleted();
    }
}
