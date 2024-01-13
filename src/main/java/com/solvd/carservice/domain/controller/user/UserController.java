package com.solvd.carservice.domain.controller.user;

import com.solvd.carservice.domain.controller.Generator;
import com.solvd.carservice.domain.controller.Validator;
import com.solvd.carservice.domain.controller.admin.*;
import com.solvd.carservice.domain.entity.Detail;
import com.solvd.carservice.domain.entity.Employee;
import com.solvd.carservice.domain.entity.Service;
import com.solvd.carservice.domain.exception.AuthorizationException;
import com.solvd.carservice.util.ConsoleUserMenu;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import java.util.Optional;
import java.util.Scanner;

public class UserController {
    static {
        System.setProperty("log4j.configurationFile", "log4j2.xml");
    }
    private final static Logger LOGGER = (Logger) LogManager.getLogger(Generator.class);
    private final Scanner scanner;
    private final Validator validator;
    private final ConsoleUserMenu consoleUserMenu;
    private final ClientController clientController;
    private final CarController carController;
    private final ServiceController serviceController;
    private final EmployeeController employeeController;
    private  final DetailController detailController;

    public UserController() {
        this.scanner = new Scanner(System.in);
        this.validator = new Validator();
        this.consoleUserMenu = new ConsoleUserMenu();
        this.clientController = new ClientController();
        this.carController = new CarController();
        this.serviceController = new ServiceController();
        this.employeeController = new EmployeeController();
        this.detailController = new DetailController();
    }

    public void userMenu() {
        consoleUserMenu.userMenu();
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
        clientController.add();
        calculationOrder();
    }
    public void calculationOrder() {
        consoleUserMenu.chooseCar();
        carController.retrieveAll();
        carController.retrieveById();
        consoleUserMenu.chooseService();
        serviceController.retrieveAll();
        serviceController.retrieveById();
        double totalTime = calculateHoursToDo(employeeController.retrieveById(), serviceController.retrieveById(), detailController.retrieveById());
        double totalPrice = calculatePrice(serviceController.retrieveById(), detailController.retrieveById());
        LOGGER.info(totalTime + " " + totalPrice);
    }
    public void carMenu() {
        consoleUserMenu.chooseCar();
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
}

