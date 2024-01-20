package com.solvd.carservice.domain.controller.admin.navigate;

import com.solvd.carservice.domain.controller.admin.AbstractController;
import com.solvd.carservice.domain.controller.admin.EmployeeController;
import com.solvd.carservice.domain.entity.Employee;
import com.solvd.carservice.domain.exception.TableException;
import com.solvd.carservice.domain.parse.Parser;
import com.solvd.carservice.domain.view.admin.ViewEmployee;
import com.solvd.carservice.service.impl.EmployeeServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

public class EmployeeNavigator extends Navigator<Employee> {
    static {
        System.setProperty("log4j.configurationFile", "log4j2.xml");
    }
    private final static Logger LOGGER = (Logger) LogManager.getLogger(Navigator.class);
    private final EmployeeController employeeController;

    public EmployeeNavigator(AbstractController<Employee> controller, Parser<Employee> parser) {
        super(controller, parser);
        this.employeeController = new EmployeeController(new ViewEmployee(), new EmployeeServiceImpl());
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
            case "0": mainMenuNavigator.moderateController(); break;
        }
        try {
            validator.validateActionMenu(menu);
        } catch (TableException e) {
            LOGGER.error(e.toString());
            navigate();
        }
    }
}
