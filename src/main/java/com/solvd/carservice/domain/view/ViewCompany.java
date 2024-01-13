package com.solvd.carservice.domain.view;

import com.solvd.carservice.domain.entity.Company;
import org.apache.logging.log4j.LogManager;
import java.util.Optional;

public class ViewCompany extends AbstractView<Company>{
    static {
        System.setProperty("log4j.configurationFile", "log4j2.xml");
    }
    private final static org.apache.logging.log4j.core.Logger LOGGER = (org.apache.logging.log4j.core.Logger) LogManager.getLogger(ViewCompany.class);

    @Override
    public void showAll() {
        LOGGER.info("List of companies ");
    }
    @Override
    public void show(Company entity) {
        super.show(entity);
    }
    @Override
    public void added(Company entity) {
        super.added(entity);
    }
    @Override
    public void update() {
        LOGGER.info("Update company");
    }
    @Override
    public void updated(String field) {
        LOGGER.info("Company " + field + " was updated");
    }
    @Override
    public void showById(Optional<Company> entity) {
        super.showById(entity);
    }
    @Override
    public void delete() {
        LOGGER.info("Following company will be deleted");
    }
    @Override
    public void successfulDeleted() {
        super.successfulDeleted();
    }
    /*
    public void showAll() {
        LOGGER.info("List of companies ");
    }
    public void show(Company company) {
        LOGGER.info(company.toString());
    }
    public void added(Company company) {
        LOGGER.info(company.toString() + " - was added");
    }
    public void update() {
        LOGGER.info("Update company");
    }
    public void updated(String field) {
        LOGGER.info("Company " + field + " was updated");
    }
    public void showById(Optional<Company> companyOptional) {
        LOGGER.info(companyOptional.toString());
    }
    public void delete() {
        LOGGER.info("Following company will be deleted");
    }
    public void successfulDeleted() {
        LOGGER.info("Successful deleted");
    }
     */
}
