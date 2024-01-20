package com.solvd.carservice.domain.view.admin;

import com.solvd.carservice.domain.entity.Detail;
import org.apache.logging.log4j.LogManager;
import java.util.Optional;

public class ViewDetail extends AbstractView<Detail> {
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
}
