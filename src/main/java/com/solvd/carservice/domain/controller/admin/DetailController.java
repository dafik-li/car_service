package com.solvd.carservice.domain.controller.admin;

import com.solvd.carservice.domain.entity.Car;
import com.solvd.carservice.domain.entity.Detail;
import com.solvd.carservice.domain.controller.Generator;
import com.solvd.carservice.domain.exception.TableException;
import com.solvd.carservice.service.DetailService;
import com.solvd.carservice.service.impl.DetailServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import java.util.Optional;

public class DetailController extends AbstractController {
    static {
        System.setProperty("log4j.configurationFile", "log4j2.xml");
    }
    private final static Logger LOGGER = (Logger) LogManager.getLogger(CarController.class);

    public void moderate() {
        consoleMenu.chooseActionMenu();
        String menu = scanner.nextLine();
        switch (menu) {
            case "1": add(); break;
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
    public void add() {
        Detail detail = new Detail(
                getDataFromConsole.getStringFromConsole("name"),
                getDataFromConsole.getIntegerFromConsole("price"),
                new Car(
                        getDataFromConsole.getLongFromConsole("car")),
                getDataFromConsole.getBooleanFromConsole("in stock"),
                getDataFromConsole.getIntegerFromConsole("delivery days"));
        DetailService detailService = new DetailServiceImpl();
        detailService.add(detail);
        LOGGER.info(
                "Detail - "
                + detail.getName() +
                " - was added");
    }
    public void retrieveAll() {
        LOGGER.info("List of details");
        for (Detail detail : new DetailServiceImpl().retrieveAll()) {
            LOGGER.info(
                    "Detail id - " + detail.getId() + "|" +
                    "name - " + detail.getName() + "|" +
                    "price - " + detail.getPrice() + "[" +
                    "car id - " + detail.getCarId().getId() + "|" +
                    "brand - " + detail.getCarId().getBrand() + "|" +
                    "model - " + detail.getCarId().getModel() + "|" +
                    "year - " + detail.getCarId().getYear() + "]" +
                    "in stock - " + detail.getInStock() + "|" +
                    "delivery days - " + detail.getDeliveryDays());
        }
    }
    public void change() {
    }
    public void retrieveById() {
        Optional<Detail> detailOptional = new DetailServiceImpl().retrieveById(
                (getDataFromConsole.getLongFromConsole("id")));
        LOGGER.info(
                "Detail id - " + detailOptional.get().getId() + "|" +
                "name - " + detailOptional.get().getName() + "|" +
                "price - " + detailOptional.get().getPrice() + "|" +
                "car - " + detailOptional.get().getCarId() + "|" +
                "in stock - " + detailOptional.get().getInStock() + "|" +
                "delivery days - " + detailOptional.get().getDeliveryDays());
    }
    public void removeById() {
        LOGGER.info("Following detail will be deleted");
        DetailService detailService = new DetailServiceImpl();
        detailService.removeById(
                getDataFromConsole.getLongFromConsole("id"));
    }
}
