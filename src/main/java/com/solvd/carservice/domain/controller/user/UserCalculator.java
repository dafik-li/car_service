package com.solvd.carservice.domain.controller.user;

import com.solvd.carservice.domain.entity.Detail;
import com.solvd.carservice.domain.entity.Employee;
import com.solvd.carservice.domain.entity.Service;
import com.solvd.carservice.domain.view.user.ViewConsoleUserMenu;
import java.util.Optional;

public class UserCalculator {
    private final UserController userController;
    private final ViewConsoleUserMenu viewConsoleUserMenu;

    public UserCalculator() {
        this.userController = new UserController();
        this.viewConsoleUserMenu = new ViewConsoleUserMenu();
    }
    public double calculateHoursToDo(Optional<Employee> employee, Optional<Service> service, Optional<Detail> detail) {
        double hoursToDo;
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
            default:
                throw new IllegalStateException("Unexpected value: " + employee.get().getLevel());
        }
        return hoursToDo;
    }
    public double calculatePrice(Optional<Service> service, Optional<Detail> detail) {
        return service.get().getPrice() + detail.get().getPrice();
    }
    public void calculateOrder() {
        viewConsoleUserMenu.chooseCar();
        userController.retrieveAllCars();
        Long carId = userController.retrieveCarById().get().getId();
        viewConsoleUserMenu.chooseService();
        userController.retrieveServicesByCarId(carId);
        Optional<Service> serviceId = userController.retrieveServiceById();
        viewConsoleUserMenu.chooseDetail();
        userController.retrieveDetailsByCarId(carId);
        Optional<Detail> detailId = userController.retrieveDetailById();
        viewConsoleUserMenu.chooseEmployee();
        userController.retrieveEmployeesByServiceId(serviceId.get());
        Optional<Employee> employeeId = userController.retrieveEmployeeById();

        double totalPrice = calculatePrice(serviceId, detailId);
        double totalTime = calculateHoursToDo(employeeId, serviceId, detailId);
        viewConsoleUserMenu.displayTotal(totalPrice, totalTime);
    }
}
