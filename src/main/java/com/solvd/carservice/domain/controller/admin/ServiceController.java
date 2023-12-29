package com.solvd.carservice.domain.controller.admin;

import com.solvd.carservice.domain.Car;
import com.solvd.carservice.domain.Department;
import com.solvd.carservice.domain.Service;
import com.solvd.carservice.domain.controller.Generator;
import com.solvd.carservice.exception.TableException;
import com.solvd.carservice.service.ServiceService;
import com.solvd.carservice.service.impl.ServiceServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import java.util.Optional;

public class ServiceController extends AbstractController {
    static {
        System.setProperty("log4j.configurationFile", "log4j2.xml");
    }
    private final static Logger LOGGER = (Logger) LogManager.getLogger(ServiceController.class);

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
        Service service = new Service(
                getDataFromConsole.getStringFromConsole("name"),
                getDataFromConsole.getDoubleFromConsole("price"),
                getDataFromConsole.getIntegerFromConsole("hours to do"),
                new Car(
                        getDataFromConsole.getLongFromConsole("car")),
                new Department(
                        getDataFromConsole.getLongFromConsole("department")));
        ServiceService serviceService = new ServiceServiceImpl();
        serviceService.add(service);
        LOGGER.info(
                "Service - " +
                service.getName() +
                " - was added");
    }
    public void retrieveAll() {
        LOGGER.info("List of services");
        for (Service service : new ServiceServiceImpl().retrieveAll()) {
            LOGGER.info(
                    "Service id - " + service.getId() + "|" +
                    "name - " + service.getName() + "|" +
                    "price - " + service.getPrice() + "|" +
                    "hours to do - " + service.getHoursToDo() + "][" +
                    "car id - " + service.getCarId().getId() + "|" +
                    "brand - " + service.getCarId().getBrand() + "|" +
                    "model - " + service.getCarId().getModel() + "|" +
                    "year - " + service.getCarId().getYear() + "][" +
                    "department id - " + service.getDepartmentId().getId() + "|" +
                    "name - " + service.getDepartmentId().getName() + "][" +
                    "company id - " + service.getDepartmentId().getCompanyId().getId() + "|" +
                    "name - " + service.getDepartmentId().getCompanyId().getName() + "|" +
                    "address - " + service.getDepartmentId().getCompanyId().getAddress() + "]");
        }
    }
    public void change() {
    }
    public void retrieveById() {
        Optional<Service> serviceOptional = new ServiceServiceImpl().retrieveById(
                (getDataFromConsole.getLongFromConsole("id")));
        LOGGER.info(
                "Service id - " + serviceOptional.get().getId() + "|" +
                "name - "  + serviceOptional.get().getName() + "|" +
                "price - " + serviceOptional.get().getPrice() + "|" +
                "hours to do - " + serviceOptional.get().getHoursToDo() + "|" +
                "car - " + serviceOptional.get().getCarId() + "|" +
                "department - " + serviceOptional.get().getDepartmentId());
    }
    public void removeById() {
        LOGGER.info("Following company will be deleted");
        ServiceService serviceService = new ServiceServiceImpl();
        serviceService.removeById(
                getDataFromConsole.getLongFromConsole("id"));
    }
}
