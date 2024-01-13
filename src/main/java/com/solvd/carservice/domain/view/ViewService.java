package com.solvd.carservice.domain.view;

import com.solvd.carservice.domain.entity.Service;
import org.apache.logging.log4j.LogManager;
import java.util.Optional;

public class ViewService extends AbstractView<Service> {
    static {
        System.setProperty("log4j.configurationFile", "log4j2.xml");
    }
    private final static org.apache.logging.log4j.core.Logger LOGGER = (org.apache.logging.log4j.core.Logger) LogManager.getLogger(ViewService.class);

    @Override
    public void showAll() {
        LOGGER.info("List of services ");
    }
    @Override
    public void show(Service entity) {
        super.show(entity);
    }
    @Override
    public void added(Service entity) {
        super.added(entity);
    }
    @Override
    public void update() {
        LOGGER.info("Update service");
    }
    @Override
    public void updated(String field) {
        LOGGER.info("Service " + field + " was updated");
    }
    @Override
    public void showById(Optional<Service> entity) {
        super.showById(entity);
    }
    @Override
    public void delete() {
        LOGGER.info("Following service will be deleted");
    }
    @Override
    public void successfulDeleted() {
        super.successfulDeleted();
    }
    /*
    public void showAll() {
        LOGGER.info("List of services ");
    }
    public void show(Service service) {
        LOGGER.info(service.toString());
    }
    public void added(Service service) {
        LOGGER.info(service.toString() + " - was added");
    }
    public void update() {
        LOGGER.info("Update service");
    }
    public void updated(String field) {
        LOGGER.info("Service " + field + " was updated");
    }
    public void showById(Optional<Service> serviceOptional) {
        LOGGER.info(serviceOptional.toString());
    }
    public void delete() {
        LOGGER.info("Following service will be deleted");
    }
    public void successfulDeleted() {
        LOGGER.info("Successful deleted");
    }
     */
}
