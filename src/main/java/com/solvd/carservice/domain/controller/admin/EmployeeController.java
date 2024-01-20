package com.solvd.carservice.domain.controller.admin;

import com.solvd.carservice.domain.entity.Department;
import com.solvd.carservice.domain.entity.Employee;
import com.solvd.carservice.domain.entity.Service;
import com.solvd.carservice.domain.view.admin.InterfaceView;
import com.solvd.carservice.domain.view.admin.ViewEmployee;
import com.solvd.carservice.domain.view.admin.ViewService;
import com.solvd.carservice.service.EmployeeService;
import com.solvd.carservice.service.InterfaceService;
import com.solvd.carservice.service.impl.EmployeeServiceImpl;
import java.util.Optional;

public class EmployeeController extends AbstractController<Employee> {
    private final ViewService viewService;
    private final ViewEmployee viewEmployee;
    private final EmployeeService employeeService;

    public EmployeeController(InterfaceView<Employee> view, InterfaceService<Employee> service) {
        super(view, service);
        this.viewService = new ViewService();
        this.viewEmployee = new ViewEmployee();
        this.employeeService = new EmployeeServiceImpl();
    }
    public void add() {
        Employee employee = new Employee(
                getDataFromConsole.getString("name"),
                getDataFromConsole.getString("surname"),
                getDataFromConsole.getInteger("age"),
                getDataFromConsole.getString("position"),
                getDataFromConsole.getInteger("level"),
                getDataFromConsole.getInteger("salary"),
                getDataFromConsole.getString("phone number"),
                new Department(
                        getDataFromConsole.getLong("department")));
        service.add(employee);
        view.added(employee);
    }
    public void addService() {
        viewEmployee.addService();
        Long employeeId = getDataFromConsole.getLong("employee_id");
        Long serviceId = getDataFromConsole.getLong("service_id");
        employeeService.addService(employeeId, serviceId);
        viewEmployee.addedService(employeeId, serviceId);
    }
    public void retrieveAll() {
        view.showAll();
        for (Employee employee : service.retrieveAll()) {
            view.show(employee);
            for (Service service : employee.getServices()) {
                viewService.show(service);
            }
        }
    }
    public void change() {
        view.update();
        Optional<Employee> employee = retrieveById();
        String field = getDataFromConsole.getString("select field");
        switch (field) {
            case "name":
                employee.get().setName(getDataFromConsole.getString("name"));
                break;
            case "surname":
                employee.get().setSurname(getDataFromConsole.getString("surname"));
                break;
            case "age":
                employee.get().setAge(getDataFromConsole.getInteger("age"));
                break;
            case "position":
                employee.get().setPosition(getDataFromConsole.getString("position"));
                break;
            case "level":
                employee.get().setLevel(getDataFromConsole.getInteger("level"));
                break;
            case "salary":
                employee.get().setSalary(getDataFromConsole.getInteger("salary"));
                break;
            case "phone_number":
                employee.get().setPhoneNumber(getDataFromConsole.getString("phone_number"));
                break;
        }
        service.change(employee, field);
        view.updated(field);
    }
    public Optional<Employee> retrieveById() {
        Optional<Employee> employeeOptional = service.retrieveById(
                (getDataFromConsole.getLong("id")));
        view.showById(employeeOptional);
        for (Service service : employeeOptional.get().getServices()) {
            viewService.showById(Optional.ofNullable(service));
        }
        return employeeOptional;
    }
}
