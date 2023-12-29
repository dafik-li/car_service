package com.solvd.carservice.domain.controller.admin;

import com.solvd.carservice.domain.Company;
import com.solvd.carservice.domain.Department;
import com.solvd.carservice.domain.controller.Generator;
import com.solvd.carservice.exception.TableException;
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
        Department department =
                new Department(
                        getDataFromConsole.getStringFromConsole("name"),
                new Company(
                        getDataFromConsole.getLongFromConsole("company")));
        DepartmentService departmentService = new DepartmentServiceImpl();
        departmentService.add(department);
        LOGGER.info(
                "Department - " +
                department.getName() +
                department.getCompanyId() +
                " - was added");
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
    }
    public void retrieveById() {
        Optional<Department> departmentOptional =
                new DepartmentServiceImpl().retrieveById(
                        (getDataFromConsole.getLongFromConsole("id")));
        LOGGER.info(
                "Department id - " + departmentOptional.get().getId() + "|" +
                "name - " + departmentOptional.get().getName() + "|" +
                "company - " + departmentOptional.get().getCompanyId());
    }
    public void removeById() {
        LOGGER.info("Following department will be redundant");
        DepartmentService departmentService = new DepartmentServiceImpl();
        departmentService.removeById(
                getDataFromConsole.getLongFromConsole("id"));
    }
}
