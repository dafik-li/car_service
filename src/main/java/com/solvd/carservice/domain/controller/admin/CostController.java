package com.solvd.carservice.domain.controller.admin;

import com.solvd.carservice.domain.controller.Generator;
import com.solvd.carservice.domain.entity.Cost;
import com.solvd.carservice.domain.entity.Detail;
import com.solvd.carservice.domain.entity.Service;
import com.solvd.carservice.domain.exception.AuthorizationException;
import com.solvd.carservice.domain.exception.TableException;
import com.solvd.carservice.service.CostService;
import com.solvd.carservice.service.impl.CostServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import java.util.Optional;

public class CostController extends AbstractController {
    static {
        System.setProperty("log4j.configurationFile", "log4j2.xml");
    }
    private final static Logger LOGGER = (Logger) LogManager.getLogger(CarController.class);

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
    }public void selectInsertMethod() {
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
            case "1": staxParser.addCost(); break;
            case "2": jaxbParser.addCost(); break;
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
        Cost cost = new Cost(
                getDataFromConsole.getDoubleFromConsole("cost"),
                new Service(
                        getDataFromConsole.getLongFromConsole("service")),
                new Detail(
                        getDataFromConsole.getLongFromConsole("detail")));
        CostService costService = new CostServiceImpl();
        costService.add(cost);
        display.addedCost(cost);
    }
    public void retrieveAll() {
        LOGGER.info("List of costs");
        for (Cost cost : new CostServiceImpl().retrieveAll()) {
            LOGGER.info(
                    "Cost id - " + cost.getId() + "|" +
                    "cost - " + cost.getCost() + "[" +
                    "service id - " + cost.getServiceId().getId() + "|" +
                    "name - " + cost.getServiceId().getName() + "|" +
                    "price - " + cost.getServiceId().getPrice() + "|" +
                    "hours to do - " + cost.getServiceId().getHoursToDo() + "][" +
                    "car id - " + cost.getServiceId().getCarId().getId() + "|" +
                    "brand - " + cost.getServiceId().getCarId().getBrand() + "|" +
                    "model - " + cost.getServiceId().getCarId().getModel() + "|" +
                    "year - " + cost.getServiceId().getCarId().getYear() + "][" +
                    "department id - " + cost.getServiceId().getDepartmentId().getId() + "|" +
                    "name - " + cost.getServiceId().getDepartmentId().getName() + "][" +
                    "company id - " + cost.getServiceId().getDepartmentId().getCompanyId().getId() + "|" +
                    "name - " + cost.getServiceId().getDepartmentId().getCompanyId().getName() + "|" +
                    "address - " + cost.getServiceId().getDepartmentId().getCompanyId().getAddress() + "][" +
                    "detail id - " + cost.getDetailId().getId() + "|" +
                    "name - " + cost.getDetailId().getName() + "|" +
                    "price - " + cost.getDetailId().getPrice() + "|" +
                    "in stock - " + cost.getDetailId().getInStock() + "|" +
                    "delivery days - " + cost.getDetailId().getDeliveryDays() + "]");
        }
    }
    public void change() {
        LOGGER.info("Update cost");
        Optional<Cost> cost = retrieveById();
        CostService costService = new CostServiceImpl();
        String field = getDataFromConsole.getStringFromConsole("select field");
        switch (field) {
            case "cost":
                cost.get().setCost(getDataFromConsole.getDoubleFromConsole("cost"));
                break;
        }
        costService.change(cost, field);
        LOGGER.info("Cost " + field + " was changed");
    }
    public Optional<Cost> retrieveById() {
        Optional<Cost> costOptional = new CostServiceImpl().retrieveById(
                (getDataFromConsole.getLongFromConsole("id")));
        LOGGER.info(
                "Cost id - " + costOptional.get().getId() + "|" +
                "cost - " + costOptional.get().getCost() + "[" +
                "service id - " + costOptional.get().getServiceId().getId() + "|" +
                "name - " + costOptional.get().getServiceId().getName() + "|" +
                "price - " + costOptional.get().getServiceId().getPrice() + "|" +
                "hours to do - " + costOptional.get().getServiceId().getHoursToDo() + "][" +
                "car id - " + costOptional.get().getServiceId().getCarId().getId() + "|" +
                "brand - " + costOptional.get().getServiceId().getCarId().getBrand() + "|" +
                "model - " + costOptional.get().getServiceId().getCarId().getModel() + "|" +
                "year - " + costOptional.get().getServiceId().getCarId().getYear() + "][" +
                "department id - " + costOptional.get().getServiceId().getDepartmentId().getId() + "|" +
                "name - " + costOptional.get().getServiceId().getDepartmentId().getName() + "][" +
                "company id - " + costOptional.get().getServiceId().getDepartmentId().getCompanyId().getId() + "|" +
                "name - " + costOptional.get().getServiceId().getDepartmentId().getCompanyId().getName() + "|" +
                "address - " + costOptional.get().getServiceId().getDepartmentId().getCompanyId().getAddress() + "][" +
                "detail id - " + costOptional.get().getDetailId().getId() + "|" +
                "name - " + costOptional.get().getDetailId().getName() + "|" +
                "price - " + costOptional.get().getDetailId().getPrice() + "|" +
                "in stock - " + costOptional.get().getDetailId().getInStock() + "|" +
                "delivery days - " + costOptional.get().getDetailId().getDeliveryDays() + "]");
        return costOptional;
    }
    public void removeById() {
        LOGGER.info("Following cost will be deleted");
        CostService costService = new CostServiceImpl();
        costService.removeById(
                getDataFromConsole.getLongFromConsole("id"));
        LOGGER.info("Successful deleted");
    }
}
