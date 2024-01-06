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

public class Parser {
    public static void addCompany() {
        CompanyService companyService = new CompanyServiceImpl();
        Company company = ParseCompany.staxParseCompany();
        companyService.add(company);
    }
    public static void addDepartment() {
        DepartmentService departmentService = new DepartmentServiceImpl();
        Department department = ParseDepartment.staxParseDepartment();
        departmentService.add(department);
    }
    public static void addEmployee() {
        EmployeeService employeeService = new EmployeeServiceImpl();
        Employee employee = ParseEmployee.staxParseEmployee();
        employeeService.add(employee);
    }
    public static void addService() {
        ServiceService serviceService = new ServiceServiceImpl();
        Service service = ParseService.staxParseService();
        serviceService.add(service);
    }
}
