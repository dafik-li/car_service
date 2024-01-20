package com.solvd.carservice.domain.controller.admin;

import com.solvd.carservice.domain.entity.Car;
import com.solvd.carservice.domain.entity.Cost;
import com.solvd.carservice.domain.entity.Detail;
import com.solvd.carservice.domain.view.admin.InterfaceView;
import com.solvd.carservice.domain.view.admin.ViewCost;
import com.solvd.carservice.service.DetailService;
import com.solvd.carservice.service.InterfaceService;
import com.solvd.carservice.service.impl.DetailServiceImpl;
import java.util.Optional;

public class DetailController extends AbstractController<Detail> {
    private final ViewCost viewCost;

    public DetailController(InterfaceView<Detail> view, InterfaceService<Detail> service) {
        super(view, service);
        this.viewCost = new ViewCost();
    }
    public void add() {
        Detail detail = new Detail(
                getDataFromConsole.getString("name"),
                getDataFromConsole.getInteger("price"),
                new Car(
                        getDataFromConsole.getLong("car")),
                getDataFromConsole.getBoolean("in stock"),
                getDataFromConsole.getInteger("delivery days"));
        service.add(detail);
        view.added(detail);
    }
    public void retrieveAll() {
        view.showAll();
        for (Detail detail : service.retrieveAll()) {
            view.show(detail);
            for (Cost cost : detail.getCosts()) {
                viewCost.show(cost);
            }
        }
    }
    public void change() {
        view.update();
        Optional<Detail> detail = retrieveById();
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
        service.change(detail, field);
        view.updated(field);
    }
    public Optional<Detail> retrieveById() {
        Optional<Detail> detailOptional = service.retrieveById(
                (getDataFromConsole.getLong("id")));
        view.showById(detailOptional);
        for (Cost cost : detailOptional.get().getCosts()) {
            viewCost.showById(Optional.ofNullable(cost));
        }
        return detailOptional;
    }
}
