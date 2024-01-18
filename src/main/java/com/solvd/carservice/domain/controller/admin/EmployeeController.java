package com.solvd.carservice.domain.controller.admin;

import com.solvd.carservice.domain.entity.Department;
import com.solvd.carservice.domain.entity.Employee;
import com.solvd.carservice.domain.entity.Service;
import com.solvd.carservice.service.EmployeeService;
import com.solvd.carservice.service.impl.EmployeeServiceImpl;
import java.util.Optional;

public class EmployeeController extends AbstractController {

    public void add() {
        Employee employee = new Employee(
                getDataFromConsole.getStringFromConsole("name"),
                getDataFromConsole.getStringFromConsole("surname"),
                getDataFromConsole.getIntegerFromConsole("age"),
                getDataFromConsole.getStringFromConsole("position"),
                getDataFromConsole.getIntegerFromConsole("level"),
                getDataFromConsole.getIntegerFromConsole("salary"),
                getDataFromConsole.getStringFromConsole("phone number"),
                new Department(
                        getDataFromConsole.getLongFromConsole("department")));
        EmployeeService employeeService = new EmployeeServiceImpl();
        employeeService.add(employee);
        viewEmployee.added(employee);
    }
    public void addService() {
        viewEmployee.addService();
        EmployeeService employeeService = new EmployeeServiceImpl();
        Long employeeId = getDataFromConsole.getLongFromConsole("employee_id");
        Long serviceId = getDataFromConsole.getLongFromConsole("service_id");
        employeeService.addService(employeeId, serviceId);
        viewEmployee.addedService(employeeId, serviceId);
    }
    public void retrieveAll() {
        viewEmployee.showAll();
        for (Employee employee : new EmployeeServiceImpl().retrieveAll()) {
            viewEmployee.show(employee);
            for (Service service : employee.getServices()) {
                viewService.show(service);
            }
        }
    }
    public void change() {
        viewEmployee.update();
        Optional<Employee> employee = retrieveById();
        EmployeeService employeeService = new EmployeeServiceImpl();
        String field = getDataFromConsole.getStringFromConsole("select field");
        switch (field) {
            case "name":
                employee.get().setName(getDataFromConsole.getStringFromConsole("name"));
                break;
            case "surname":
                employee.get().setSurname(getDataFromConsole.getStringFromConsole("surname"));
                break;
            case "age":
                employee.get().setAge(getDataFromConsole.getIntegerFromConsole("age"));
                break;
            case "position":
                employee.get().setPosition(getDataFromConsole.getStringFromConsole("position"));
                break;
            case "level":
                employee.get().setLevel(getDataFromConsole.getIntegerFromConsole("level"));
                break;
            case "salary":
                employee.get().setSalary(getDataFromConsole.getIntegerFromConsole("salary"));
                break;
            case "phone_number":
                employee.get().setPhoneNumber(getDataFromConsole.getStringFromConsole("phone_number"));
                break;
        }
        employeeService.change(employee, field);
        viewEmployee.updated(field);
    }
    public Optional<Employee> retrieveById() {
        Optional<Employee> employeeOptional = new EmployeeServiceImpl().retrieveById(
                (getDataFromConsole.getLongFromConsole("id")));
        viewEmployee.showById(employeeOptional);
        for (Service service : employeeOptional.get().getServices()) {
            viewService.showById(Optional.ofNullable(service));
        }
        return employeeOptional;
    }
    public void removeById() {
        viewEmployee.delete();
        EmployeeService employeeService = new EmployeeServiceImpl();
        employeeService.removeById(
                getDataFromConsole.getLongFromConsole("id"));
        viewEmployee.successfulDeleted();
    }
}
