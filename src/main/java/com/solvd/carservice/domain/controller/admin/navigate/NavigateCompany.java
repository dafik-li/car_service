package com.solvd.carservice.domain.controller.admin.navigate;

import com.solvd.carservice.domain.controller.admin.CompanyController;
import com.solvd.carservice.domain.exception.AuthorizationException;
import com.solvd.carservice.domain.exception.TableException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

public class NavigateCompany extends AbstractNavigate {
    static {
        System.setProperty("log4j.configurationFile", "log4j2.xml");
    }
    private final static Logger LOGGER = (Logger) LogManager.getLogger(NavigateCompany.class);
    private final CompanyController companyController;

    public NavigateCompany() {
        this.companyController = new CompanyController();
    }
    public void navigate() {
        viewConsoleAdminMenu.chooseModerateMenu();
        String menu = scanner.nextLine();
        switch (menu) {
            case "1": selectInsertMethod(); break;
            case "2": companyController.retrieveAll(); break;
            case "3": companyController.retrieveById(); break;
            case "4": companyController.change(); break;
            case "5": companyController.removeById(); break;
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
            case "2": companyController.add(); break;
            case "3": parser.addCompany(menu);
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
                parser.addCompany(menu); break;
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
