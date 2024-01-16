package com.solvd.carservice.domain.controller.user;

import com.solvd.carservice.domain.controller.Generator;
import com.solvd.carservice.domain.controller.Validator;
import com.solvd.carservice.domain.controller.admin.*;
import com.solvd.carservice.domain.entity.Car;
import com.solvd.carservice.domain.entity.Detail;
import com.solvd.carservice.domain.entity.Employee;
import com.solvd.carservice.domain.entity.Service;
import com.solvd.carservice.domain.exception.AuthorizationException;
import com.solvd.carservice.domain.view.*;
import com.solvd.carservice.persistence.jdbcimpl.EmployeeRepositoryImpl;
import com.solvd.carservice.service.DetailService;
import com.solvd.carservice.service.ServiceService;
import com.solvd.carservice.service.impl.CarServiceImpl;
import com.solvd.carservice.service.impl.DetailServiceImpl;
import com.solvd.carservice.service.impl.EmployeeServiceImpl;
import com.solvd.carservice.service.impl.ServiceServiceImpl;
import com.solvd.carservice.util.GetDataFromConsole;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class UserController extends CarController{
    static {
        System.setProperty("log4j.configurationFile", "log4j2.xml");
    }
    private final static Logger LOGGER = (Logger) LogManager.getLogger(UserController.class);
    private final Scanner scanner;
    private final Validator validator;
    private final ViewConsoleUserMenu viewConsoleUserMenu;
    private final ClientController clientController;
    private final ViewCar viewCar;
    private final ViewService viewService;
    private final ViewDetail viewDetail;
    private final ViewEmployee viewEmployee;

    public UserController() {
        this.scanner = new Scanner(System.in);
        this.validator = new Validator();
        this.viewConsoleUserMenu = new ViewConsoleUserMenu();
        this.clientController = new ClientController();
        this.viewCar = new ViewCar();
        this.viewService = new ViewService();
        this.viewDetail = new ViewDetail();
        this.viewEmployee = new ViewEmployee();
    }
    public void userMenu() {
        viewConsoleUserMenu.userMenu();
        String menu = scanner.nextLine();
        switch (menu) {
            case "1": clientMenu(); break;
            case "2": newClientMenu(); break;
            case "0": System.exit(0); break;
        }
        try {
            validator.validateStartPageMenu(menu);
        } catch (AuthorizationException e) {
            LOGGER.error(e.toString());
            userMenu();
        }
    }
    public void clientMenu() {
        clientController.retrieveById();
        calculationOrder();
    }
    public void newClientMenu() {
        clientController.add();userMenu();
        calculationOrder();
    }
    public void retrieveAll() {
        viewCar.showAll();
        for (Car car : new CarServiceImpl().retrieveAll()) {
            viewCar.show(car);
        }
    }
    public Optional<Car> retrieveCarById() {
        Optional<Car> carOptional = new CarServiceImpl().retrieveById(
                (getDataFromConsole.getLongFromConsole("id")));
        viewCar.showById(carOptional);
        return carOptional;
    }
    public List<Service> retrieveServicesById(Long id) {
        List<Service> services = new ArrayList<>();
        viewService.showAll();
        for (Service service : new ServiceServiceImpl().retrieveByCar(id)) {
            viewService.show(service);
        }
        return services;
    }
    public Optional<Service> retrieveServiceById() {
        Optional<Service> serviceOptional = new ServiceServiceImpl().retrieveById(
                (getDataFromConsole.getLongFromConsole("id")));
        viewService.showById(serviceOptional);
        return serviceOptional;
    }
    public List<Detail> retrieveDetailsById(Long id) {
        List<Detail> details = new ArrayList<>();
        viewDetail.showAll();
        for (Detail detail : new DetailServiceImpl().retrieveByCar(id)) {
            viewDetail.show(detail);
        }
        return details;
    }
    public List<Employee> retrieveEmployeesById(Service service) {
        List<Employee> employees = new ArrayList<>();
        viewEmployee.showAll();
        for (Employee employee : new ServiceServiceImpl().retrieveEmployeesByServiceId(service)) {
            viewEmployee.show(employee);
        }
        return employees;
    }
    public Optional<Detail> retrieveDetailById() {
        Optional<Detail> detailOptional = new DetailServiceImpl().retrieveById(
                (getDataFromConsole.getLongFromConsole("id")));
        viewDetail.showById(detailOptional);
        return detailOptional;
    }
    public Optional<Employee> retrieveEmployeeById() {
        Optional<Employee> employeeOptional = new EmployeeServiceImpl().retrieveById(
                (getDataFromConsole.getLongFromConsole("id")));
        viewEmployee.showById(employeeOptional);
        return employeeOptional;
    }
    public double calculateHoursToDo(Optional<Employee> employee, Optional<Service> service, Optional<Detail> detail) {
        double hoursToDo = scanner.nextInt();
        switch (employee.get().getLevel()) {
            case 1:
                hoursToDo = service.get().getHoursToDo() * 1.7 + detail.get().getDeliveryDays()*24;
                break;
            case 2:
                hoursToDo = service.get().getHoursToDo() * 1.5 + detail.get().getDeliveryDays()*24;
                break;
            case 3:
                hoursToDo = service.get().getHoursToDo() * 1.3 + detail.get().getDeliveryDays()*24;
                break;
            case 4:
                hoursToDo = service.get().getHoursToDo() + detail.get().getDeliveryDays()*24;
                break;
            case 5:
                hoursToDo = service.get().getHoursToDo() * 0.7 + detail.get().getDeliveryDays()*24;
                break;
            case 6:
                hoursToDo = service.get().getHoursToDo() * 0.5 + detail.get().getDeliveryDays()*24;
                break;
            case 7:
                hoursToDo = service.get().getHoursToDo() * 0.3 + detail.get().getDeliveryDays()*24;
                break;
        }
        return hoursToDo;
    }
    public double calculatePrice(Optional<Service> service, Optional<Detail> detail) {
        return service.get().getPrice() + detail.get().getPrice();
    }
    public void calculationOrder() {
        viewConsoleUserMenu.chooseCar();
        retrieveAll();
        Long carId = retrieveCarById().get().getId();
        viewConsoleUserMenu.chooseService();
        List<Service> services = retrieveServicesById(carId);
        //Optional<Service> serviceId = serviceController.retrieveById();
        Optional<Service> serviceId = retrieveServiceById();
        viewConsoleUserMenu.chooseDetail();
        List<Detail> details = retrieveDetailsById(carId);
        //Optional<Detail> detailId = detailController.retrieveById();
        Optional<Detail> detailId = retrieveDetailById();
        viewConsoleUserMenu.chooseEmployee();
        List<Employee> employees = retrieveEmployeesById(serviceId.get());
        //Optional<Employee> employeeId = employeeController.retrieveById();
        Optional<Employee> employeeId = retrieveEmployeeById();

        double totalPrice = calculatePrice(serviceId, detailId);
        double totalTime = calculateHoursToDo(employeeId, serviceId, detailId);
        LOGGER.info(totalPrice + " - " + totalTime);
    }
    public void carMenu() {
        viewConsoleUserMenu.chooseCar();
        String menu = scanner.nextLine();
        switch (menu) {
            case "1": new ClientController().retrieveById(); break;
            case "2": new ClientController().add(); break;
            case "0": System.exit(0); break;
        }
        try {
            validator.validateStartPageMenu(menu);
        } catch (AuthorizationException e) {
            LOGGER.error(e.toString());
            userMenu();
        }
    }
}

