package com.solvd.carservice.domain.controller.admin;

import com.solvd.carservice.domain.controller.Generator;
import com.solvd.carservice.domain.entity.Car;
import com.solvd.carservice.domain.entity.Detail;
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
        consoleMenu.chooseModerateMenu();
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
        display.addedDetail(detail);
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
        LOGGER.info("Update detail");
        Optional<Detail> detail = retrieveById();
        DetailService detailService = new DetailServiceImpl();
        String field = getDataFromConsole.getStringFromConsole("select field");
        switch (field) {
            case "name":
                detail.get().setName(getDataFromConsole.getStringFromConsole("name"));
                break;
            case "price":
                detail.get().setPrice(getDataFromConsole.getIntegerFromConsole("price"));
                break;
            case "in_stock":
                detail.get().setInStock(getDataFromConsole.getBooleanFromConsole("in_stock"));
                break;
            case "delivery_days":
                detail.get().setDeliveryDays(getDataFromConsole.getIntegerFromConsole("delivery_days"));
                break;
        }
        detailService.change(detail, field);
        LOGGER.info("Client " + field + " was changed");
    }
    public Optional<Detail> retrieveById() {
        Optional<Detail> detailOptional = new DetailServiceImpl().retrieveById(
                (getDataFromConsole.getLongFromConsole("id")));
        LOGGER.info("\n|" +
                "Detail id - " + detailOptional.get().getId() + "|" +
                "name - " + detailOptional.get().getName() + "|" +
                "price - " + detailOptional.get().getPrice() + "\n[" +
                "car id - " + detailOptional.get().getCarId().getId() + "|" +
                "brand - " + detailOptional.get().getCarId().getBrand() + "|" +
                "model - " + detailOptional.get().getCarId().getModel() + "|" +
                "year - " + detailOptional.get().getCarId().getYear() + "]\n" +
                "in stock - " + detailOptional.get().getInStock() + "|" +
                "delivery days - " + detailOptional.get().getDeliveryDays() + "|");
        return detailOptional;
    }
    public void removeById() {
        LOGGER.info("Following detail will be deleted");
        DetailService detailService = new DetailServiceImpl();
        detailService.removeById(
                getDataFromConsole.getLongFromConsole("id"));
        LOGGER.info("Successful deleted");
    }
}
