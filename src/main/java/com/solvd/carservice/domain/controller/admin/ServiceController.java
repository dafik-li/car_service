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
                getDataFromConsole.getString("name"),
                getDataFromConsole.getDouble("price"),
                getDataFromConsole.getInteger("hours to do"),
                new Car(
                        getDataFromConsole.getLong("car")),
                new Department(
                        getDataFromConsole.getLong("department")));
        ServiceService serviceService = new ServiceServiceImpl();
        serviceService.add(service);
        viewService.added(service);
    }
    public void addEmployee() {
        viewService.addEmployee();
        ServiceService serviceService = new ServiceServiceImpl();
        Long serviceId = getDataFromConsole.getLong("service_id");
        Long employeeId = getDataFromConsole.getLong("employee_id");
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
        String field = getDataFromConsole.getString("select field");
        switch (field) {
            case "name":
                service.get().setName(getDataFromConsole.getString("name"));
                break;
            case "price":
                service.get().setPrice(getDataFromConsole.getDouble("price"));
                break;
            case "hours_to_do":
                service.get().setHoursToDo(getDataFromConsole.getInteger("hours_to_do"));
                break;
        }
        serviceService.change(service, field);
        viewService.updated(field);
    }
    public Optional<Service> retrieveById() {
        Optional<Service> serviceOptional = new ServiceServiceImpl().retrieveById(
                (getDataFromConsole.getLong("id")));
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
                getDataFromConsole.getLong("id"));
        viewService.successfulDeleted();
    }
}
