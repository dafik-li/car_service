package com.solvd.carservice.domain.controller.admin;

import com.solvd.carservice.domain.controller.Generator;
import com.solvd.carservice.domain.entity.Car;
import com.solvd.carservice.domain.exception.AuthorizationException;
import com.solvd.carservice.domain.exception.TableException;
import com.solvd.carservice.domain.view.ViewCar;
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
    private final CarServiceImpl carServiceImpl;

    public CarController() {
        this.carServiceImpl = new CarServiceImpl();
        this.viewCar = new ViewCar();
    }
    public void moderate() {
        viewConsoleMenu.chooseModerateMenu();
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
        viewConsoleMenu.chooseInsertMethod();
        String menu = scanner.nextLine();
        switch (menu) {
            case "1": selectXmlParser(); break;
            case "2": add(); break;
            case "3": parser.addCar(menu);
            case "0": moderate(); break;
        }
        try {
            validator.validateStartPageMenu(menu);
        } catch (AuthorizationException e) {
            LOGGER.error(e.toString());
            selectInsertMethod();
        }
    }
    public void selectXmlParser() {
        viewConsoleMenu.chooseXmlParser();
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
        ((CarService) carServiceImpl).add(car);
        viewCar.added(car);
    }
    public void retrieveAll() {
        viewCar.showAll();
        for (Car car : carServiceImpl.retrieveAll()) {
            viewCar.show(car);
        }
    }
    public void change() {
        viewCar.update();
        Optional<Car> car = retrieveById();
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
        ((CarService) carServiceImpl).change(car, field);
        viewCar.updated(field);
    }
    public Optional<Car> retrieveById() {
        Optional<Car> carOptional = carServiceImpl.retrieveById(
                (getDataFromConsole.getLongFromConsole("id")));
        viewCar.showById(carOptional);
        return carOptional;
    }
    public void removeById() {
        viewCar.delete();
        ((CarService) carServiceImpl).removeById(
                getDataFromConsole.getLongFromConsole("id"));
        viewCar.successfulDeleted();
    }
}