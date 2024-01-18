package com.solvd.carservice.domain.controller.user;

import com.solvd.carservice.domain.controller.admin.navigate.MainMenuNavigate;
import com.solvd.carservice.domain.controller.Validator;
import com.solvd.carservice.domain.controller.admin.*;
import com.solvd.carservice.domain.entity.*;
import com.solvd.carservice.domain.exception.AuthorizationException;
import com.solvd.carservice.domain.view.user.ViewConsoleUserMenu;
import com.solvd.carservice.service.impl.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

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

    public UserController() {
        this.scanner = new Scanner(System.in);
        this.validator = new Validator();
        this.viewConsoleUserMenu = new ViewConsoleUserMenu();
    }
    public void userMenu() {
        viewConsoleUserMenu.userMenu();
        String menu = scanner.nextLine();
        switch (menu) {
            case "1": retrieveClientById(); calculationOrder(); break;
            case "2": new ClientController().add(); userMenu(); break;
            case "3": new MainMenuNavigate().authorization(); break;
            case "0": System.exit(0); break;
        }
        try {
            validator.validateAuthorization(menu);
        } catch (AuthorizationException e) {
            LOGGER.error(e.toString());
            userMenu();
        }
    }
    //authorization the client
    public void retrieveClientById() {
        Optional<Client> clientOptional = new ClientServiceImpl().retrieveById(
                (getDataFromConsole.getLongFromConsole("id")));
        viewConsoleUserMenu.displayClient(clientOptional);
    }
    //retrieve cars list
    public void retrieveAll() {
        viewConsoleUserMenu.displayTittleCars();
        for (Car car : new CarServiceImpl().retrieveAll()) {
            viewConsoleUserMenu.displayCars(car);
        }
    }
    //select desire car
    public Optional<Car> retrieveById() {
        Optional<Car> carOptional = new CarServiceImpl().retrieveById(
                (getDataFromConsole.getLongFromConsole("id")));
        viewConsoleUserMenu.displayCar(carOptional);
        return carOptional;
    }
    //retrieve services list for chosen car
    public void retrieveServicesByCarId(Long id) {
        viewConsoleUserMenu.displayTittleServices();
        for (Service service : new ServiceServiceImpl().retrieveByCar(id)) {
            viewConsoleUserMenu.displayServices(service);
        }
    }
    //select desire service
    public Optional<Service> retrieveServiceById() {
        Optional<Service> serviceOptional = new ServiceServiceImpl().retrieveById(
                (getDataFromConsole.getLongFromConsole("id")));
        viewConsoleUserMenu.displayService(serviceOptional);
        return serviceOptional;
    }
    //retrieve details list for chosen car
    public void retrieveDetailsByCarId(Long id) {
        viewConsoleUserMenu.displayTittleDetails();
        for (Detail detail : new DetailServiceImpl().retrieveByCar(id)) {
            viewConsoleUserMenu.displayDetails(detail);
        }
    }
    //retrieve employees available for chosen service
    public void retrieveEmployeesByServiceId(Service service) {
        viewConsoleUserMenu.displayTittleEmployees();
        for (Employee employee : new ServiceServiceImpl().retrieveEmployeesByServiceId(service)) {
            viewConsoleUserMenu.displayEmployees(employee);
        }
    }
    //select desire detail
    public Optional<Detail> retrieveDetailById() {
        Optional<Detail> detailOptional = new DetailServiceImpl().retrieveById(
                (getDataFromConsole.getLongFromConsole("id")));
        viewConsoleUserMenu.displayDetail(detailOptional);
        return detailOptional;
    }
    //select desire employee
    public Optional<Employee> retrieveEmployeeById() {
        Optional<Employee> employeeOptional = new EmployeeServiceImpl().retrieveById(
                (getDataFromConsole.getLongFromConsole("id")));
        viewConsoleUserMenu.displayEmployee(employeeOptional);
        return employeeOptional;
    }
    public double calculateHoursToDo(Optional<Employee> employee, Optional<Service> service, Optional<Detail> detail) {
        double hoursToDo = scanner.nextInt();
        switch (employee.get().getLevel()) {
            case 1:
                hoursToDo = service.get().getHoursToDo() * 1.7 + detail.get().getDeliveryDays() * 24;
                break;
            case 2:
                hoursToDo = service.get().getHoursToDo() * 1.5 + detail.get().getDeliveryDays() * 24;
                break;
            case 3:
                hoursToDo = service.get().getHoursToDo() * 1.3 + detail.get().getDeliveryDays() * 24;
                break;
            case 4:
                hoursToDo = service.get().getHoursToDo() + detail.get().getDeliveryDays() * 24;
                break;
            case 5:
                hoursToDo = service.get().getHoursToDo() * 0.7 + detail.get().getDeliveryDays() * 24;
                break;
            case 6:
                hoursToDo = service.get().getHoursToDo() * 0.5 + detail.get().getDeliveryDays() * 24;
                break;
            case 7:
                hoursToDo = service.get().getHoursToDo() * 0.3 + detail.get().getDeliveryDays() * 24;
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
        Long carId = retrieveById().get().getId();
        viewConsoleUserMenu.chooseService();
        retrieveServicesByCarId(carId);
        Optional<Service> serviceId = retrieveServiceById();
        viewConsoleUserMenu.chooseDetail();
        retrieveDetailsByCarId(carId);
        Optional<Detail> detailId = retrieveDetailById();
        viewConsoleUserMenu.chooseEmployee();
        retrieveEmployeesByServiceId(serviceId.get());
        Optional<Employee> employeeId = retrieveEmployeeById();

        double totalPrice = calculatePrice(serviceId, detailId);
        double totalTime = calculateHoursToDo(employeeId, serviceId, detailId);
        viewConsoleUserMenu.displayTotal(totalPrice, totalTime);
    }
}

