package com.solvd.carservice.domain.controller.admin;

import com.solvd.carservice.domain.controller.Generator;
import com.solvd.carservice.domain.entity.Company;
import com.solvd.carservice.domain.entity.Department;
import com.solvd.carservice.domain.exception.AuthorizationException;
import com.solvd.carservice.domain.exception.TableException;
import com.solvd.carservice.domain.view.ViewDepartment;
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
    private final ViewDepartment viewDepartment;

    public DepartmentController() {
        this.viewDepartment = new ViewDepartment();
    }

    public void moderate() {
        viewConsoleMenu.chooseModerateMenu();
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
        viewConsoleMenu.chooseInsertMethod();
        String menu = scanner.nextLine();
        switch (menu) {
            case "1": selectXmlParser(); break;
            case "2": add(); break;
            case "3": parser.addDepartment(menu);
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
        viewConsoleMenu.chooseXmlParser();
        String menu = scanner.nextLine();
        switch (menu) {
            case "1":
            case "2":
                parser.addDepartment(menu); break;
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
        viewDepartment.added(department);
    }
    public void retrieveAll() {
        viewDepartment.showAll();
        for (Department department : new DepartmentServiceImpl().retrieveAll()) {
            viewDepartment.show(department);
        }
    }
    public void change() {
        viewDepartment.update();
        Optional<Department> department = retrieveById();
        DepartmentService departmentService = new DepartmentServiceImpl();
        String field = getDataFromConsole.getStringFromConsole("select field");
        switch (field) {
            case "name":
                department.get().setName(getDataFromConsole.getStringFromConsole("name"));
                break;
        }
        departmentService.change(department, field);
        viewDepartment.updated(field);
    }
    public Optional<Department> retrieveById() {
        Optional<Department> departmentOptional =
                new DepartmentServiceImpl().retrieveById(
                        (getDataFromConsole.getLongFromConsole("id")));
        viewDepartment.showById(departmentOptional);
        return departmentOptional;
    }
    public void removeById() {
        viewDepartment.delete();
        DepartmentService departmentService = new DepartmentServiceImpl();
        departmentService.removeById(
                getDataFromConsole.getLongFromConsole("id"));
        viewDepartment.successfulDeleted();
    }
}
