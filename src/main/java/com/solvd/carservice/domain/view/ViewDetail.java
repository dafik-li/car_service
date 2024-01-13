package com.solvd.carservice.domain.view;

import com.solvd.carservice.domain.entity.Detail;
import org.apache.logging.log4j.LogManager;
import java.util.Optional;

public class ViewDetail extends AbstractView<Detail>{
    static {
        System.setProperty("log4j.configurationFile", "log4j2.xml");
    }
    private final static org.apache.logging.log4j.core.Logger LOGGER = (org.apache.logging.log4j.core.Logger) LogManager.getLogger(ViewDetail.class);

    @Override
    public void showAll() {
        LOGGER.info("List of details ");
    }
    @Override
    public void show(Detail entity) {
        super.show(entity);
    }
    @Override
    public void added(Detail entity) {
        super.added(entity);
    }
    @Override
    public void update() {
        LOGGER.info("Update detail");
    }
    @Override
    public void updated(String field) {
        LOGGER.info("Detail " + field + " was updated");
    }
    @Override
    public void showById(Optional<Detail> entity) {
        super.showById(entity);
    }
    @Override
    public void delete() {
        LOGGER.info("Following detail will be deleted");
    }
    @Override
    public void successfulDeleted() {
        super.successfulDeleted();
    }
    /*
    public void showAll() {
        LOGGER.info("List of details ");
    }
    public void show(Detail detail) {
        LOGGER.info(detail.toString());
    }
    public void added(Detail detail) {
        LOGGER.info(detail.toString() + " - was added");
    }
    public void update() {
        LOGGER.info("Update detail");
    }
    public void updated(String field) {
        LOGGER.info("Detail " + field + " was updated");
    }
    public void showById(Optional<Detail> detailOptional) {
        LOGGER.info(detailOptional.toString());
    }
    public void delete() {
        LOGGER.info("Following detail will be deleted");
    }
    public void successfulDeleted() {
        LOGGER.info("Successful deleted");
    }
     */
}
