package com.solvd.carservice.domain.view.admin;

import com.solvd.carservice.domain.entity.Order;
import org.apache.logging.log4j.LogManager;
import java.util.Optional;

public class ViewOrder extends AbstractView<Order> {
    static {
        System.setProperty("log4j.configurationFile", "log4j2.xml");
    }
    private final static org.apache.logging.log4j.core.Logger LOGGER = (org.apache.logging.log4j.core.Logger) LogManager.getLogger(ViewOrder.class);

    @Override
    public void showAll() {
        LOGGER.info("List of orders ");
    }
    @Override
    public void show(Order entity) {
        super.show(entity);
    }
    @Override
    public void added(Order entity) {
        super.added(entity);
    }
    @Override
    public void update() {
        LOGGER.info("Update order");
    }
    @Override
    public void updated(String field) {
        LOGGER.info("Order " + field + " was updated");
    }
    @Override
    public void showById(Optional<Order> entity) {
        super.showById(entity);
    }
    @Override
    public void delete() {
        LOGGER.info("Following order will be deleted");
    }
    @Override
    public void successfulDeleted() {
        super.successfulDeleted();
    }
}
