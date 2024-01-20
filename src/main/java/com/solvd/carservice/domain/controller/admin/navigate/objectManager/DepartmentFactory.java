package com.solvd.carservice.domain.controller.admin.navigate.objectManager;

import com.solvd.carservice.domain.controller.admin.DepartmentController;
import com.solvd.carservice.domain.controller.admin.navigate.Navigator;
import com.solvd.carservice.domain.parse.Parser;
import com.solvd.carservice.domain.parse.entity.ParseDepartment;
import com.solvd.carservice.domain.view.admin.ViewDepartment;
import com.solvd.carservice.service.impl.DepartmentServiceImpl;

public class DepartmentFactory {
    public Navigator<?> create() {
        return new Navigator<>(
                new DepartmentController(new ViewDepartment(), new DepartmentServiceImpl()),
                new Parser<>(new ViewDepartment(), new ParseDepartment(), new DepartmentServiceImpl()));
    }
}
