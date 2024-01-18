package com.solvd.carservice.domain.controller.admin;

import com.solvd.carservice.domain.entity.Car;
import com.solvd.carservice.domain.entity.Detail;
import com.solvd.carservice.domain.entity.Service;
import com.solvd.carservice.service.CarService;
import com.solvd.carservice.service.impl.CarServiceImpl;
import java.util.Optional;

public class CarController extends AbstractController {

    public void add() {
        Car car = new Car(
                getDataFromConsole.getStringFromConsole("brand"),
                getDataFromConsole.getStringFromConsole("model"),
                getDataFromConsole.getIntegerFromConsole("year"));
        CarService carService = new CarServiceImpl();
        carService.add(car);
        viewCar.added(car);
    }
    public void retrieveAll() {
        viewCar.showAll();
        for (Car car : new CarServiceImpl().retrieveAll()) {
            viewCar.show(car);
            for (Service service : car.getServices()) {
                viewService.show(service);
            }
            for (Detail detail : car.getDetails()) {
                viewDetail.show(detail);
            }
        }
    }
    public void change() {
        viewCar.update();
        Optional<Car> car = retrieveById();
        CarService carService = new CarServiceImpl();
        String field = getDataFromConsole.getStringFromConsole("select field");
        switch (field) {
            case "brand":
                car.get().setBrand(getDataFromConsole.getStringFromConsole("brand"));
                break;
            case "model":
                car.get().setModel(getDataFromConsole.getStringFromConsole("model"));
                break;
            case "year":
                car.get().setYear(getDataFromConsole.getIntegerFromConsole("year"));
                break;
        }
        carService.change(car, field);
        viewCar.updated(field);
    }
    public Optional<Car> retrieveById() {
        Optional<Car> carOptional = new CarServiceImpl().retrieveById(
                (getDataFromConsole.getLongFromConsole("id")));
        viewCar.showById(carOptional);
        for (Service service : carOptional.get().getServices()) {
            viewService.showById(Optional.ofNullable(service));
        }
        for (Detail detail : carOptional.get().getDetails()) {
            viewDetail.showById(Optional.ofNullable(detail));
        }
        return carOptional;
    }
    public void removeById() {
        viewCar.delete();
        CarService carService = new CarServiceImpl();
        carService.removeById(
                getDataFromConsole.getLongFromConsole("id"));
        viewCar.successfulDeleted();
    }
}