package com.solvd.carservice.domain.controller.admin;

import com.solvd.carservice.domain.controller.Generator;
import com.solvd.carservice.domain.entity.Cost;
import com.solvd.carservice.domain.entity.Detail;
import com.solvd.carservice.domain.entity.Service;
import com.solvd.carservice.domain.exception.AuthorizationException;
import com.solvd.carservice.domain.exception.TableException;
import com.solvd.carservice.domain.view.ViewCost;
import com.solvd.carservice.service.CostService;
import com.solvd.carservice.service.impl.CostServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import java.util.Optional;

public class CostController extends AbstractController {
    static {
        System.setProperty("log4j.configurationFile", "log4j2.xml");
    }
    private final static Logger LOGGER = (Logger) LogManager.getLogger(CarController.class);
    private final ViewCost viewCost;

    public CostController() {
        this.viewCost = new ViewCost();
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
    }public void selectInsertMethod() {
        viewConsoleMenu.chooseInsertMethod();
        String menu = scanner.nextLine();
        switch (menu) {
            case "1": selectXmlParser(); break;
            case "2": add(); break;
            case "3": parser.addCost(menu);
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
                parser.addCost(menu); break;
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
        Cost cost = new Cost(
                getDataFromConsole.getDoubleFromConsole("cost"),
                new Service(
                        getDataFromConsole.getLongFromConsole("service")),
                new Detail(
                        getDataFromConsole.getLongFromConsole("detail")));
        CostService costService = new CostServiceImpl();
        costService.add(cost);
        viewCost.added(cost);
    }
    public void retrieveAll() {
        viewCost.showAll();
        for (Cost cost : new CostServiceImpl().retrieveAll()) {
            viewCost.show(cost);
        }
    }
    public void change() {
        viewCost.update();
        Optional<Cost> cost = retrieveById();
        CostService costService = new CostServiceImpl();
        String field = getDataFromConsole.getStringFromConsole("select field");
        switch (field) {
            case "cost":
                cost.get().setCost(getDataFromConsole.getDoubleFromConsole("cost"));
                break;
        }
        costService.change(cost, field);
        viewCost.updated(field);
    }
    public Optional<Cost> retrieveById() {
        Optional<Cost> costOptional = new CostServiceImpl().retrieveById(
                (getDataFromConsole.getLongFromConsole("id")));
        viewCost.showById(costOptional);
        return costOptional;
    }
    public void removeById() {
        viewCost.delete();
        CostService costService = new CostServiceImpl();
        costService.removeById(
                getDataFromConsole.getLongFromConsole("id"));
        viewCost.successfulDeleted();
    }
}
