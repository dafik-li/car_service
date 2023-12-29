package com.solvd.carservice.domain.controller.admin;

import com.solvd.carservice.domain.Company;
import com.solvd.carservice.domain.controller.Generator;
import com.solvd.carservice.exception.TableException;
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
    public void change() {
    }
    public void retrieveById() {
        Optional<Company> companyOptional = new CompanyServiceImpl().retrieveById(
                (getDataFromConsole.getLongFromConsole("id")));
        LOGGER.info(
                "Company id - " + companyOptional.get().getId() + "|" +
                "name - " + companyOptional.get().getName() + "|" +
                "address - " + companyOptional.get().getAddress());
    }
    public void removeById() {
        LOGGER.info("Following company will be terminated");
        CompanyService companyService = new CompanyServiceImpl();
        companyService.removeById(
                getDataFromConsole.getLongFromConsole("id"));
    }
}
