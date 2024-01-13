package com.solvd.carservice.domain.view;

import org.apache.logging.log4j.LogManager;
import java.util.Optional;

public abstract class AbstractView<E> implements InterfaceView<E>{
    static {
        System.setProperty("log4j.configurationFile", "log4j2.xml");
    }
    private final static org.apache.logging.log4j.core.Logger LOGGER = (org.apache.logging.log4j.core.Logger) LogManager.getLogger(AbstractView.class);

    public abstract void showAll();
    public void show(E entity) {
        LOGGER.info(entity.toString());
    }
    public void added(E entity) {
        LOGGER.info(entity.toString() + " - was added");
    }
    public abstract void update();
    public abstract void updated(String field);
    public void showById(Optional<E> entity) {
        LOGGER.info(entity.toString());
    }
    public abstract void delete();
    public void successfulDeleted() {
        LOGGER.info("Successful deleted");
    }
}
