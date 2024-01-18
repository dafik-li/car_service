package com.solvd.carservice.domain.controller.admin.navigate;

import com.solvd.carservice.domain.controller.admin.ServiceController;
import com.solvd.carservice.domain.exception.AuthorizationException;
import com.solvd.carservice.domain.exception.TableException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

public class NavigateService extends AbstractNavigate {
    static {
        System.setProperty("log4j.configurationFile", "log4j2.xml");
    }
    private final static Logger LOGGER = (Logger) LogManager.getLogger(NavigateService.class);
    private final ServiceController serviceController;

    public NavigateService() {
        this.serviceController = new ServiceController();
    }
    public void navigate() {
        viewConsoleAdminMenu.chooseModerateService();
        String menu = scanner.nextLine();
        switch (menu) {
            case "1": selectInsertMethod(); break;
            case "2": serviceController.retrieveAll(); break;
            case "3": serviceController.retrieveById(); break;
            case "4": serviceController.change(); break;
            case "5": serviceController.removeById(); break;
            case "6": serviceController.addEmployee(); break;
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
            case "2": serviceController.add(); break;
            case "3": parser.addService(menu);
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
                parser.addService(menu); break;
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
