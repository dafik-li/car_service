package com.solvd.carservice.domain.parse;

import com.solvd.carservice.domain.entity.*;
import com.solvd.carservice.domain.parse.entity.*;
import com.solvd.carservice.service.*;
import com.solvd.carservice.service.impl.*;
import com.solvd.carservice.util.Display;

public class Parser {
    private final Display display;
    private final ParseCompany parseCompany;
    private final ParseDepartment parseDepartment;
    private final ParseEmployee parseEmployee;
    private final ParseService parseService;
    private final ParseCar parseCar;
    private final ParseDetail parseDetail;
    private final ParseClient parseClient;
    private final ParseCost parseCost;
    private final ParseOrder parseOrder;

    public Parser() {
        this.display = new Display();
        this.parseCompany = new ParseCompany();
        this.parseDepartment = new ParseDepartment();
        this.parseEmployee = new ParseEmployee();
        this.parseService = new ParseService();
        this.parseCar = new ParseCar();
        this.parseDetail = new ParseDetail();
        this.parseClient = new ParseClient();
        this.parseCost = new ParseCost();
        this.parseOrder = new ParseOrder();
    }
    public void addCompany(String menu) {
        CompanyService companyService = new CompanyServiceImpl();
        Company company = new Company();
        if (Integer.parseInt(menu) == EnumParser.STAX.getParseType()) {
            company = parseCompany.staxParse();
        } else if (Integer.parseInt(menu) == EnumParser.JAXB.getParseType()) {
            company = parseCompany.jaxbParse();
        } else if (Integer.parseInt(menu) == EnumParser.JACKSON.getParseType()) {
            company = parseCompany.jacksonParse();
        }
        companyService.add(company);
        display.addedCompany(company);
    }
    public void addDepartment(String menu) {
        DepartmentService departmentService = new DepartmentServiceImpl();
        Department department = new Department();
        if (Integer.parseInt(menu) == EnumParser.STAX.getParseType()) {
            department = parseDepartment.staxParse();
        } else if (Integer.parseInt(menu) == EnumParser.JAXB.getParseType()) {
            department = parseDepartment.jaxbParse();
        } else if (Integer.parseInt(menu) == EnumParser.JACKSON.getParseType()) {
            department = parseDepartment.jacksonParse();
        }
        departmentService.add(department);
        display.addedDepartment(department);
    }
    public void addEmployee(String menu) {
        EmployeeService employeeService = new EmployeeServiceImpl();
        Employee employee = new Employee();
        if (Integer.parseInt(menu) == EnumParser.STAX.getParseType()) {
            employee = parseEmployee.staxParse();
        } else if (Integer.parseInt(menu) == EnumParser.JAXB.getParseType()) {
            employee = parseEmployee.jaxbParse();
        } else if (Integer.parseInt(menu) == EnumParser.JACKSON.getParseType()) {
            employee = parseEmployee.jacksonParse();
        }
        employeeService.add(employee);
        display.addedEmployee(employee);
    }
    public void addService(String menu) {
        ServiceService serviceService = new ServiceServiceImpl();
        Service service = new Service();
        if (Integer.parseInt(menu) == EnumParser.STAX.getParseType()) {
            service = parseService.staxParse();
        } else if (Integer.parseInt(menu) == EnumParser.JAXB.getParseType()) {
            service = parseService.jaxbParse();
        } else if (Integer.parseInt(menu) == EnumParser.JACKSON.getParseType()) {
            service = parseService.jacksonParse();
        }
        serviceService.add(service);
        display.addedService(service);
    }
    public void addCar(String menu) {
        CarService carService = new CarServiceImpl();
        Car car = new Car();
        if (Integer.parseInt(menu) == EnumParser.STAX.getParseType()) {
            car = parseCar.staxParse();
        } else if (Integer.parseInt(menu) == EnumParser.JAXB.getParseType()) {
            car = parseCar.jaxbParse();
        } else if (Integer.parseInt(menu) == EnumParser.JACKSON.getParseType()) {
            car = parseCar.jacksonParse();
        }
        carService.add(car);
        display.addedCar(car);
    }
    public void addDetail(String menu) {
        DetailService detailService = new DetailServiceImpl();
        Detail detail = new Detail();
        if (Integer.parseInt(menu) == EnumParser.STAX.getParseType()) {
            detail = parseDetail.staxParse();
        } else if (Integer.parseInt(menu) == EnumParser.JAXB.getParseType()) {
            detail = parseDetail.jaxbParse();
        } else if (Integer.parseInt(menu) == EnumParser.JACKSON.getParseType()) {
            detail = parseDetail.jacksonParse();
        }
        detailService.add(detail);
        display.addedDetail(detail);
    }
    public void addClient(String menu) {
        ClientService clientService = new ClientServiceImpl();
        Client client = new Client();
        if (Integer.parseInt(menu) == EnumParser.STAX.getParseType()) {
            client = parseClient.staxParse();
        } else if (Integer.parseInt(menu) == EnumParser.JAXB.getParseType()) {
            client = parseClient.jaxbParse();
        } else if (Integer.parseInt(menu) == EnumParser.JACKSON.getParseType()) {
            client = parseClient.jacksonParse();
        }
        clientService.add(client);
        display.addedClient(client);
    }
    public void addCost(String menu) {
        CostService costService = new CostServiceImpl();
        Cost cost = new Cost();
        if (Integer.parseInt(menu) == EnumParser.STAX.getParseType()) {
            cost = parseCost.staxParse();
        } else if (Integer.parseInt(menu) == EnumParser.JAXB.getParseType()) {
            cost = parseCost.jaxbParse();
        } else if (Integer.parseInt(menu) == EnumParser.JACKSON.getParseType()) {
            cost = parseCost.jacksonParse();
        }
        costService.add(cost);
        display.addedCost(cost);
    }
    public void addOrder(String menu) {
        OrderService orderService = new OrderServiceImpl();
        Order order = new Order();
        if (Integer.parseInt(menu) == EnumParser.STAX.getParseType()) {
            order = parseOrder.staxParse();
        } else if (Integer.parseInt(menu) == EnumParser.JAXB.getParseType()) {
            order = parseOrder.jaxbParse();
        } else if (Integer.parseInt(menu) == EnumParser.JACKSON.getParseType()) {
            order = parseOrder.jacksonParse();
        }
        orderService.add(order);
        display.addedOrder(order);
    }
}
