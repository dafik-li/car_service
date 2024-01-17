package com.solvd.carservice.domain.view.user;

import com.solvd.carservice.domain.entity.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import java.util.Optional;

public class ViewConsoleUserMenu {
    static {
        System.setProperty("log4j.configurationFile", "log4j2.xml");
    }
    private final static Logger LOGGER = (Logger) LogManager.getLogger(ViewConsoleUserMenu.class);

    public void userMenu() {
        LOGGER.info("Hallo in Tony Montana Car Service!" + "\n" +
                "1 - log in" + "\n" +
                "2 - register" + "\n" +
                "3 - return" + "\n" +
                "0 - exit");
    }
    public void returnClient(Optional<Client> client) {
        LOGGER.info("Hallo " + client.get().getName() + " " + client.get().getSurname() + "!");
    }
    public void displayTittleCars() {
        LOGGER.info("List of cars");
    }
    public void displayCars(Car car) {
        LOGGER.info(
                "Car id - " + car.getId() + "|" +
                "brand - " + car.getBrand() + "|" +
                "model - " + car.getModel() + "|" +
                "year - " + car.getYear());
    }
    public void chooseCar() {
        LOGGER.info("What car do want to pimp?");
    }
    public void displayCar(Optional<Car> car) {
        LOGGER.info(
                "Car id - " + car.get().getId() + "|" +
                "brand - " + car.get().getBrand() + "|" +
                "model - " + car.get().getModel() + "|" +
                "year - " + car.get().getYear());
    }
    public void displayTittleServices() {
        LOGGER.info("List of services");
    }
    public void displayServices(Service service) {
        LOGGER.info(
                "Service id - " + service.getId() + "|" +
                "name - " + service.getName() + "|" +
                "price - " + service.getPrice());
    }
    public void chooseService() {
        LOGGER.info("What service do you need?");
    }
    public void displayService(Optional<Service> service) {
        LOGGER.info(
                "Service id - " + service.get().getId() + "|" +
                "name - " + service.get().getName() + "|" +
                "price - " + service.get().getPrice());
    }
    public void displayTittleDetails() {
        LOGGER.info("List of details");
    }
    public void displayDetails(Detail detail) {
        LOGGER.info(
                "Detail id - " + detail.getId() + "|" +
                "name - " + detail.getName() + "|" +
                "price - " + detail.getPrice());
    }
    public void chooseDetail() {
        LOGGER.info("What detail do you want?");
    }
    public void displayDetail(Optional<Detail> detail) {
        LOGGER.info(
                "Detail id - " + detail.get().getId() + "|" +
                "name - " + detail.get().getName() + "|" +
                "price - " + detail.get().getPrice());
    }
    public void displayTittleEmployees() {
        LOGGER.info("List of employees");
    }
    public void displayEmployees(Employee employee) {
        LOGGER.info(
                "Employee id - " + employee.getId() + "|" +
                "name - " + employee.getName() + "|" +
                "surname - " + employee.getSurname());
    }
    public void chooseEmployee() {
        LOGGER.info("What employee do you want?");
    }
    public void displayEmployee(Optional<Employee> employee) {
        LOGGER.info(
                "Employee id - " + employee.get().getId() + "|" +
                "name - " + employee.get().getName() + "|" +
                "surname - " + employee.get().getSurname());
    }
    public void displayTotal(double totalPrice, double totalTime) {
        LOGGER.info("Your bill - " + totalPrice + "| We pimp your car in - " + totalTime + " hours");
    }
}
