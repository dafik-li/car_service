package com.solvd.carservice.domain.controller.admin;

import com.solvd.carservice.domain.controller.Generator;
import com.solvd.carservice.domain.entity.Client;
import com.solvd.carservice.domain.entity.Cost;
import com.solvd.carservice.domain.entity.Order;
import com.solvd.carservice.domain.exception.TableException;
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
        Order order = new Order(
                getDataFromConsole.getDateFromConsole("date"),
                new Client(
                        getDataFromConsole.getLongFromConsole("client")),
                new Cost(
                        getDataFromConsole.getLongFromConsole("cost")));
        OrderService orderService = new OrderServiceImpl();
        orderService.add(order);
        display.addedOrder(order);
    }
    public void retrieveAll() {
        LOGGER.info("List of orders");
        for (Order order : new OrderServiceImpl().retrieveAll()) {
            LOGGER.info(
                    "Order id - " + order.getId() + "|" +
                    "date - " + order.getDate() + "[" +
                    "client id - " + order.getClientId().getId() + "|" +
                    "name - " + order.getClientId().getName() + "|" +
                    "surname - " + order.getClientId().getSurname() + "|" +
                    "phone number - " + order.getClientId().getPhoneNumber() + "|" +
                    "birthday - " + order.getClientId().getBirthday() + "][" +
                    "cost id - " + order.getCostId().getId() + "|" +
                    "cost - " + order.getCostId().getCost() + "[" +
                    "service id - " + order.getCostId().getServiceId().getId() + "|" +
                    "name - " + order.getCostId().getServiceId().getName() + "|" +
                    "price - " + order.getCostId().getServiceId().getPrice() + "|" +
                    "hours to do - " + order.getCostId().getServiceId().getHoursToDo() + "][" +
                    "car id - " + order.getCostId().getServiceId().getCarId().getId() + "|" +
                    "brand - " + order.getCostId().getServiceId().getCarId().getBrand() + "|" +
                    "model - " + order.getCostId().getServiceId().getCarId().getModel() + "|" +
                    "year - " + order.getCostId().getServiceId().getCarId().getYear() + "][" +
                    "department id - " + order.getCostId().getServiceId().getDepartmentId().getId() + "|" +
                    "name - " + order.getCostId().getServiceId().getDepartmentId().getName() + "][" +
                    "company id - " + order.getCostId().getServiceId().getDepartmentId().getCompanyId().getId() + "|" +
                    "name - " + order.getCostId().getServiceId().getDepartmentId().getCompanyId().getName() + "|" +
                    "address - " + order.getCostId().getServiceId().getDepartmentId().getCompanyId().getAddress() + "][" +
                    "detail id - " + order.getCostId().getDetailId().getId() + "|" +
                    "name - " + order.getCostId().getDetailId().getName() + "|" +
                    "price - " + order.getCostId().getDetailId().getPrice() + "|" +
                    "in stock - " + order.getCostId().getDetailId().getInStock() + "|" +
                    "delivery days - " + order.getCostId().getDetailId().getDeliveryDays() + "]");
        }
    }
    public void change() {
        LOGGER.info("Update order");
        Optional<Order> order = retrieveById();
        OrderService orderService = new OrderServiceImpl();
        String field = getDataFromConsole.getStringFromConsole("select field");
        switch (field) {
            case "date":
                order.get().setDate(getDataFromConsole.getDateFromConsole("date"));
                break;
        }
        orderService.change(order, field);
        LOGGER.info("Order " + field + " was changed");
    }
    public Optional<Order> retrieveById() {
        Optional<Order> orderOptional = new OrderServiceImpl().retrieveById(
                (getDataFromConsole.getLongFromConsole("id")));
        LOGGER.info("\n|" +
                "Order id - " + orderOptional.get().getId() + "|" +
                "date - " + orderOptional.get().getDate() + "\n[" +
                "client id - " + orderOptional.get().getClientId().getId() + "|" +
                "name - " + orderOptional.get().getClientId().getName() + "|" +
                "surname - " + orderOptional.get().getClientId().getSurname() + "|" +
                "phone number - " + orderOptional.get().getClientId().getPhoneNumber() + "|" +
                "birthday - " + orderOptional.get().getClientId().getBirthday() + "]\n[" +
                "cost id - " + orderOptional.get().getCostId().getId() + "|" +
                "cost - " + orderOptional.get().getCostId().getCost() + "\n[" +
                "service id - " + orderOptional.get().getCostId().getServiceId().getId() + "|" +
                "name - " + orderOptional.get().getCostId().getServiceId().getName() + "|" +
                "price - " + orderOptional.get().getCostId().getServiceId().getPrice() + "|" +
                "hours to do - " + orderOptional.get().getCostId().getServiceId().getHoursToDo() + "]\n[" +
                "car id - " + orderOptional.get().getCostId().getServiceId().getCarId().getId() + "|" +
                "brand - " + orderOptional.get().getCostId().getServiceId().getCarId().getBrand() + "|" +
                "model - " + orderOptional.get().getCostId().getServiceId().getCarId().getModel() + "|" +
                "year - " + orderOptional.get().getCostId().getServiceId().getCarId().getYear() + "]\n[" +
                "department id - " + orderOptional.get().getCostId().getServiceId().getDepartmentId().getId() + "|" +
                "name - " + orderOptional.get().getCostId().getServiceId().getDepartmentId().getName() + "]\n[" +
                "company id - " + orderOptional.get().getCostId().getServiceId().getDepartmentId().getCompanyId().getId() + "|" +
                "name - " + orderOptional.get().getCostId().getServiceId().getDepartmentId().getCompanyId().getName() + "|" +
                "address - " + orderOptional.get().getCostId().getServiceId().getDepartmentId().getCompanyId().getAddress() + "]\n[" +
                "detail id - " + orderOptional.get().getCostId().getDetailId().getId() + "|" +
                "name - " + orderOptional.get().getCostId().getDetailId().getName() + "|" +
                "price - " + orderOptional.get().getCostId().getDetailId().getPrice() + "|" +
                "in stock - " + orderOptional.get().getCostId().getDetailId().getInStock() + "|" +
                "delivery days - " + orderOptional.get().getCostId().getDetailId().getDeliveryDays() + "]");
        return orderOptional;
    }
    public void removeById() {
        LOGGER.info("Following order will be deleted");
        OrderService orderService = new OrderServiceImpl();
        orderService.removeById(
                getDataFromConsole.getLongFromConsole("id"));
        LOGGER.info("Successful deleted");
    }
}
