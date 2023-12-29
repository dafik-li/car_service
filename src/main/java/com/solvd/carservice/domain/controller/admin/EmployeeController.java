package com.solvd.carservice.domain.controller.admin;

import com.solvd.carservice.domain.entity.Department;
import com.solvd.carservice.domain.entity.Employee;
import com.solvd.carservice.domain.controller.Generator;
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
        consoleMenu.chooseActionMenu();
        String menu = scanner.nextLine();
        switch (menu) {
            case "1": add(); break;
            case "2": retrieveAll(); break;
            case "3": retrieveById(); break;
            case "4": change(); break;
            case "5": removeById(); break;
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
                employee.getName() +
                employee.getSurname() +
                " - was added");
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
                    "department id - " + employee.getDepartmentId().getName() + "|" +
                    "name - " + employee.getDepartmentId().getName() + "][" +
                    "company id - " + employee.getDepartmentId().getCompanyId().getId() + "|" +
                    "name - " + employee.getDepartmentId().getCompanyId().getName() + "|" +
                    "address - " + employee.getDepartmentId().getCompanyId().getAddress() + "]");
        }
    }
    public void change() {
    }
    public void retrieveById() {
        Optional<Employee> employeeOptional = new EmployeeServiceImpl().retrieveById(
                (getDataFromConsole.getLongFromConsole("id")));
        LOGGER.info(
                "Employee id - " + employeeOptional.get().getId() + "|" +
                "name - " + employeeOptional.get().getName() + "|" +
                "surname - " + employeeOptional.get().getSurname() + "|" +
                "age - " + employeeOptional.get().getAge() + "|" +
                "position - " + employeeOptional.get().getPosition() + "|" +
                "level - " + employeeOptional.get().getLevel() + "|" +
                "salary - " + employeeOptional.get().getSalary() + "|" +
                "phone - " + employeeOptional.get().getPhoneNumber() + "|" +
                "department - " + employeeOptional.get().getDepartmentId());
    }
    public void removeById() {
        LOGGER.info("Following employee will be fired");
        EmployeeService employeeService = new EmployeeServiceImpl();
        employeeService.removeById(
                getDataFromConsole.getLongFromConsole("id"));
    }
}
