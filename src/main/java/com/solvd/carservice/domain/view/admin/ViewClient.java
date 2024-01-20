package com.solvd.carservice.domain.view.admin;

import com.solvd.carservice.domain.entity.Client;
import org.apache.logging.log4j.LogManager;
import java.util.Optional;

public class ViewClient extends AbstractView<Client> {
    static {
        System.setProperty("log4j.configurationFile", "log4j2.xml");
    }
    private final static org.apache.logging.log4j.core.Logger LOGGER = (org.apache.logging.log4j.core.Logger) LogManager.getLogger(ViewClient.class);

    @Override
    public void showAll() {
        LOGGER.info("List of clients ");
    }
    @Override
    public void show(Client entity) {
        super.show(entity);
    }
    @Override
    public void added(Client entity) {
        super.added(entity);
    }
    @Override
    public void update() {
        LOGGER.info("Update client");
    }
    @Override
    public void updated(String field) {
        LOGGER.info("Client " + field + " was updated");
    }
    @Override
    public void showById(Optional<Client> entity) {
        super.showById(entity);
    }
    @Override
    public void delete() {
        LOGGER.info("Following client will be deleted");
    }
    @Override
    public void successfulDeleted() {
        super.successfulDeleted();
    }
}
