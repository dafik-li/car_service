package com.solvd.carservice.domain.controller.admin;

import com.solvd.carservice.domain.controller.Generator;
import com.solvd.carservice.domain.entity.Car;
import com.solvd.carservice.domain.entity.Detail;
import com.solvd.carservice.domain.entity.Service;
import com.solvd.carservice.domain.exception.AuthorizationException;
import com.solvd.carservice.domain.exception.TableException;
import com.solvd.carservice.domain.view.admin.ViewCar;
import com.solvd.carservice.domain.view.admin.ViewDetail;
import com.solvd.carservice.domain.view.admin.ViewService;
import com.solvd.carservice.service.CarService;
import com.solvd.carservice.service.impl.CarServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import java.util.Optional;

public class CarController extends AbstractController {
    static {
        System.setProperty("log4j.configurationFile", "log4j2.xml");
    }
    private final static Logger LOGGER = (Logger) LogManager.getLogger(CarController.class);
    private final ViewCar viewCar;
    private final ViewService viewService;
    private final ViewDetail viewDetail;

    public CarController() {
        this.viewCar = new ViewCar();
        this.viewService = new ViewService();
        this.viewDetail = new ViewDetail();
    }
    public void moderate() {
        viewConsoleAdminMenu.chooseModerateMenu();
        String menu = scanner.nextLine();
        switch (menu) {
            case "1": selectInsertMethod(); break;
            case "2": retrieveAll(); break;
            case "3": retrieveById(); break;
            case "4": change(); break;
            case "5": removeById(); break;
            case "0": new Generator().moderateController(); break;
        }
        try {
            validator.validateActionMenu(menu);
        } catch (TableException e) {
            LOGGER.error(e.toString());
            moderate();
        }
    }
    public void selectInsertMethod() {
        viewConsoleAdminMenu.chooseInsertMethod();
        String menu = scanner.nextLine();
        switch (menu) {
            case "1": selectXmlParser(); break;
            case "2": add(); break;
            case "3": parser.addCar(menu);
            case "0": moderate(); break;
        }
        try {
            validator.validateAuthorization(menu);
        } catch (AuthorizationException e) {
            LOGGER.error(e.toString());
            selectInsertMethod();
        }
    }
    public void selectXmlParser() {
        viewConsoleAdminMenu.chooseXmlParser();
        String menu = scanner.nextLine();
        switch (menu) {
            case "1":
            case "2":
                parser.addCar(menu); break;
            case "0": selectInsertMethod(); break;
        }
        try {
            validator.validateStartPageMenu(menu);
        } catch (AuthorizationException e) {
            LOGGER.error(e.toString());
            selectXmlParser();
        }
    }
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