package com.solvd.carservice.domain.controller.admin;

import com.solvd.carservice.domain.controller.Generator;
import com.solvd.carservice.domain.entity.Car;
import com.solvd.carservice.domain.exception.AuthorizationException;
import com.solvd.carservice.domain.exception.TableException;
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
    private final CarServiceImpl carServiceImpl;

    public CarController() {
        this.carServiceImpl = new CarServiceImpl();
    }
    public void moderate() {
        consoleMenu.chooseModerateMenu();
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
        consoleMenu.chooseInsertMethod();
        String menu = scanner.nextLine();
        switch (menu) {
            case "1": selectXmlParser(); break;
            case "2": add(); break;
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
        consoleMenu.chooseXmlParser();
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
        display.addedCar(car);
    }
    public void retrieveAll() {
        LOGGER.info("List of cars");
        for (Car car : carServiceImpl.retrieveAll()) {
            LOGGER.info(
                    "Car id - " + car.getId() + "|" +
                    "brand - " + car.getBrand() + "|" +
                    "model - " + car.getModel() + "|" +
                    "year - " + car.getYear());
        }
    }
    public void change() {
        LOGGER.info("Update car");
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
        LOGGER.info("Car " + field + " was changed");
    }
    public Optional<Car> retrieveById() {
        Optional<Car> carOptional = carServiceImpl.retrieveById(
                (getDataFromConsole.getLongFromConsole("id")));
        LOGGER.info("|" +
                "Car id - " + carOptional.get().getId() + "|" +
                "brand - " + carOptional.get().getBrand() + "|" +
                "model - " + carOptional.get().getModel() + "|" +
                "year - " + carOptional.get().getYear()+ "|");
        return carOptional;
    }
    public void removeById() {
        LOGGER.info("Following car will be deleted");
        ((CarService) carServiceImpl).removeById(
                getDataFromConsole.getLongFromConsole("id"));
        LOGGER.info("Successful deleted");
    }
}