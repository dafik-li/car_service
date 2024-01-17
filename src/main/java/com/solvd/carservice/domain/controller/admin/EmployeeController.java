package com.solvd.carservice.domain.controller.admin;

import com.solvd.carservice.domain.controller.Generator;
import com.solvd.carservice.domain.entity.Department;
import com.solvd.carservice.domain.entity.Employee;
import com.solvd.carservice.domain.entity.Service;
import com.solvd.carservice.domain.exception.AuthorizationException;
import com.solvd.carservice.domain.exception.TableException;
import com.solvd.carservice.domain.view.admin.ViewEmployee;
import com.solvd.carservice.domain.view.admin.ViewService;
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
    private final ViewEmployee viewEmployee;
    private final ViewService viewService;

    public EmployeeController() {
        this.viewEmployee = new ViewEmployee();
        this.viewService = new ViewService();
    }
    public void moderate() {
        viewConsoleAdminMenu.chooseModerateEmployee();
        String menu = scanner.nextLine();
        switch (menu) {
            case "1": selectInsertMethod(); break;
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
    public void selectInsertMethod() {
        viewConsoleAdminMenu.chooseInsertMethod();
        String menu = scanner.nextLine();
        switch (menu) {
            case "1": selectXmlParser(); break;
            case "2": add(); break;
            case "3": parser.addEmployee(menu);
            case "0": moderate(); break;
        }
        try {
            validator.validateAuthorization(menu);
        } catch (AuthorizationException e) {
            LOGGER.error(e.toString());
            selectInsertMethod();
        }
    }
    public void selectXmlParser() {
        viewConsoleAdminMenu.chooseXmlParser();
        String menu = scanner.nextLine();
        switch (menu) {
            case "1":
            case "2":
                parser.addEmployee(menu); break;
            case "0": selectInsertMethod(); break;
        }
        try {
            validator.validateStartPageMenu(menu);
        } catch (AuthorizationException e) {
            LOGGER.error(e.toString());
            selectXmlParser();
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
        viewEmployee.added(employee);
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
