package com.solvd.carservice.domain.view;

import com.solvd.carservice.domain.entity.*;
import org.apache.logging.log4j.LogManager;

public class Display {
    static {
        System.setProperty("log4j.configurationFile", "log4j2.xml");
    }
    private final static org.apache.logging.log4j.core.Logger LOGGER = (org.apache.logging.log4j.core.Logger) LogManager.getLogger(Display.class);

    public void addedCompany(Company company) {
        LOGGER.info(company.toString());
    }
    public void addedDepartment(Department department) {
        LOGGER.info(
                "Department id - " + department.getId() + "|" +
                "name - " + department.getName() + "[" +
                "company id - " + department.getCompanyId().getId() + "|" +
                "name - " + department.getCompanyId().getName() + "|" +
                "address - " + department.getCompanyId().getAddress() + "] - was added");
    }
    public void addedEmployee(Employee employee) {
        LOGGER.info(
                "Employee id - " + employee.getId() + "|" +
                "name - " + employee.getName() + "|" +
                "surname - " + employee.getSurname() + "|" +
                "age - " + employee.getAge() + "|" +
                "position - " + employee.getPosition() + "|" +
                "level - " + employee.getLevel() + "|" +
                "salary - " + employee.getSalary() + "|" +
                "phone - " + employee.getPhoneNumber() + "[" +
                "department id - " + employee.getDepartmentId().getId() + "|" +
                "name - " + employee.getDepartmentId().getName() + "][" +
                "company id - " + employee.getDepartmentId().getCompanyId().getId() + "|" +
                "name - " + employee.getDepartmentId().getCompanyId().getName() + "|" +
                "address - " + employee.getDepartmentId().getCompanyId().getAddress() + "] - was added");
    }
    public void addedService(Service service) {
        LOGGER.info(
                "Service id - " + service.getId() + "|" +
                "name - " + service.getName() + "|" +
                "price - " + service.getPrice() + "|" +
                "hours to do - " + service.getHoursToDo() + "[" +
                "car id - " + service.getCarId().getId() + "|" +
                "brand - " + service.getCarId().getBrand() + "|" +
                "model - " + service.getCarId().getModel() + "|" +
                "year - " + service.getCarId().getYear() + "][" +
                "department id - " + service.getDepartmentId().getId() + "|" +
                "name - " + service.getDepartmentId().getName() + "][" +
                "company id - " + service.getDepartmentId().getCompanyId().getId() + "|" +
                "name - " + service.getDepartmentId().getCompanyId().getName() + "|" +
                "address - " + service.getDepartmentId().getCompanyId().getAddress() + "] - was added");
    }
    public void addedCar(Car car) {
        LOGGER.info(
                "Car id - " + car.getId() + "|" +
                "brand - " + car.getBrand() + "|" +
                "model - " + car.getModel() + "|" +
                "year - " + car.getYear() + " - was added");
    }
    public void addedClient(Client client) {
        LOGGER.info(
                "Client id - " + client.getId() + "|" +
                "name - " + client.getName() + "|" +
                "surname - " + client.getSurname() + "|" +
                "phone number - " + client.getPhoneNumber() + "|" +
                "birthday - " + client.getBirthday() + " - was added");
    }
    public void addedCost(Cost cost) {
        LOGGER.info(
                "Cost id - " + cost.getId() + "|" +
                "cost - " + cost.getCost() + "[" +
                "service id - " + cost.getServiceId().getId() + "|" +
                "name - " + cost.getServiceId().getName() + "|" +
                "price - " + cost.getServiceId().getPrice() + "|" +
                "hours to do - " + cost.getServiceId().getHoursToDo() + "][" +
                "car id - " + cost.getServiceId().getCarId().getId() + "|" +
                "brand - " + cost.getServiceId().getCarId().getBrand() + "|" +
                "model - " + cost.getServiceId().getCarId().getModel() + "|" +
                "year - " + cost.getServiceId().getCarId().getYear() + "][" +
                "department id - " + cost.getServiceId().getDepartmentId().getId() + "|" +
                "name - " + cost.getServiceId().getDepartmentId().getName() + "][" +
                "company id - " + cost.getServiceId().getDepartmentId().getCompanyId().getId() + "|" +
                "name - " + cost.getServiceId().getDepartmentId().getCompanyId().getName() + "|" +
                "address - " + cost.getServiceId().getDepartmentId().getCompanyId().getAddress() + "][" +
                "detail id - " + cost.getDetailId().getId() + "|" +
                "name - " + cost.getDetailId().getName() + "|" +
                "price - " + cost.getDetailId().getPrice() + "|" +
                "in stock - " + cost.getDetailId().getInStock() + "|" +
                "delivery days - " + cost.getDetailId().getDeliveryDays() + "] - was added");
    }
    public void addedDetail(Detail detail) {
        LOGGER.info(
                "Detail id - " + detail.getId() + "|" +
                "name - " + detail.getName() + "|" +
                "price - " + detail.getPrice() + "[" +
                "car id - " + detail.getCarId().getId() + "|" +
                "brand - " + detail.getCarId().getBrand() + "|" +
                "model - " + detail.getCarId().getModel() + "|" +
                "year - " + detail.getCarId().getYear() + "]" +
                "in stock - " + detail.getInStock() + "|" +
                "delivery days - " + detail.getDeliveryDays() + "] - was added");
    }
    public void addedOrder(Order order) {
        LOGGER.info(
                "Order id - " + order.getId() + "|" +
                "date - " + order.getDate() + "[" +
                "client id - " + order.getClientId().getId() + "|" +
                "name - " + order.getClientId().getName() + "|" +
                "surname - " + order.getClientId().getSurname() + "|" +
                "phone number - " + order.getClientId().getPhoneNumber() + "|" +
                "birthday - " + order.getClientId().getBirthday() + "][" +
                "cost id - " + order.getCostId().getId() + "|" +
                "cost - " + order.getCostId().getCost() + "[" +
                "service id - " + order.getCostId().getServiceId().getId() + "|" +
                "name - " + order.getCostId().getServiceId().getName() + "|" +
                "price - " + order.getCostId().getServiceId().getPrice() + "|" +
                "hours to do - " + order.getCostId().getServiceId().getHoursToDo() + "][" +
                "car id - " + order.getCostId().getServiceId().getCarId().getId() + "|" +
                "brand - " + order.getCostId().getServiceId().getCarId().getBrand() + "|" +
                "model - " + order.getCostId().getServiceId().getCarId().getModel() + "|" +
                "year - " + order.getCostId().getServiceId().getCarId().getYear() + "][" +
                "department id - " + order.getCostId().getServiceId().getDepartmentId().getId() + "|" +
                "name - " + order.getCostId().getServiceId().getDepartmentId().getName() + "][" +
                "company id - " + order.getCostId().getServiceId().getDepartmentId().getCompanyId().getId() + "|" +
                "name - " + order.getCostId().getServiceId().getDepartmentId().getCompanyId().getName() + "|" +
                "address - " + order.getCostId().getServiceId().getDepartmentId().getCompanyId().getAddress() + "][" +
                "detail id - " + order.getCostId().getDetailId().getId() + "|" +
                "name - " + order.getCostId().getDetailId().getName() + "|" +
                "price - " + order.getCostId().getDetailId().getPrice() + "|" +
                "in stock - " + order.getCostId().getDetailId().getInStock() + "|" +
                "delivery days - " + order.getCostId().getDetailId().getDeliveryDays() + "] - was added");
    }
}
