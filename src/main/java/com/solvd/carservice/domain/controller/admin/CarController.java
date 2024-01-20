package com.solvd.carservice.domain.controller.admin;

import com.solvd.carservice.domain.entity.Car;
import com.solvd.carservice.domain.entity.Detail;
import com.solvd.carservice.domain.entity.Service;
import com.solvd.carservice.domain.view.admin.InterfaceView;
import com.solvd.carservice.domain.view.admin.ViewDetail;
import com.solvd.carservice.domain.view.admin.ViewService;
import com.solvd.carservice.service.InterfaceService;
import java.util.Optional;

public class CarController extends AbstractController<Car> {
    private final ViewService viewService;
    private final ViewDetail viewDetail;

    public CarController(InterfaceView<Car> view, InterfaceService<Car> service) {
        super(view, service);
        this.viewService = new ViewService();
        this.viewDetail = new ViewDetail();
    }
    public void add() {
        Car car = new Car(
                getDataFromConsole.getString("brand"),
                getDataFromConsole.getString("model"),
                getDataFromConsole.getInteger("year"));
        service.add(car);
        view.added(car);
    }
    public void retrieveAll() {
        view.showAll();
        for (Car car : service.retrieveAll()) {
            view.show(car);
            for (Service service : car.getServices()) {
                viewService.show(service);
            }
            for (Detail detail : car.getDetails()) {
                viewDetail.show(detail);
            }
        }
    }
    public void change() {
        view.update();
        Optional<Car> car = retrieveById();
        String field = getDataFromConsole.getString("select field");
        switch (field) {
            case "brand":
                car.get().setBrand(getDataFromConsole.getString("brand"));
                break;
            case "model":
                car.get().setModel(getDataFromConsole.getString("model"));
                break;
            case "year":
                car.get().setYear(getDataFromConsole.getInteger("year"));
                break;
        }
        service.change(car, field);
        view.updated(field);
    }
    public Optional<Car> retrieveById() {
        Optional<Car> carOptional = service.retrieveById(
                (getDataFromConsole.getLong("id")));
        view.showById(carOptional);
        for (Service service : carOptional.get().getServices()) {
            viewService.showById(Optional.ofNullable(service));
        }
        for (Detail detail : carOptional.get().getDetails()) {
            viewDetail.showById(Optional.ofNullable(detail));
        }
        return carOptional;
    }
}