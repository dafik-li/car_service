package com.solvd.carservice.domain.controller.admin;

import com.solvd.carservice.domain.entity.Car;
import com.solvd.carservice.domain.entity.Department;
import com.solvd.carservice.domain.entity.Employee;
import com.solvd.carservice.domain.entity.Service;
import com.solvd.carservice.service.ServiceService;
import com.solvd.carservice.service.impl.ServiceServiceImpl;
import java.util.Optional;

public class ServiceController extends AbstractController {

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
        viewService.addEmployee();
        ServiceService serviceService = new ServiceServiceImpl();
        Long serviceId = getDataFromConsole.getLongFromConsole("service_id");
        Long employeeId = getDataFromConsole.getLongFromConsole("employee_id");
        serviceService.addEmployee(employeeId, serviceId);
        viewService.addedEmployee(employeeId, serviceId);
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
