package com.solvd.carservice.domain.controller.admin;

import com.solvd.carservice.domain.Cost;
import com.solvd.carservice.domain.Detail;
import com.solvd.carservice.domain.Service;
import com.solvd.carservice.domain.controller.Generator;
import com.solvd.carservice.exception.TableException;
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
        Cost cost = new Cost(
                getDataFromConsole.getDoubleFromConsole("cost"),
                new Service(getDataFromConsole.getLongFromConsole("service")),
                new Detail(getDataFromConsole.getLongFromConsole("detail")));
        CostService costService = new CostServiceImpl();
        costService.add(cost);
        LOGGER.info("Cost - " + cost.getCost() + cost.getServiceId() + cost.getDetailId() + " - was added");
    }
    public void retrieveAll() {
        LOGGER.info("List of costs");
        for (Cost cost : new CostServiceImpl().retrieveAll()) {
            LOGGER.info(
                    "Cost id - " + cost.getId() + "|" +
                    "cost - " + cost.getCost() + "|" +
                    "service - " + cost.getServiceId() + "|" +
                    "detail - " + cost.getDetailId());
        }
    }
    public void change() {
    }
    public void retrieveById() {
        Optional<Cost> costOptional = new CostServiceImpl().retrieveById((getDataFromConsole.getLongFromConsole("id")));
        LOGGER.info(
                "Cost id - " + costOptional.get().getId() + "|" +
                "cost - " + costOptional.get().getCost() + "|" +
                "service - " + costOptional.get().getServiceId()  + "|" +
                "detail - " + costOptional.get().getDetailId());
    }
    public void removeById() {
        LOGGER.info("Following cost will be deleted");
        CostService costService = new CostServiceImpl();
        costService.removeById(getDataFromConsole.getLongFromConsole("id"));
    }
}
