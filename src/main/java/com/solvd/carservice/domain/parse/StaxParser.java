package com.solvd.carservice.domain.parse;

import com.solvd.carservice.domain.entity.*;
import com.solvd.carservice.service.*;
import com.solvd.carservice.service.impl.*;

public class StaxParser extends AbstractParser {
    public void addCompany() {
        CompanyService companyService = new CompanyServiceImpl();
        Company company = parseCompany.staxParse();
        companyService.add(company);
        display.addedCompany(company);
    }
    public void addDepartment() {
        DepartmentService departmentService = new DepartmentServiceImpl();
        Department department = parseDepartment.staxParse();
        departmentService.add(department);
        display.addedDepartment(department);
    }
    public void addEmployee() {
        EmployeeService employeeService = new EmployeeServiceImpl();
        Employee employee = parseEmployee.staxParse();
        employeeService.add(employee);
        display.addedEmployee(employee);
    }
    public void addService() {
        ServiceService serviceService = new ServiceServiceImpl();
        Service service = parseService.staxParse();
        serviceService.add(service);
        display.addedService(service);
    }
    public void addCar() {
        CarService carService = new CarServiceImpl();
        Car car = parseCar.staxParse();
        carService.add(car);
        display.addedCar(car);
    }
    public void addDetail() {
        DetailService detailService = new DetailServiceImpl();
        Detail detail = parseDetail.staxParse();
        detailService.add(detail);
        display.addedDetail(detail);
    }
    public void addClient() {
        ClientService clientService = new ClientServiceImpl();
        Client client = parseClient.staxParse();
        clientService.add(client);
        display.addedClient(client);
    }
    public void addCost() {
        CostService costService = new CostServiceImpl();
        Cost cost = parseCost.staxParse();
        costService.add(cost);
        display.addedCost(cost);
    }
    public void addOrder() {
        OrderService orderService = new OrderServiceImpl();
        Order order = parseOrder.staxParse();
        orderService.add(order);
        display.addedOrder(order);
    }
}
