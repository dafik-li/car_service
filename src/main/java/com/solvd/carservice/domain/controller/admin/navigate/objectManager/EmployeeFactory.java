package com.solvd.carservice.domain.controller.admin.navigate.objectManager;

import com.solvd.carservice.domain.controller.admin.EmployeeController;
import com.solvd.carservice.domain.entity.Employee;
import com.solvd.carservice.domain.parse.Parser;
import com.solvd.carservice.domain.parse.entity.ParseEmployee;
import com.solvd.carservice.domain.view.admin.ViewEmployee;
import com.solvd.carservice.service.impl.EmployeeServiceImpl;

public class EmployeeFactory {
    public EmployeeController createController() {
        return new EmployeeController(new ViewEmployee(), new EmployeeServiceImpl());
    }
    public Parser<Employee> createParser() {
        return new Parser<>(new ViewEmployee(), new ParseEmployee(), new EmployeeServiceImpl());
    }
}
