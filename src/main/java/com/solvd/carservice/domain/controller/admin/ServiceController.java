package com.solvd.carservice.domain.controller.admin;

import com.solvd.carservice.domain.controller.Generator;
import com.solvd.carservice.domain.entity.Car;
import com.solvd.carservice.domain.entity.Department;
import com.solvd.carservice.domain.entity.Employee;
import com.solvd.carservice.domain.entity.Service;
import com.solvd.carservice.domain.exception.AuthorizationException;
import com.solvd.carservice.domain.exception.TableException;
import com.solvd.carservice.domain.parser.Parser;
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
        consoleMenu.chooseModerateService();
        String menu = scanner.nextLine();
        switch (menu) {
            case "1": selectInsertMethod(); break;
            case "2": retrieveAll(); break;
            case "3": retrieveById(); break;
            case "4": change(); break;
            case "5": removeById(); break;
            case "6": addEmployee(); break;
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
            case "1": Parser.addService(); break;
            case "2": add(); break;
            case "0": moderate(); break;
        }
        try {
            validator.validateStartPageMenu(menu);
        } catch (AuthorizationException e) {
            LOGGER.error(e.toString());
            add();
        }
    }
    public void addEmployee() {
        LOGGER.info("Add employee to service");
        ServiceService serviceService = new ServiceServiceImpl();
        Long serviceId = getDataFromConsole.getLongFromConsole("service_id");
        Long employeeId = getDataFromConsole.getLongFromConsole("employee_id");
        serviceService.addEmployee(employeeId, serviceId);
        LOGGER.info("Employee id - " + employeeId + " was assigned to the service id - " + serviceId);
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
                    "hours to do - " + service.getHoursToDo() + "[" +
                    "car id - " + service.getCarId().getId() + "|" +
                    "brand - " + service.getCarId().getBrand() + "|" +
                    "model - " + service.getCarId().getModel() + "|" +
                    "year - " + service.getCarId().getYear() + "][");
            for (Employee employee : service.getEmployees()) {
                LOGGER.info(
                        "employee id - " + employee.getId() + "|" +
                        "name - " + employee.getName() + "|" +
                        "surname - " + employee.getSurname() + "|" +
                        "age - " + employee.getAge() + "|" +
                        "position - " + employee.getPosition() + "|" +
                        "level - " + employee.getLevel() + "|" +
                        "salary - " + employee.getSalary() + "|" +
                        "phone - " + employee.getPhoneNumber() + "][" +
                        "department id - " + service.getDepartmentId().getId() + "|" +
                        "name - " + service.getDepartmentId().getName() + "][" +
                        "company id - " + service.getDepartmentId().getCompanyId().getId() + "|" +
                        "name - " + service.getDepartmentId().getCompanyId().getName() + "|" +
                        "address - " + service.getDepartmentId().getCompanyId().getAddress() + "]");
            }
        }
    }
    public void change() {
        LOGGER.info("Update service");
        Optional<Service> service = retrieveById();
        ServiceService serviceService = new ServiceServiceImpl();
        String field = getDataFromConsole.getStringFromConsole("select field");
        switch (field) {
            case "name":
                service.get().setName(getDataFromConsole.getStringFromConsole("name"));
                break;
            case "price":
                service.get().setPrice(getDataFromConsole.getDoubleFromConsole("price"));
                break;
            case "hours_to_do":
                service.get().setHoursToDo(getDataFromConsole.getIntegerFromConsole("hours_to_do"));
                break;
        }
        serviceService.change(service, field);
        LOGGER.info("Client " + field + " was changed");
    }
    public Optional<Service> retrieveById() {
        Optional<Service> serviceOptional = new ServiceServiceImpl().retrieveById(
                (getDataFromConsole.getLongFromConsole("id")));
        LOGGER.info("\n|" +
                "Service id - " + serviceOptional.get().getId() + "|" +
                "name - "  + serviceOptional.get().getName() + "|" +
                "price - " + serviceOptional.get().getPrice() + "|" +
                "hours to do - " + serviceOptional.get().getHoursToDo() + "[" +
                "car id - " + serviceOptional.get().getCarId().getId() + "|" +
                "brand - " + serviceOptional.get().getCarId().getBrand() + "|" +
                "model - " + serviceOptional.get().getCarId().getModel() + "|" +
                "year - " + serviceOptional.get().getCarId().getYear() + "][");
        for (Employee employee : serviceOptional.get().getEmployees()) {
            LOGGER.info(
                    "employee id - " + employee.getId() + "|" +
                    "name - " + employee.getName() + "|" +
                    "surname - " + employee.getSurname() + "|" +
                    "age - " + employee.getAge() + "|" +
                    "position - " + employee.getPosition() + "|" +
                    "level - " + employee.getLevel() + "|" +
                    "salary - " + employee.getSalary() + "|" +
                    "phone - " + employee.getPhoneNumber() + "][" +
                    "department id - " + employee.getDepartmentId().getId() + "|" +
                    "name - " + employee.getDepartmentId().getName() + "][" +
                    "company id - " + employee.getDepartmentId().getCompanyId().getId() + "|" +
                    "name - " + employee.getDepartmentId().getCompanyId().getName() + "|" +
                    "address - " + employee.getDepartmentId().getCompanyId().getAddress() + "]");
        }
        return serviceOptional;
    }
    public void removeById() {
        LOGGER.info("Following company will be deleted");
        ServiceService serviceService = new ServiceServiceImpl();
        serviceService.removeById(
                getDataFromConsole.getLongFromConsole("id"));
        LOGGER.info("Successful deleted");
    }
}
