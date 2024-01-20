package com.solvd.carservice.domain.view.admin;

import com.solvd.carservice.domain.entity.Department;
import org.apache.logging.log4j.LogManager;
import java.util.Optional;

public class ViewDepartment extends AbstractView<Department> {
    static {
        System.setProperty("log4j.configurationFile", "log4j2.xml");
    }
    private final static org.apache.logging.log4j.core.Logger LOGGER = (org.apache.logging.log4j.core.Logger) LogManager.getLogger(ViewDepartment.class);

    @Override
    public void showAll() {
        LOGGER.info("List of departments ");
    }
    @Override
    public void show(Department entity) {
        super.show(entity);
    }
    @Override
    public void added(Department entity) {
        super.added(entity);
    }
    @Override
    public void update() {
        LOGGER.info("Update department");
    }
    @Override
    public void updated(String field) {
        LOGGER.info("Department " + field + " was updated");
    }
    @Override
    public void showById(Optional<Department> entity) {
        super.showById(entity);
    }
    @Override
    public void delete() {
        LOGGER.info("Following department will be deleted");
    }
    @Override
    public void successfulDeleted() {
        super.successfulDeleted();
    }
}
