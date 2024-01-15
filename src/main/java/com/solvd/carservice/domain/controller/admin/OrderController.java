package com.solvd.carservice.domain.controller.admin;

import com.solvd.carservice.domain.controller.Generator;
import com.solvd.carservice.domain.entity.Client;
import com.solvd.carservice.domain.entity.Cost;
import com.solvd.carservice.domain.entity.Order;
import com.solvd.carservice.domain.exception.AuthorizationException;
import com.solvd.carservice.domain.exception.TableException;
import com.solvd.carservice.domain.view.ViewOrder;
import com.solvd.carservice.service.OrderService;
import com.solvd.carservice.service.impl.OrderServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import java.util.Optional;

public class OrderController extends AbstractController {
    static {
        System.setProperty("log4j.configurationFile", "log4j2.xml");
    }
    private final static Logger LOGGER = (Logger) LogManager.getLogger(CarController.class);
    private final ViewOrder viewOrder;

    public OrderController() {
        this.viewOrder = new ViewOrder();
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
            case "3": parser.addOrder(menu);
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
                parser.addOrder(menu); break;
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
        Order order = new Order(
                getDataFromConsole.getDateFromConsole("date"),
                new Client(
                        getDataFromConsole.getLongFromConsole("client")),
                new Cost(
                        getDataFromConsole.getLongFromConsole("cost")));
        OrderService orderService = new OrderServiceImpl();
        orderService.add(order);
        viewOrder.added(order);
    }
    public void retrieveAll() {
        viewOrder.showAll();
        for (Order order : new OrderServiceImpl().retrieveAll()) {
            viewOrder.show(order);
        }
    }
    public void change() {
        viewOrder.update();
        Optional<Order> order = retrieveById();
        OrderService orderService = new OrderServiceImpl();
        String field = getDataFromConsole.getStringFromConsole("select field");
        switch (field) {
            case "date":
                order.get().setDate(getDataFromConsole.getDateFromConsole("date"));
                break;
        }
        orderService.change(order, field);
        viewOrder.updated(field);
    }
    public Optional<Order> retrieveById() {
        Optional<Order> orderOptional = new OrderServiceImpl().retrieveById(
                (getDataFromConsole.getLongFromConsole("id")));
        viewOrder.showById(orderOptional);
        return orderOptional;
    }
    public void removeById() {
        viewOrder.delete();
        OrderService orderService = new OrderServiceImpl();
        orderService.removeById(
                getDataFromConsole.getLongFromConsole("id"));
        viewOrder.successfulDeleted();
    }
}
