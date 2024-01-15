package com.solvd.carservice.domain.controller.admin;

import com.solvd.carservice.domain.controller.Generator;
import com.solvd.carservice.domain.entity.Client;
import com.solvd.carservice.domain.entity.Cost;
import com.solvd.carservice.domain.entity.Order;
import com.solvd.carservice.domain.exception.AuthorizationException;
import com.solvd.carservice.domain.exception.TableException;
import com.solvd.carservice.domain.view.ViewClient;
import com.solvd.carservice.domain.view.ViewOrder;
import com.solvd.carservice.service.ClientService;
import com.solvd.carservice.service.impl.ClientServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import java.util.Optional;

public class ClientController extends AbstractController {
    static {
        System.setProperty("log4j.configurationFile", "log4j2.xml");
    }
    private final static Logger LOGGER = (Logger) LogManager.getLogger(ClientController.class);
    private final ViewClient viewClient;
    private final ViewOrder viewOrder;

    public ClientController() {
        this.viewClient = new ViewClient();
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
            case "3": parser.addClient(menu);
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
                parser.addClient(menu); break;
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
        Client client =
                new Client(
                        getDataFromConsole.getStringFromConsole("name"),
                        getDataFromConsole.getStringFromConsole("surname"),
                        getDataFromConsole.getStringFromConsole("phone number"),
                        getDataFromConsole.getDateFromConsole("birthday"));
        ClientService clientService = new ClientServiceImpl();
        clientService.add(client);
        viewClient.added(client);
    }
    public void retrieveAll() {
        viewClient.showAll();
        for (Client client : new ClientServiceImpl().retrieveAll()) {
            viewClient.show(client);
            for (Order order : client.getOrders()) {
                viewOrder.show(order);
            }
        }
    }
    public void change() {
        viewClient.update();
        Optional<Client> client = retrieveById();
        ClientService clientService = new ClientServiceImpl();
        String field = getDataFromConsole.getStringFromConsole("select field");
        switch (field) {
            case "name":
                client.get().setName(getDataFromConsole.getStringFromConsole("name"));
                break;
            case "surname":
                client.get().setSurname(getDataFromConsole.getStringFromConsole("surname"));
                break;
            case "phone_number":
                client.get().setPhoneNumber(getDataFromConsole.getStringFromConsole("phone_number"));
                break;
            case "birthday":
                client.get().setBirthday(getDataFromConsole.getDateFromConsole("birthday"));
                break;
        }
        clientService.change(client, field);
        viewClient.updated(field);
    }
    public Optional<Client> retrieveById() {
        Optional<Client> clientOptional = new ClientServiceImpl().retrieveById(
                (getDataFromConsole.getLongFromConsole("id")));
        viewClient.showById(clientOptional);
        for (Order order : clientOptional.get().getOrders()) {
            viewOrder.showById(Optional.ofNullable(order));
        }
        return clientOptional;
    }
    public void removeById() {
        viewClient.delete();
        ClientService clientService = new ClientServiceImpl();
        clientService.removeById(
                getDataFromConsole.getLongFromConsole("id"));
        viewClient.successfulDeleted();
    }
}
