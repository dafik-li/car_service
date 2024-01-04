package com.solvd.carservice.domain.controller.admin;

import com.solvd.carservice.domain.entity.Company;
import com.solvd.carservice.domain.controller.Generator;
import com.solvd.carservice.domain.exception.TableException;
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

    public void moderate() {
        consoleMenu.chooseModerateMenu();
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
        consoleMenu.chooseInsertMethod();
        String menu = scanner.nextLine();
        switch (menu) {
            case "1": ; break;
            case "2": ; break;
            case "3": ; add();break;
            case "0": System.exit(0);break;
        }
        Company company = new Company(
                getDataFromConsole.getStringFromConsole("name"),
                getDataFromConsole.getStringFromConsole("address"));
        CompanyService companyService = new CompanyServiceImpl();
        companyService.add(company);
        LOGGER.info(
                "Company - " +
                company.getName() +
                company.getAddress() +
                " - was added");
    }
    public void retrieveAll() {
        LOGGER.info("List of companies");
        for (Company company : new CompanyServiceImpl().retrieveAll()) {
            LOGGER.info(
                    "Company id - " + company.getId() + "|" +
                    "name - " + company.getName() + "|" +
                    "address - " + company.getAddress());
        }
    }
    @Override
    public void change() {
        LOGGER.info("Update company");
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
        LOGGER.info("Company " + field + " was changed");
    }
    public Optional<Company> retrieveById() {
        Optional<Company> companyOptional = new CompanyServiceImpl().retrieveById(
                (getDataFromConsole.getLongFromConsole("id")));
        LOGGER.info("|" +
                "Company id - " + companyOptional.get().getId() + "|" +
                "name - " + companyOptional.get().getName() + "|" +
                "address - " + companyOptional.get().getAddress() + "|");
        return companyOptional;
    }
    public void removeById() {
        LOGGER.info("Following company will be terminated");
        CompanyService companyService = new CompanyServiceImpl();
        companyService.removeById(
                getDataFromConsole.getLongFromConsole("id"));
        LOGGER.info("Successful deleted");
    }
}
