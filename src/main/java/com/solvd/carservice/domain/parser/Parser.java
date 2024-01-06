package com.solvd.carservice.domain.parser;

import com.solvd.carservice.domain.entity.Company;
import com.solvd.carservice.domain.entity.Department;
import com.solvd.carservice.domain.entity.Employee;
import com.solvd.carservice.domain.entity.Service;
import com.solvd.carservice.service.CompanyService;
import com.solvd.carservice.service.DepartmentService;
import com.solvd.carservice.service.EmployeeService;
import com.solvd.carservice.service.ServiceService;
import com.solvd.carservice.service.impl.CompanyServiceImpl;
import com.solvd.carservice.service.impl.DepartmentServiceImpl;
import com.solvd.carservice.service.impl.EmployeeServiceImpl;
import com.solvd.carservice.service.impl.ServiceServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

public class Parser {
    static {
        System.setProperty("log4j.configurationFile", "log4j2.xml");
    }
    private final static Logger LOGGER = (Logger) LogManager.getLogger(Parser.class);
    public static void addCompany() {
        CompanyService companyService = new CompanyServiceImpl();
        Company company = ParseCompany.staxParseCompany();
        companyService.add(company);
        LOGGER.info("Id - " + company.getId() + "Company - " + company.getName() + company.getAddress() + " - was added");
    }
    public static void addDepartment() {
        DepartmentService departmentService = new DepartmentServiceImpl();
        Department department = ParseDepartment.staxParseDepartment();
        departmentService.add(department);
        LOGGER.info("Id - " + department.getId() + "Department - " + department.getName() + department.getCompanyId() + " - was added");
    }
    public static void addEmployee() {
        EmployeeService employeeService = new EmployeeServiceImpl();
        Employee employee = ParseEmployee.staxParseEmployee();
        employeeService.add(employee);
        LOGGER.info("Id - " + employee.getId() + "Employee - " + employee.getName() + " " + employee.getSurname() + " - was added");
    }
    public static void addService() {
        ServiceService serviceService = new ServiceServiceImpl();
        Service service = ParseService.staxParseService();
        serviceService.add(service);
        LOGGER.info("Id - " + service.getId() + "Service - " + service.getName() + " - was added");
    }
}
