package com.solvd.carservice.domain.parse;

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
}
