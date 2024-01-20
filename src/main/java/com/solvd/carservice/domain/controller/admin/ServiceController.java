package com.solvd.carservice.domain.controller.admin;

import com.solvd.carservice.domain.entity.Car;
import com.solvd.carservice.domain.entity.Department;
import com.solvd.carservice.domain.entity.Employee;
import com.solvd.carservice.domain.entity.Service;
import com.solvd.carservice.domain.view.admin.InterfaceView;
import com.solvd.carservice.domain.view.admin.ViewEmployee;
import com.solvd.carservice.domain.view.admin.ViewService;
import com.solvd.carservice.service.InterfaceService;
import com.solvd.carservice.service.ServiceService;
import com.solvd.carservice.service.impl.ServiceServiceImpl;
import java.util.Optional;

public class ServiceController extends AbstractController<Service> {
    private final ViewService viewService;
    private final ViewEmployee viewEmployee;
    private final ServiceService serviceService;

    public ServiceController(InterfaceView<Service> view, InterfaceService<Service> service) {
        super(view, service);
        this.viewService = new ViewService();
        this.viewEmployee = new ViewEmployee();
        this.serviceService = new ServiceServiceImpl();
    }
    public void add() {
        Service serviceEntity = new Service(
                getDataFromConsole.getString("name"),
                getDataFromConsole.getDouble("price"),
                getDataFromConsole.getInteger("hours to do"),
                new Car(
                        getDataFromConsole.getLong("car")),
                new Department(
                        getDataFromConsole.getLong("department")));
        service.add(serviceEntity);
        view.added(serviceEntity);
    }
    public void addEmployee() {
        viewService.addEmployee();
        Long serviceId = getDataFromConsole.getLong("service_id");
        Long employeeId = getDataFromConsole.getLong("employee_id");
        serviceService.addEmployee(employeeId, serviceId);
        viewService.addedEmployee(employeeId, serviceId);
    }
    public void retrieveAll() {
        view.showAll();
        for (Service service : service.retrieveAll()) {
            view.show(service);
            for (Employee employee : service.getEmployees()) {
                viewEmployee.show(employee);
            }
        }
    }
    public void change() {
        view.update();
        Optional<Service> serviceEntity = retrieveById();
        String field = getDataFromConsole.getString("select field");
        switch (field) {
            case "name":
                serviceEntity.get().setName(getDataFromConsole.getString("name"));
                break;
            case "price":
                serviceEntity.get().setPrice(getDataFromConsole.getDouble("price"));
                break;
            case "hours_to_do":
                serviceEntity.get().setHoursToDo(getDataFromConsole.getInteger("hours_to_do"));
                break;
        }
        service.change(serviceEntity, field);
        view.updated(field);
    }
    public Optional<Service> retrieveById() {
        Optional<Service> serviceOptional = service.retrieveById(
                (getDataFromConsole.getLong("id")));
        view.showById(serviceOptional);
        for (Employee employee : serviceOptional.get().getEmployees()) {
            viewEmployee.showById(Optional.ofNullable(employee));
        }
        return serviceOptional;
    }
}
