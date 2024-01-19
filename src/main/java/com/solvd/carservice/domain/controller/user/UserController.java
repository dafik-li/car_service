package com.solvd.carservice.domain.controller.user;

import com.solvd.carservice.domain.controller.GetDataFromConsole;
import com.solvd.carservice.domain.entity.*;
import com.solvd.carservice.domain.view.user.ViewConsoleUserMenu;
import com.solvd.carservice.service.impl.*;
import java.util.Optional;

public class UserController {
    private final GetDataFromConsole getDataFromConsole;
    private final ViewConsoleUserMenu viewConsoleUserMenu;

    public UserController() {
        this.getDataFromConsole = new GetDataFromConsole();
        this.viewConsoleUserMenu = new ViewConsoleUserMenu();
    }
    //authorization the client
    public void retrieveClientById() {
        Optional<Client> clientOptional = new ClientServiceImpl().retrieveById(
                (getDataFromConsole.getLong("id")));
        viewConsoleUserMenu.displayClient(clientOptional);
    }
    //retrieve cars list
    public void retrieveAllCars() {
        viewConsoleUserMenu.displayTittleCars();
        for (Car car : new CarServiceImpl().retrieveAll()) {
            viewConsoleUserMenu.displayCars(car);
        }
    }
    //select desire car
    public Optional<Car> retrieveCarById() {
        Optional<Car> carOptional = new CarServiceImpl().retrieveById(
                (getDataFromConsole.getLong("id")));
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
                (getDataFromConsole.getLong("id")));
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
                (getDataFromConsole.getLong("id")));
        viewConsoleUserMenu.displayDetail(detailOptional);
        return detailOptional;
    }
    //select desire employee
    public Optional<Employee> retrieveEmployeeById() {
        Optional<Employee> employeeOptional = new EmployeeServiceImpl().retrieveById(
                (getDataFromConsole.getLong("id")));
        viewConsoleUserMenu.displayEmployee(employeeOptional);
        return employeeOptional;
    }
}

