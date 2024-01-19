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
                getDataFromConsole.getString("name"),
                getDataFromConsole.getString("surname"),
                getDataFromConsole.getInteger("age"),
                getDataFromConsole.getString("position"),
                getDataFromConsole.getInteger("level"),
                getDataFromConsole.getInteger("salary"),
                getDataFromConsole.getString("phone number"),
                new Department(
                        getDataFromConsole.getLong("department")));
        EmployeeService employeeService = new EmployeeServiceImpl();
        employeeService.add(employee);
        viewEmployee.added(employee);
    }
    public void addService() {
        viewEmployee.addService();
        EmployeeService employeeService = new EmployeeServiceImpl();
        Long employeeId = getDataFromConsole.getLong("employee_id");
        Long serviceId = getDataFromConsole.getLong("service_id");
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
        employeeService.change(employee, field);
        viewEmployee.updated(field);
    }
    public Optional<Employee> retrieveById() {
        Optional<Employee> employeeOptional = new EmployeeServiceImpl().retrieveById(
                (getDataFromConsole.getLong("id")));
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
                getDataFromConsole.getLong("id"));
        viewEmployee.successfulDeleted();
    }
}
