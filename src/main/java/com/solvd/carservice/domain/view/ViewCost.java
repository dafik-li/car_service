package com.solvd.carservice.domain.view;

import com.solvd.carservice.domain.entity.Cost;
import org.apache.logging.log4j.LogManager;
import java.util.Optional;

public class ViewCost extends AbstractView<Cost>{
    static {
        System.setProperty("log4j.configurationFile", "log4j2.xml");
    }
    private final static org.apache.logging.log4j.core.Logger LOGGER = (org.apache.logging.log4j.core.Logger) LogManager.getLogger(ViewCost.class);

    @Override
    public void showAll() {
        LOGGER.info("List of costs ");
    }
    @Override
    public void show(Cost entity) {
        super.show(entity);
    }
    @Override
    public void added(Cost entity) {
        super.added(entity);
    }
    @Override
    public void update() {
        LOGGER.info("Update cost");
    }
    @Override
    public void updated(String field) {
        LOGGER.info("Cost " + field + " was updated");
    }
    @Override
    public void showById(Optional<Cost> entity) {
        super.showById(entity);
    }
    @Override
    public void delete() {
        LOGGER.info("Following cost will be deleted");
    }
    @Override
    public void successfulDeleted() {
        super.successfulDeleted();
    }
    /*
    public void showAll() {
        LOGGER.info("List of costs ");
    }
    public void show(Cost cost) {
        LOGGER.info(cost.toString());
    }
    public void added(Cost cost) {
        LOGGER.info(cost.toString() + " - was added");
    }
    public void update() {
        LOGGER.info("Update cost");
    }
    public void updated(String field) {
        LOGGER.info("Cost " + field + " was updated");
    }
    public void showById(Optional<Cost> costOptional) {
        LOGGER.info(costOptional.toString());
    }
    public void delete() {
        LOGGER.info("Following cost will be deleted");
    }
    public void successfulDeleted() {
        LOGGER.info("Successful deleted");
    }
     */
}
