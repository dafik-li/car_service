package com.solvd.carservice.domain.parse;

import com.solvd.carservice.domain.entity.*;
import com.solvd.carservice.service.*;
import com.solvd.carservice.service.impl.*;

public class JaxbParser extends AbstractParser {
    public void addCompany() {
        CompanyService companyService = new CompanyServiceImpl();
        Company company = parseCompany.jaxbParse();
        companyService.add(company);
        display.addedCompany(company);
    }
    public void addDepartment() {
        DepartmentService departmentService = new DepartmentServiceImpl();
        Department department = parseDepartment.jaxbParse();
        departmentService.add(department);
        display.addedDepartment(department);
    }
    public void addEmployee() {
        EmployeeService employeeService = new EmployeeServiceImpl();
        Employee employee = parseEmployee.jaxbParse();
        employeeService.add(employee);
        display.addedEmployee(employee);
    }
    public void addService() {
        ServiceService serviceService = new ServiceServiceImpl();
        Service service = parseService.jaxbParse();
        serviceService.add(service);
        display.addedService(service);
    }
    public void addCar() {
        CarService carService = new CarServiceImpl();
        Car car = parseCar.jaxbParse();
        carService.add(car);
        display.addedCar(car);
    }
    public void addDetail() {
        DetailService detailService = new DetailServiceImpl();
        Detail detail = parseDetail.jaxbParse();
        detailService.add(detail);
        display.addedDetail(detail);
    }
    public void addClient() {
        ClientService clientService = new ClientServiceImpl();
        Client client = parseClient.jaxbParse();
        clientService.add(client);
        display.addedClient(client);
    }
    public void addCost() {
        CostService costService = new CostServiceImpl();
        Cost cost = parseCost.jaxbParse();
        costService.add(cost);
        display.addedCost(cost);
    }
    public void addOrder() {
        OrderService orderService = new OrderServiceImpl();
        Order order = parseOrder.jaxbParse();
        orderService.add(order);
        display.addedOrder(order);
    }
}
