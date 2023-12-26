package com.solvd.carservice.domain.controller;

import com.solvd.carservice.domain.*;
import com.solvd.carservice.exception.MenuException;
import com.solvd.carservice.service.*;
import com.solvd.carservice.service.impl.*;
import com.solvd.carservice.utils.ConsoleMenu;
import com.solvd.carservice.utils.GetDataFromConsole;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import java.util.Optional;
import java.util.Scanner;

public class AdminController {
    static {
        System.setProperty("log4j.configurationFile", "log4j2.xml");
    }
    private final static Logger LOGGER = (Logger) LogManager.getLogger(AdminController.class);
    private final Scanner scanner;
    private final Validator validator;
    private final GetDataFromConsole getDataFromConsole;
    private final ConsoleMenu consoleMenu;

    public AdminController() {
        this.scanner = new Scanner(System.in);
        this.validator = new Validator();
        this.getDataFromConsole = new GetDataFromConsole();
        this.consoleMenu = new ConsoleMenu();
    }


    public void moderateTables() {
        consoleMenu.moderateTablesMenu();
        String menu = scanner.nextLine();
        switch (menu) {
            case "1": moderateCompany(); break;
            case "2": moderateDepartment(); break;
            case "3": moderateEmployee(); break;
            case "4": moderateService(); break;
            case "5": moderateCar(); break;
            case "6": moderateDetail(); break;
            case "7": moderateOrder(); break;
            case "8": moderateCost(); break;
            case "9": moderateClient(); break;
            case "0": new ActionController().authorization(); break;
        }
        try {
            validator.validateModeration(menu);
        } catch (MenuException e) {
            LOGGER.error(toString());
            moderateTables();
        }
    }


    public void moderateCompany() {
        consoleMenu.moderateActionsMenu();
        String menu = scanner.nextLine();
        switch (menu) {
            case "1": addCompany(); break;
            case "2": retrieveAllCompanies(); break;
            case "3": retrieveByIdCompany(); break;
            case "4": changeCompany(); break;
            case "5": removeByIdCompany(); break;
            case "0": moderateTables(); break;
        }
        try {
            validator.validateTable(menu);
        } catch (MenuException e) {
            LOGGER.error(toString());
            moderateCompany();
        }
    }
    public void addCompany() {
        Company company = new Company(
                getDataFromConsole.getStringFromConsole("name"),
                getDataFromConsole.getStringFromConsole("address"));
        CompanyService companyService = new CompanyServiceImpl();
        companyService.add(company);
        LOGGER.info("Company - " + company.getName() + company.getAddress() + " - was added");
    }
    public void retrieveAllCompanies() {
        LOGGER.info("List of companies");
        for (Company company : new CompanyServiceImpl().retrieveAll()) {
            LOGGER.info(
                    "Company id - " + "" + company.getId() + "|" +
                    "name - " + "" + company.getName() + "|" +
                    "address - " + "" + company.getAddress());
        }
    }
    public void changeCompany () {
    }
    public void retrieveByIdCompany() {
        Optional<Company> companyOptional = new CompanyServiceImpl().retrieveById((getDataFromConsole.getLongFromConsole("id")));
        LOGGER.info(
                "Company id - " + "" + companyOptional.get().getId() + "|" +
                "name - " + "" + companyOptional.get().getName() + "|" +
                "address - " + "" + companyOptional.get().getAddress());
    }
    public void removeByIdCompany() {
        LOGGER.info("Following company wil be terminated");
        CompanyService companyService = new CompanyServiceImpl();
        companyService.removeById(getDataFromConsole.getLongFromConsole("id"));
    }


    public void moderateDepartment() {
        consoleMenu.moderateActionsMenu();
        String menu = scanner.nextLine();
        switch (menu) {
            case "1": addDepartment(); break;
            case "2": retrieveAllDepartments(); break;
            case "3": retrieveByIdDepartment(); break;
            case "4": changeDepartment(); break;
            case "5": removeByIdDepartment(); break;
            case "0": moderateTables(); break;
        }
        try {
            validator.validateTable(menu);
        } catch (MenuException e) {
            LOGGER.error(toString());
            moderateDepartment();
        }
    }
    public void addDepartment() {
        Department department = new Department(
                getDataFromConsole.getStringFromConsole("name"),
                new Company(getDataFromConsole.getLongFromConsole("company")));
        DepartmentService departmentService = new DepartmentServiceImpl();
        departmentService.add(department);
        LOGGER.info("Department - " + department.getName() + department.getCompanyId() + " - was added");
    }
    public void retrieveAllDepartments() {
        LOGGER.info("List of departments");
        for (Department department : new DepartmentServiceImpl().retrieveAll()) {
            LOGGER.info(
                    "Department id - " + "" + department.getId() + "|" +
                    "name - " + "" + department.getName() + "|" +
                    "company - " + "" + department.getCompanyId());
        }
    }
    public void changeDepartment () {
    }
    public void retrieveByIdDepartment() {
        Optional<Department> departmentOptional = new DepartmentServiceImpl().retrieveById((getDataFromConsole.getLongFromConsole("id")));
        LOGGER.info(
                "Department id - " + "" + departmentOptional.get().getId() + "|" +
                "name - " + "" + departmentOptional.get().getName() + "|" +
                "company - " + "" + departmentOptional.get().getCompanyId());
    }
    public void removeByIdDepartment() {
        LOGGER.info("Following department will be redundant");
        DepartmentService departmentService = new DepartmentServiceImpl();
        departmentService.removeById(getDataFromConsole.getLongFromConsole("id"));
    }


    public void moderateEmployee() {
        consoleMenu.moderateActionsMenu();
        String menu = scanner.nextLine();
        switch (menu) {
            case "1": addEmployee(); break;
            case "2": retrieveAllEmployees(); break;
            case "3": retrieveByIdEmployee(); break;
            case "4": changeEmployee(); break;
            case "5": removeByIdEmployee(); break;
            case "0": moderateTables(); break;
        }
        try {
            validator.validateTable(menu);
        } catch (MenuException e) {
            LOGGER.error(toString());
            moderateEmployee();
        }
    }
    public void addEmployee() {
        Employee employee = new Employee(
                getDataFromConsole.getStringFromConsole("name"),
                getDataFromConsole.getStringFromConsole("surname"),
                getDataFromConsole.getIntegerFromConsole("age"),
                getDataFromConsole.getStringFromConsole("position"),
                getDataFromConsole.getIntegerFromConsole("level"),
                getDataFromConsole.getIntegerFromConsole("salary"),
                getDataFromConsole.getStringFromConsole("phone number"),
                new Department(getDataFromConsole.getLongFromConsole("department")));
        EmployeeService employeeService = new EmployeeServiceImpl();
        employeeService.add(employee);
        LOGGER.info("Employee - " + employee.getName() + employee.getSurname() + " - was added");
    }
    public void retrieveAllEmployees() {
        LOGGER.info("List of employees");
        for (Employee employee : new EmployeeServiceImpl().retrieveAll()) {
            LOGGER.info(
                    "Employee id - " + "" + employee.getId() + "|" +
                    "name - " + "" + employee.getName() + "|" +
                    "surname - " + "" + employee.getSurname() + "|" +
                    "age - " + "" + employee.getAge() + "|" +
                    "position - " + "" + employee.getPosition() + "|" +
                    "level - " + "" + employee.getLevel() + "|" +
                    "salary - " + "" + employee.getSalary() + "|" +
                    "phone - " + "" + employee.getPhoneNumber() + "|" +
                    "department - " + "" + employee.getDepartmentId());
        }
    }
    public void changeEmployee () {
    }
    public void retrieveByIdEmployee() {
        Optional<Employee> employeeOptional = new EmployeeServiceImpl().retrieveById((getDataFromConsole.getLongFromConsole("id")));
        LOGGER.info(
                "Employee id - " + "" + employeeOptional.get().getId() + "|" +
                "name - " + "" + employeeOptional.get().getName() + "|" +
                "surname - " + "" + employeeOptional.get().getSurname() + "|" +
                "age - " + "" + employeeOptional.get().getAge() + "|" +
                "position - " + "" + employeeOptional.get().getPosition() + "|" +
                "level - " + "" + employeeOptional.get().getLevel() + "|" +
                "salary - " + "" + employeeOptional.get().getSalary() + "|" +
                "phone - " + "" + employeeOptional.get().getPhoneNumber() + "|" +
                "department - " + "" + employeeOptional.get().getDepartmentId());
    }
    public void removeByIdEmployee() {
        LOGGER.info("Following employee will be fired");
        EmployeeService employeeService = new EmployeeServiceImpl();
        employeeService.removeById(getDataFromConsole.getLongFromConsole("id"));
    }


    public void moderateService() {
        consoleMenu.moderateActionsMenu();
        String menu = scanner.nextLine();
        switch (menu) {
            case "1": addService(); break;
            case "2": retrieveAllServices(); break;
            case "3": retrieveByIdService(); break;
            case "4": changeService(); break;
            case "5": removeByIdService(); break;
            case "0": moderateTables(); break;
        }
        try {
            validator.validateTable(menu);
        } catch (MenuException e) {
            LOGGER.error(toString());
            moderateService();
        }
    }
    public void addService() {
        Service service = new Service(
                getDataFromConsole.getStringFromConsole("name"),
                getDataFromConsole.getDoubleFromConsole("price"),
                getDataFromConsole.getDoubleFromConsole("days to do"),
                new Car(getDataFromConsole.getLongFromConsole("car")),
                new Department(getDataFromConsole.getLongFromConsole("department")));
        ServiceService serviceService = new ServiceServiceImpl();
        serviceService.add(service);
        LOGGER.info("Service - " + service.getName() + " - was added");
    }
    public void retrieveAllServices() {
        LOGGER.info("List of services");
        for (Service service : new ServiceServiceImpl().retrieveAll()) {
            LOGGER.info(
                    "Service id - " + "" + service.getId() + "|" +
                    "name - " + "" + service.getName() + "|" +
                    "price - " + "" + service.getPrice() + "|" +
                    "days to do - " + "" + service.getDaysToDo() + "|" +
                    "car - " + "" + service.getCarId() + "|" +
                    "department - " + "" + service.getDepartmentId());
        }
    }
    public void changeService () {
    }
    public void retrieveByIdService() {
        Optional<Service> serviceOptional = new ServiceServiceImpl().retrieveById((getDataFromConsole.getLongFromConsole("id")));
        LOGGER.info(
                "Service id - " + "" + serviceOptional.get().getId() + "|" +
                "name - " + "" + serviceOptional.get().getName() + "|" +
                "price - " + "" + serviceOptional.get().getPrice() + "|" +
                "days to do - " + "" + serviceOptional.get().getDaysToDo() + "|" +
                "car - " + "" + serviceOptional.get().getCarId() + "|" +
                "department - " + "" + serviceOptional.get().getDepartmentId());
    }
    public void removeByIdService() {
        LOGGER.info("Following company will be deleted");
        ServiceService serviceService = new ServiceServiceImpl();
        serviceService.removeById(getDataFromConsole.getLongFromConsole("id"));
    }


    public void moderateCar() {
        consoleMenu.moderateActionsMenu();
        String menu = scanner.nextLine();
        switch (menu) {
            case "1": addCar(); break;
            case "2": retrieveAllCars(); break;
            case "3": retrieveByIdCar(); break;
            case "4": changeCar(); break;
            case "5": removeByIdCar(); break;
            case "0": moderateTables(); break;
        }
        try {
            validator.validateTable(menu);
        } catch (MenuException e) {
            LOGGER.error(toString());
            moderateCar();
        }
    }
    public void addCar() {
        Car car = new Car(
                getDataFromConsole.getStringFromConsole("brand"),
                getDataFromConsole.getStringFromConsole("model"),
                getDataFromConsole.getIntegerFromConsole("year"));
        CarService carService = new CarServiceImpl();
        carService.add(car);
        LOGGER.info("Car - " + car.getBrand() + car.getModel() + car.getYear() + " - was added");
    }
    public void retrieveAllCars() {
        LOGGER.info("List of cars");
        for (Car car : new CarServiceImpl().retrieveAll()) {
            LOGGER.info(
                    "Car id - " + "" + car.getId() + "|" +
                    "brand - " + "" + car.getBrand() + "|" +
                    "model - " + "" + car.getModel() + "|" +
                    "year - " + "" + car.getYear());
        }
    }
    public void retrieveByIdCar() {
        Optional<Car> carOptional = new CarServiceImpl().retrieveById((getDataFromConsole.getLongFromConsole("id")));
        LOGGER.info(
                "Car id - " + "" + carOptional.get().getId() + "|" +
                "brand - " + "" + carOptional.get().getBrand() + "|" +
                "model - " + "" + carOptional.get().getModel() + "|" +
                "year - " + "" + carOptional.get().getYear());
    }
    public void changeCar() {
    }
    public void removeByIdCar() {
        LOGGER.info("Following car will be deleted");
        CarService carService = new CarServiceImpl();
        carService.removeById(getDataFromConsole.getLongFromConsole("id"));
    }


    public void moderateDetail() {
        consoleMenu.moderateActionsMenu();
        String menu = scanner.nextLine();
        switch (menu) {
            case "1": addDetail(); break;
            case "2": retrieveAllDetails(); break;
            case "3": retrieveByIdDetail(); break;
            case "4": changeDetail(); break;
            case "5": removeByIdDetail(); break;
            case "0": moderateTables(); break;
        }
        try {
            validator.validateTable(menu);
        } catch (MenuException e) {
            LOGGER.error(toString());
            moderateDetail();
        }
    }
    public void addDetail() {
        Detail detail = new Detail(
                getDataFromConsole.getStringFromConsole("name"),
                new Car(getDataFromConsole.getLongFromConsole("car")),
                getDataFromConsole.getBooleanFromConsole("in stock"),
                getDataFromConsole.getIntegerFromConsole("delivery days"));
        DetailService detailService = new DetailServiceImpl();
        detailService.add(detail);
        LOGGER.info("Detail - " + detail.getName() + " - was added");
    }
    public void retrieveAllDetails() {
        LOGGER.info("List of details");
        for (Detail detail : new DetailServiceImpl().retrieveAll()) {
            LOGGER.info(
                    "Detail id - " + "" + detail.getId() + "|" +
                    "name - " + "" + detail.getName() + "|" +
                    "car - " + "" + detail.getCarId() + "|" +
                    "in stock - " + "" + detail.getInStock() + "|" +
                    "delivery days - " + "" + detail.getDeliveryDays());
        }
    }
    public void changeDetail () {
    }
    public void retrieveByIdDetail() {
        Optional<Detail> detailOptional = new DetailServiceImpl().retrieveById((getDataFromConsole.getLongFromConsole("id")));
        LOGGER.info(
                "Detail id - " + "" + detailOptional.get().getId() + "|" +
                "name - " + "" + detailOptional.get().getName() + "|" +
                "car - " + "" + detailOptional.get().getCarId() + "|" +
                "in stock - " + "" + detailOptional.get().getInStock() + "|" +
                "delivery days - " + "" + detailOptional.get().getDeliveryDays());
    }
    public void removeByIdDetail() {
        LOGGER.info("Following detail will be deleted");
        DetailService detailService = new DetailServiceImpl();
        detailService.removeById(getDataFromConsole.getLongFromConsole("id"));
    }


    public void moderateOrder() {
        consoleMenu.moderateTablesMenu();
        String menu = scanner.nextLine();
        switch (menu) {
            case "1": addOrder(); break;
            case "2": retrieveAllOrders(); break;
            case "3": retrieveByIdOrder(); break;
            case "4": changeOrder(); break;
            case "5": removeByIdOrder(); break;
            case "0": moderateTables(); break;
        }
        try {
            validator.validateTable(menu);
        } catch (MenuException e) {
            LOGGER.error(toString());
            moderateOrder();
        }
    }
    public void addOrder() {
        Order order = new Order(
                getDataFromConsole.getDateFromConsole("date"),
                new Client(getDataFromConsole.getLongFromConsole("client")),
                new Cost(getDataFromConsole.getLongFromConsole("cost")));
        OrderService orderService = new OrderServiceImpl();
        orderService.add(order);
        LOGGER.info("Order - " + order.getDate() + order.getClientId() + order.getCostId() + " - was added");
    }
    public void retrieveAllOrders() {
        LOGGER.info("List of orders");
        for (Order order : new OrderServiceImpl().retrieveAll()) {
            LOGGER.info(
                    "Order id - " + "" + order.getId() + "|" +
                    "date - " + "" + order.getDate() + "|" +
                    "client - " + "" + order.getClientId() + "|" +
                    "cost - " + "" + order.getCostId());
        }
    }
    public void changeOrder () {
    }
    public void retrieveByIdOrder() {
        Optional<Order> orderOptional = new OrderServiceImpl().retrieveById((getDataFromConsole.getLongFromConsole("id")));
        LOGGER.info(
                "Order id - " + "" + orderOptional.get().getId() + "|" +
                "date - " + "" + orderOptional.get().getDate() + "|" +
                "client - " + "" + orderOptional.get().getClientId()  + "|" +
                "cost - " + "" + orderOptional.get().getCostId());
    }
    public void removeByIdOrder() {
        LOGGER.info("Following order will be deleted");
        OrderService orderService = new OrderServiceImpl();
        orderService.removeById(getDataFromConsole.getLongFromConsole("id"));
    }


    public void moderateCost() {
        consoleMenu.moderateTablesMenu();
        String menu = scanner.nextLine();
        switch (menu) {
            case "1": addCost(); break;
            case "2": retrieveAllCosts(); break;
            case "3": retrieveByIdCost(); break;
            case "4": changeCost(); break;
            case "5": removeByIdCost(); break;
            case "0": moderateTables(); break;
        }
        try {
            validator.validateTable(menu);
        } catch (MenuException e) {
            LOGGER.error(toString());
            moderateCost();
        }
    }
    public void addCost() {
        Cost cost = new Cost(
                getDataFromConsole.getDoubleFromConsole("cost"),
                new Service(getDataFromConsole.getLongFromConsole("service")),
                new Detail(getDataFromConsole.getLongFromConsole("detail")));
        CostService costService = new CostServiceImpl();
        costService.add(cost);
        LOGGER.info("Cost - " + cost.getCost() + cost.getServiceId() + cost.getDetailId() + " - was added");
    }
    public void retrieveAllCosts() {
        LOGGER.info("List of costs");
        for (Cost cost : new CostServiceImpl().retrieveAll()) {
            LOGGER.info(
                    "Cost id - " + "" + cost.getId() + "|" +
                    "cost - " + "" + cost.getCost() + "|" +
                    "service - " + "" + cost.getServiceId() + "|" +
                    "detail - " + "" + cost.getDetailId());
        }
    }
    public void changeCost () {
    }
    public void retrieveByIdCost() {
        Optional<Cost> costOptional = new CostServiceImpl().retrieveById((getDataFromConsole.getLongFromConsole("id")));
        LOGGER.info(
                "Cost id - " + "" + costOptional.get().getId() + "|" +
                "cost - " + "" + costOptional.get().getCost() + "|" +
                "service - " + "" + costOptional.get().getServiceId()  + "|" +
                "detail - " + "" + costOptional.get().getDetailId());
    }
    public void removeByIdCost() {
        LOGGER.info("Following cost will be deleted");
        CostService costService = new CostServiceImpl();
        costService.removeById(getDataFromConsole.getLongFromConsole("id"));
    }


    public void moderateClient() {
        consoleMenu.moderateActionsMenu();
        String menu = scanner.nextLine();
        switch (menu) {
            case "1": addClient(); break;
            case "2": retrieveAllClients(); break;
            case "3": retrieveByIdClient(); break;
            case "4": changeClient(); break;
            case "5": removeByIdClient(); break;
            case "0": moderateTables(); break;
        }
        try {
            validator.validateTable(menu);
        } catch (MenuException e) {
            LOGGER.error(toString());
            moderateClient();
        }
    }
    public void addClient() {
        Client client = new Client(getDataFromConsole.getStringFromConsole("name"),
                getDataFromConsole.getStringFromConsole("surname"),
                getDataFromConsole.getStringFromConsole("phone number"),
                getDataFromConsole.getDateFromConsole("birthday"));
        ClientService clientService = new ClientServiceImpl();
        clientService.add(client);
        LOGGER.info("Client - " + client.getName() + client.getSurname() + " - was added");
    }
    public void retrieveAllClients() {
        LOGGER.info("List of clients");
        for (Client client : new ClientServiceImpl().retrieveAll()) {
            LOGGER.info(
                    "Client id - " + "" + client.getId() + "|" +
                    "name - " + "" + client.getName() + "|" +
                    "surname - " + "" + client.getSurname() + "|" +
                    "phone number - " + "" + client.getPhoneNumber() + "|" +
                    "birthday - " + "" + client.getBirthday());
        }
    }
    public void retrieveByIdClient() {
        Optional<Client> clientOptional = new ClientServiceImpl().retrieveById((getDataFromConsole.getLongFromConsole("id")));
        LOGGER.info(
                "Client id - " + "" + clientOptional.get().getId() + "|" +
                "name - " + "" + clientOptional.get().getName() + "|" +
                "surname - " + "" + clientOptional.get().getSurname() + "|" +
                "phone number - " + "" + clientOptional.get().getPhoneNumber() + "|" +
                "birthday - " + "" + clientOptional.get().getBirthday());
    }
    public void changeClient() {

    }
    public void removeByIdClient() {
        LOGGER.info("Following car will be deleted");
        ClientService clientService = new ClientServiceImpl();
        clientService.removeById(getDataFromConsole.getLongFromConsole("id"));
    }
}
