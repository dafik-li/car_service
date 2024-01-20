package com.solvd.carservice.domain.view.admin;

import com.solvd.carservice.domain.entity.Car;
import org.apache.logging.log4j.LogManager;
import java.util.Optional;

public class ViewCar extends AbstractView<Car> {
    static {
        System.setProperty("log4j.configurationFile", "log4j2.xml");
    }
    private final static org.apache.logging.log4j.core.Logger LOGGER = (org.apache.logging.log4j.core.Logger) LogManager.getLogger(ViewCar.class);

    @Override
    public void showAll() {
        LOGGER.info("List of cars ");
    }
    @Override
    public void show(Car entity) {
        super.show(entity);
    }
    @Override
    public void added(Car entity) {
        super.added(entity);
    }
    @Override
    public void update() {
        LOGGER.info("Update car");
    }
    @Override
    public void updated(String field) {
        LOGGER.info("Car " + field + " was updated");
    }
    @Override
    public void showById(Optional<Car> entity) {
        super.showById(entity);
    }
    @Override
    public void delete() {
        LOGGER.info("Following car will be deleted");
    }
    @Override
    public void successfulDeleted() {
        super.successfulDeleted();
    }
}
