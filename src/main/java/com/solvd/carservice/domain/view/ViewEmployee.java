package com.solvd.carservice.domain.view;

import com.solvd.carservice.domain.entity.Employee;
import org.apache.logging.log4j.LogManager;
import java.util.Optional;

public class ViewEmployee extends AbstractView<Employee>{
    static {
        System.setProperty("log4j.configurationFile", "log4j2.xml");
    }
    private final static org.apache.logging.log4j.core.Logger LOGGER = (org.apache.logging.log4j.core.Logger) LogManager.getLogger(ViewEmployee.class);

    @Override
    public void showAll() {
        LOGGER.info("List of employees ");
    }
    @Override
    public void show(Employee entity) {
        super.show(entity);
    }
    @Override
    public void added(Employee entity) {
        super.added(entity);
    }
    @Override
    public void update() {
        LOGGER.info("Update employee");
    }
    @Override
    public void updated(String field) {
        LOGGER.info("Employee " + field + " was updated");
    }
    @Override
    public void showById(Optional<Employee> entity) {
        super.showById(entity);
    }
    @Override
    public void delete() {
        LOGGER.info("Following employee will be deleted");
    }
    @Override
    public void successfulDeleted() {
        super.successfulDeleted();
    }
    /*
    public void showAll() {
        LOGGER.info("List of employees ");
    }
    public void show(Employee employee) {
        LOGGER.info(employee.toString());
    }
    public void added(Employee employee) {
        LOGGER.info(employee.toString() + " - was added");
    }
    public void update() {
        LOGGER.info("Update employee");
    }
    public void updated(String field) {
        LOGGER.info("Employee " + field + " was updated");
    }
    public void showById(Optional<Employee> employeeOptional) {
        LOGGER.info(employeeOptional.toString());
    }
    public void delete() {
        LOGGER.info("Following employee will be deleted");
    }
    public void successfulDeleted() {
        LOGGER.info("Successful deleted");
    }
     */
}
