package com.solvd.carservice.domain.controller.admin;

import com.solvd.carservice.domain.Client;
import com.solvd.carservice.domain.controller.Generator;
import com.solvd.carservice.exception.TableException;
import com.solvd.carservice.service.ClientService;
import com.solvd.carservice.service.impl.ClientServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import java.util.Optional;

public class ClientController extends AbstractController {
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
        Client client = new Client(getDataFromConsole.getStringFromConsole("name"),
                getDataFromConsole.getStringFromConsole("surname"),
                getDataFromConsole.getStringFromConsole("phone number"),
                getDataFromConsole.getDateFromConsole("birthday"));
        ClientService clientService = new ClientServiceImpl();
        clientService.add(client);
        LOGGER.info("Client - " + client.getName() + client.getSurname() + " - was added");
    }
    public void retrieveAll() {
        LOGGER.info("List of clients");
        for (Client client : new ClientServiceImpl().retrieveAll()) {
            LOGGER.info(
                    "Client id - " + client.getId() + "|" +
                    "name - " + client.getName() + "|" +
                    "surname - " + client.getSurname() + "|" +
                    "phone number - " + client.getPhoneNumber() + "|" +
                    "birthday - " + client.getBirthday());
        }
    }
    public void retrieveById() {
        Optional<Client> clientOptional = new ClientServiceImpl().retrieveById((getDataFromConsole.getLongFromConsole("id")));
        LOGGER.info(
                "Client id - " + clientOptional.get().getId() + "|" +
                "name - " + clientOptional.get().getName() + "|" +
                "surname - " + clientOptional.get().getSurname() + "|" +
                "phone number - " + clientOptional.get().getPhoneNumber() + "|" +
                "birthday - " + clientOptional.get().getBirthday());
    }
    public void change() {
    }
    public void removeById() {
        LOGGER.info("Following car will be deleted");
        ClientService clientService = new ClientServiceImpl();
        clientService.removeById(getDataFromConsole.getLongFromConsole("id"));
    }
}
