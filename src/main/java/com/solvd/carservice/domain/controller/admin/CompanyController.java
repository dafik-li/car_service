package com.solvd.carservice.domain.controller.admin;

import com.solvd.carservice.domain.entity.Company;
import com.solvd.carservice.domain.controller.Generator;
import com.solvd.carservice.domain.exception.AuthorizationException;
import com.solvd.carservice.domain.exception.TableException;
import com.solvd.carservice.domain.view.ViewCompany;
import com.solvd.carservice.service.CompanyService;
import com.solvd.carservice.service.impl.CompanyServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import java.util.Optional;

public class CompanyController extends AbstractController {
    static {
        System.setProperty("log4j.configurationFile", "log4j2.xml");
    }
    private final static Logger LOGGER = (Logger) LogManager.getLogger(CompanyController.class);
    private final ViewCompany viewCompany;

    public CompanyController() {
        this.viewCompany = new ViewCompany();
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
            case "3": parser.addCompany(menu);
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
        viewConsoleMenu.chooseXmlParser();
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
    public void add() {
        Company company = new Company(
                getDataFromConsole.getStringFromConsole("name"),
                getDataFromConsole.getStringFromConsole("address"));
        CompanyService companyService = new CompanyServiceImpl();
        companyService.add(company);
        viewCompany.added(company);
    }
    public void retrieveAll() {
        viewCompany.showAll();
        for (Company company : new CompanyServiceImpl().retrieveAll()) {
            viewCompany.show(company);
        }
    }
    @Override
    public void change() {
        viewCompany.update();
        Optional<Company> company = retrieveById();
        CompanyService companyService = new CompanyServiceImpl();
        String field = getDataFromConsole.getStringFromConsole("select field");
        switch (field) {
            case "name":
                company.get().setName(getDataFromConsole.getStringFromConsole("name"));
                break;
            case "address":
                company.get().setAddress(getDataFromConsole.getStringFromConsole("address"));
                break;
        }
        companyService.change(company, field);
        viewCompany.updated(field);
    }
    public Optional<Company> retrieveById() {
        Optional<Company> companyOptional = new CompanyServiceImpl().retrieveById(
                (getDataFromConsole.getLongFromConsole("id")));
        viewCompany.showById(companyOptional);
        return companyOptional;
    }
    public void removeById() {
        viewCompany.delete();
        CompanyService companyService = new CompanyServiceImpl();
        companyService.removeById(
                getDataFromConsole.getLongFromConsole("id"));
        viewCompany.successfulDeleted();
    }
}
