package com.solvd.carservice.domain.controller.admin;

import com.solvd.carservice.domain.controller.Generator;
import com.solvd.carservice.domain.entity.Company;
import com.solvd.carservice.domain.entity.Department;
import com.solvd.carservice.domain.exception.AuthorizationException;
import com.solvd.carservice.domain.exception.TableException;
import com.solvd.carservice.service.DepartmentService;
import com.solvd.carservice.service.impl.DepartmentServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import java.util.Optional;

public class DepartmentController extends AbstractController {
    static {
        System.setProperty("log4j.configurationFile", "log4j2.xml");
    }
    private final static Logger LOGGER = (Logger) LogManager.getLogger(DepartmentController.class);

    public void moderate() {
        consoleMenu.chooseModerateMenu();
        String menu = scanner.nextLine();
        switch (menu) {
            case "1": selectInsertMethod(); break;
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
    public void selectInsertMethod() {
        consoleMenu.chooseInsertMethod();
        String menu = scanner.nextLine();
        switch (menu) {
            case "1": selectXmlParser(); break;
            case "2": add(); break;
            case "0": moderate(); break;
        }
        try {
            validator.validateStartPageMenu(menu);
        } catch (AuthorizationException e) {
            LOGGER.error(e.toString());
            selectInsertMethod();
        }
    }
    public void selectXmlParser() {
        consoleMenu.chooseXmlParser();
        String menu = scanner.nextLine();
        switch (menu) {
            case "1": staxParser.addDepartment(); break;
            case "2": jaxbParser.addDepartment(); break;
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
        Department department =
                new Department(
                        getDataFromConsole.getStringFromConsole("name"),
                new Company(
                        getDataFromConsole.getLongFromConsole("company")));
        DepartmentService departmentService = new DepartmentServiceImpl();
        departmentService.add(department);
        display.addedDepartment(department);
    }
    public void retrieveAll() {
        LOGGER.info("List of departments");
        for (Department department : new DepartmentServiceImpl().retrieveAll()) {
            LOGGER.info(
                    "Department id - " + department.getId() + "|" +
                    "name - " + department.getName() + "[" +
                    "company id - " + department.getCompanyId().getId() + "|" +
                    "name - " + department.getCompanyId().getName() + "|" +
                    "address - " + department.getCompanyId().getAddress() + "]");
        }
    }
    public void change() {
        LOGGER.info("Update department");
        Optional<Department> department = retrieveById();
        DepartmentService departmentService = new DepartmentServiceImpl();
        String field = getDataFromConsole.getStringFromConsole("select field");
        switch (field) {
            case "name":
                department.get().setName(getDataFromConsole.getStringFromConsole("name"));
                break;
        }
        departmentService.change(department, field);
        LOGGER.info("Cost " + field + " was changed");
    }
    public Optional<Department> retrieveById() {
        Optional<Department> departmentOptional =
                new DepartmentServiceImpl().retrieveById(
                        (getDataFromConsole.getLongFromConsole("id")));
        LOGGER.info("\n|" +
                "Department id - " + departmentOptional.get().getId() + "|" +
                "name - " + departmentOptional.get().getName() + "\n[" +
                "company id - " + departmentOptional.get().getCompanyId().getId() + "|" +
                "name - " + departmentOptional.get().getCompanyId().getName() + "|" +
                "address - " + departmentOptional.get().getCompanyId().getAddress() + "]");
        return departmentOptional;
    }
    public void removeById() {
        LOGGER.info("Following department will be redundant");
        DepartmentService departmentService = new DepartmentServiceImpl();
        departmentService.removeById(
                getDataFromConsole.getLongFromConsole("id"));
        LOGGER.info("Successful deleted");
    }
}
