package com.solvd.carservice.domain.controller.admin;

import com.solvd.carservice.domain.entity.Client;
import com.solvd.carservice.domain.entity.Order;
import com.solvd.carservice.domain.view.admin.InterfaceView;
import com.solvd.carservice.domain.view.admin.ViewOrder;
import com.solvd.carservice.service.InterfaceService;
import java.util.Optional;

public class ClientController extends AbstractController<Client> {
    private final ViewOrder viewOrder;

    public ClientController(InterfaceView<Client> view, InterfaceService<Client> service) {
        super(view, service);
        this.viewOrder = new ViewOrder();
    }
    public void add() {
        Client client =
                new Client(
                        getDataFromConsole.getString("name"),
                        getDataFromConsole.getString("surname"),
                        getDataFromConsole.getString("phone number"),
                        getDataFromConsole.getDate("birthday"));
        service.add(client);
        view.added(client);
    }
    public void retrieveAll() {
        view.showAll();
        for (Client client : service.retrieveAll()) {
            view.show(client);
            for (Order order : client.getOrders()) {
                viewOrder.show(order);
            }
        }
    }
    public void change() {
        view.update();
        Optional<Client> client = retrieveById();
        String field = getDataFromConsole.getString("select field");
        switch (field) {
            case "name":
                client.get().setName(getDataFromConsole.getString("name"));
                break;
            case "surname":
                client.get().setSurname(getDataFromConsole.getString("surname"));
                break;
            case "phone_number":
                client.get().setPhoneNumber(getDataFromConsole.getString("phone_number"));
                break;
            case "birthday":
                client.get().setBirthday(getDataFromConsole.getDate("birthday"));
                break;
        }
        service.change(client, field);
        view.updated(field);
    }
    public Optional<Client> retrieveById() {
        Optional<Client> clientOptional = service.retrieveById(
                (getDataFromConsole.getLong("id")));
        view.showById(clientOptional);
        for (Order order : clientOptional.get().getOrders()) {
            viewOrder.showById(Optional.ofNullable(order));
        }
        return clientOptional;
    }
}
