package com.solvd.carservice.domain.controller.admin;

import com.solvd.carservice.domain.Client;
import com.solvd.carservice.domain.Cost;
import com.solvd.carservice.domain.Order;
import com.solvd.carservice.domain.controller.Generator;
import com.solvd.carservice.exception.TableException;
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
        Order order = new Order(
                getDataFromConsole.getDateFromConsole("date"),
                new Client(getDataFromConsole.getLongFromConsole("client")),
                new Cost(getDataFromConsole.getLongFromConsole("cost")));
        OrderService orderService = new OrderServiceImpl();
        orderService.add(order);
        LOGGER.info("Order - " + order.getDate() + order.getClientId() + order.getCostId() + " - was added");
    }
    public void retrieveAll() {
        LOGGER.info("List of orders");
        for (Order order : new OrderServiceImpl().retrieveAll()) {
            LOGGER.info(
                    "Order id - " + order.getId() + "|" +
                    "date - " + order.getDate() + "|" +
                    "client - " + order.getClientId() + "|" +
                    "cost - " + order.getCostId());
        }
    }
    public void change() {
    }
    public void retrieveById() {
        Optional<Order> orderOptional = new OrderServiceImpl().retrieveById((getDataFromConsole.getLongFromConsole("id")));
        LOGGER.info(
                "Order id - " + orderOptional.get().getId() + "|" +
                "date - " + orderOptional.get().getDate() + "|" +
                "client - " + orderOptional.get().getClientId()  + "|" +
                "cost - " + orderOptional.get().getCostId());
    }
    public void removeById() {
        LOGGER.info("Following order will be deleted");
        OrderService orderService = new OrderServiceImpl();
        orderService.removeById(getDataFromConsole.getLongFromConsole("id"));
    }
}
