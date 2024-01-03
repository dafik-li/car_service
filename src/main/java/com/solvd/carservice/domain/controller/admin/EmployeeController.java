package com.solvd.carservice.domain.controller.admin;

import com.solvd.carservice.domain.controller.Generator;
import com.solvd.carservice.domain.entity.Department;
import com.solvd.carservice.domain.entity.Employee;
import com.solvd.carservice.domain.exception.TableException;
import com.solvd.carservice.service.EmployeeService;
import com.solvd.carservice.service.impl.EmployeeServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import java.util.Optional;

public class EmployeeController extends AbstractController {
    static {
        System.setProperty("log4j.configurationFile", "log4j2.xml");
    }
    private final static Logger LOGGER = (Logger) LogManager.getLogger(EmployeeController.class);

    public void moderate() {
        consoleMenu.chooseModerateEmployee();
        String menu = scanner.nextLine();
        switch (menu) {
            case "1": add(); break;
            case "2": retrieveAll(); break;
            case "3": retrieveById(); break;
            case "4": change(); break;
            case "5": removeById(); break;
            case "6": addService(); break;
            case "0": new Generator().moderateController(); break;
        }
        try {
            validator.validateActionMenu(menu);
        } catch (TableException e) {
            LOGGER.error(e.toString());
            moderate();
        }
    }
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
        LOGGER.info(
                "Employee - " +
                employee.getName() + " " +
                employee.getSurname() +
                " - was added");
    }
    public void addService() {
        LOGGER.info("Add service to employee");
        EmployeeService employeeService = new EmployeeServiceImpl();
        Long employeeId = getDataFromConsole.getLongFromConsole("employee_id");
        Long serviceId = getDataFromConsole.getLongFromConsole("service_id");
        employeeService.addService(employeeId, serviceId);
        LOGGER.info("Service id - " + serviceId + " was assigned to the employee id - " + employeeId);
    }
    public void retrieveAll() {
        LOGGER.info("List of employees");
        for (Employee employee : new EmployeeServiceImpl().retrieveAll()) {
            LOGGER.info(
                    "Employee id - " + employee.getId() + "|" +
                    "name - " + employee.getName() + "|" +
                    "surname - " + employee.getSurname() + "|" +
                    "age - " + employee.getAge() + "|" +
                    "position - " + employee.getPosition() + "|" +
                    "level - " + employee.getLevel() + "|" +
                    "salary - " + employee.getSalary() + "|" +
                    "phone - " + employee.getPhoneNumber() + "[" +
                    "service id - " + employee.getService().getId() + "|" +
                    "name - " + employee.getService().getName() + "|" +
                    "price - " + employee.getService().getPrice() + "|" +
                    "hours to do - " + employee.getService().getHoursToDo() + "[" +
                    "department id - " + employee.getService().getDepartmentId().getId() + "|" +
                    "name - " + employee.getService().getDepartmentId().getName() + "][" +
                    "company id - " + employee.getService().getDepartmentId().getCompanyId().getId() + "|" +
                    "name - " + employee.getService().getDepartmentId().getCompanyId().getName() + "|" +
                    "address - " + employee.getService().getDepartmentId().getCompanyId().getAddress() + "]");
        }
    }
    public void change() {
        LOGGER.info("Update employee");
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
        LOGGER.info("Employee " + field + " was changed");
    }
    public Optional<Employee> retrieveById() {
        Optional<Employee> employeeOptional = new EmployeeServiceImpl().retrieveById(
                (getDataFromConsole.getLongFromConsole("id")));
        LOGGER.info("\n|" +
                "Employee id - " + employeeOptional.get().getId() + "|" +
                "name - " + employeeOptional.get().getName() + "|" +
                "surname - " + employeeOptional.get().getSurname() + "|" +
                "age - " + employeeOptional.get().getAge() + "|" +
                "position - " + employeeOptional.get().getPosition() + "|" +
                "level - " + employeeOptional.get().getLevel() + "|" +
                "salary - " + employeeOptional.get().getSalary() + "|" +
                "phone - " + employeeOptional.get().getPhoneNumber() + "[" +
                "service id - " + employeeOptional.get().getService().getId() + "|" +
                "name - " + employeeOptional.get().getService().getName() + "|" +
                "price - " + employeeOptional.get().getService().getPrice() + "|" +
                "hours to do - " + employeeOptional.get().getService().getHoursToDo() + "[" +
                "department id - " + employeeOptional.get().getService().getDepartmentId().getId() + "|" +
                "name - " + employeeOptional.get().getService().getDepartmentId().getName() + "][" +
                "company id - " + employeeOptional.get().getService().getDepartmentId().getCompanyId().getId() + "|" +
                "name - " + employeeOptional.get().getService().getDepartmentId().getCompanyId().getName() + "|" +
                "address - " + employeeOptional.get().getService().getDepartmentId().getCompanyId().getAddress() + "]");
        return employeeOptional;
    }
    public void removeById() {
        LOGGER.info("Following employee will be fired");
        EmployeeService employeeService = new EmployeeServiceImpl();
        employeeService.removeById(
                getDataFromConsole.getLongFromConsole("id"));
        LOGGER.info("Successful deleted");
    }
}
