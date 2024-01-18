package com.solvd.carservice.domain.controller.admin.navigate;

import com.solvd.carservice.domain.controller.admin.EmployeeController;
import com.solvd.carservice.domain.exception.AuthorizationException;
import com.solvd.carservice.domain.exception.TableException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

public class NavigateEmployee extends AbstractNavigate {
    static {
        System.setProperty("log4j.configurationFile", "log4j2.xml");
    }
    private final static Logger LOGGER = (Logger) LogManager.getLogger(NavigateEmployee.class);
    private final EmployeeController employeeController;

    public NavigateEmployee() {
        this.employeeController = new EmployeeController();
    }
    public void navigate() {
        viewConsoleAdminMenu.chooseModerateEmployee();
        String menu = scanner.nextLine();
        switch (menu) {
            case "1": selectInsertMethod(); break;
            case "2": employeeController.retrieveAll(); break;
            case "3": employeeController.retrieveById(); break;
            case "4": employeeController.change(); break;
            case "5": employeeController.removeById(); break;
            case "6": employeeController.addService(); break;
            case "0": new MainMenuNavigate().moderateController(); break;
        }
        try {
            validator.validateActionMenu(menu);
        } catch (TableException e) {
            LOGGER.error(e.toString());
            navigate();
        }
    }
    public void selectInsertMethod() {
        viewConsoleAdminMenu.chooseInsertMethod();
        String menu = scanner.nextLine();
        switch (menu) {
            case "1": selectXmlParser(); break;
            case "2": employeeController.add(); break;
            case "3": parser.addEmployee(menu);
            case "0": navigate(); break;
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
}
