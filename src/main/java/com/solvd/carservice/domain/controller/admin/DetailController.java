package com.solvd.carservice.domain.controller.admin;

import com.solvd.carservice.domain.controller.Generator;
import com.solvd.carservice.domain.entity.Car;
import com.solvd.carservice.domain.entity.Cost;
import com.solvd.carservice.domain.entity.Department;
import com.solvd.carservice.domain.entity.Detail;
import com.solvd.carservice.domain.exception.AuthorizationException;
import com.solvd.carservice.domain.exception.TableException;
import com.solvd.carservice.domain.view.ViewCost;
import com.solvd.carservice.domain.view.ViewDetail;
import com.solvd.carservice.service.DetailService;
import com.solvd.carservice.service.impl.DetailServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import java.util.Optional;

public class DetailController extends AbstractController {
    static {
        System.setProperty("log4j.configurationFile", "log4j2.xml");
    }
    private final static Logger LOGGER = (Logger) LogManager.getLogger(CarController.class);
    private final ViewDetail viewDetail;
    private final ViewCost viewCost;

    public DetailController() {
        this.viewDetail = new ViewDetail();
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
    }
    public void selectInsertMethod() {
        viewConsoleMenu.chooseInsertMethod();
        String menu = scanner.nextLine();
        switch (menu) {
            case "1": selectXmlParser(); break;
            case "2": add(); break;
            case "3": parser.addDetail(menu);
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
                parser.addDetail(menu); break;
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
        Detail detail = new Detail(
                getDataFromConsole.getStringFromConsole("name"),
                getDataFromConsole.getIntegerFromConsole("price"),
                new Car(
                        getDataFromConsole.getLongFromConsole("car")),
                getDataFromConsole.getBooleanFromConsole("in stock"),
                getDataFromConsole.getIntegerFromConsole("delivery days"));
        DetailService detailService = new DetailServiceImpl();
        detailService.add(detail);
        viewDetail.added(detail);
    }
    public void retrieveAll() {
        viewDetail.showAll();
        for (Detail detail : new DetailServiceImpl().retrieveAll()) {
            viewDetail.show(detail);
            for (Cost cost : detail.getCosts()) {
                viewCost.show(cost);
            }
        }
    }
    public void change() {
        viewDetail.update();
        Optional<Detail> detail = retrieveById();
        DetailService detailService = new DetailServiceImpl();
        String field = getDataFromConsole.getStringFromConsole("select field");
        switch (field) {
            case "name":
                detail.get().setName(getDataFromConsole.getStringFromConsole("name"));
                break;
            case "price":
                detail.get().setPrice(getDataFromConsole.getIntegerFromConsole("price"));
                break;
            case "in_stock":
                detail.get().setInStock(getDataFromConsole.getBooleanFromConsole("in_stock"));
                break;
            case "delivery_days":
                detail.get().setDeliveryDays(getDataFromConsole.getIntegerFromConsole("delivery_days"));
                break;
        }
        detailService.change(detail, field);
        viewDetail.updated(field);
    }
    public Optional<Detail> retrieveById() {
        Optional<Detail> detailOptional = new DetailServiceImpl().retrieveById(
                (getDataFromConsole.getLongFromConsole("id")));
        viewDetail.showById(detailOptional);
        for (Cost cost : detailOptional.get().getCosts()) {
            viewCost.showById(Optional.ofNullable(cost));
        }
        return detailOptional;
    }
    public void removeById() {
        viewDetail.delete();
        DetailService detailService = new DetailServiceImpl();
        detailService.removeById(
                getDataFromConsole.getLongFromConsole("id"));
        viewDetail.successfulDeleted();
    }
}
