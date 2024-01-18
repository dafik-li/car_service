package com.solvd.carservice.domain.controller.admin;

import com.solvd.carservice.domain.entity.Client;
import com.solvd.carservice.domain.entity.Order;
import com.solvd.carservice.service.ClientService;
import com.solvd.carservice.service.impl.ClientServiceImpl;
import java.util.Optional;

public class ClientController extends AbstractController {

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
