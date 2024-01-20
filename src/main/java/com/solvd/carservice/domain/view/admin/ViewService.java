package com.solvd.carservice.domain.view.admin;

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
    public void addEmployee() {
        LOGGER.info("Add employee to service");
    }
    public void addedEmployee(Long employeeId, Long serviceId) {
        LOGGER.info("Employee id - " + employeeId + " was assigned to the service id - " + serviceId);
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
}
