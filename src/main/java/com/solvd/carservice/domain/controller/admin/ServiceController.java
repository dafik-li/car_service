package com.solvd.carservice.domain.controller.admin;

import com.solvd.carservice.domain.controller.Generator;
import com.solvd.carservice.domain.entity.Car;
import com.solvd.carservice.domain.entity.Department;
import com.solvd.carservice.domain.entity.Employee;
import com.solvd.carservice.domain.entity.Service;
import com.solvd.carservice.domain.exception.AuthorizationException;
import com.solvd.carservice.domain.exception.TableException;
import com.solvd.carservice.domain.view.admin.ViewEmployee;
import com.solvd.carservice.domain.view.admin.ViewService;
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
    private final ViewService viewService;
    private final ViewEmployee viewEmployee;

    public ServiceController() {
        this.viewService = new ViewService();
        this.viewEmployee = new ViewEmployee();
    }

    public void moderate() {
        viewConsoleAdminMenu.chooseModerateService();
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
        viewConsoleAdminMenu.chooseInsertMethod();
        String menu = scanner.nextLine();
        switch (menu) {
            case "1": selectXmlParser(); break;
            case "2": add(); break;
            case "3": parser.addService(menu);
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
                parser.addService(menu); break;
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
        viewService.added(service);
    }
    public void addEmployee() {
        LOGGER.info("Add employee to service");
        ServiceService serviceService = new ServiceServiceImpl();
        Long serviceId = getDataFromConsole.getLongFromConsole("service_id");
        Long employeeId = getDataFromConsole.getLongFromConsole("employee_id");
        serviceService.addEmployee(employeeId, serviceId);
        LOGGER.info("Employee id - " + employeeId + " was assigned to the service id - " + serviceId);
    }
    public void retrieveAll() {
        viewService.showAll();
        for (Service service : new ServiceServiceImpl().retrieveAll()) {
            viewService.show(service);
            for (Employee employee : service.getEmployees()) {
                viewEmployee.show(employee);
            }
        }
    }
    public void change() {
        viewService.update();
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
        viewService.updated(field);
    }
    public Optional<Service> retrieveById() {
        Optional<Service> serviceOptional = new ServiceServiceImpl().retrieveById(
                (getDataFromConsole.getLongFromConsole("id")));
        viewService.showById(serviceOptional);
        for (Employee employee : serviceOptional.get().getEmployees()) {
            viewEmployee.showById(Optional.ofNullable(employee));
        }
        return serviceOptional;
    }
    public void removeById() {
        viewService.delete();
        ServiceService serviceService = new ServiceServiceImpl();
        serviceService.removeById(
                getDataFromConsole.getLongFromConsole("id"));
        viewService.successfulDeleted();
    }
}
