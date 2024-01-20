package com.solvd.carservice.domain.controller.admin.navigate.objectManager;

import com.solvd.carservice.domain.controller.admin.CompanyController;
import com.solvd.carservice.domain.controller.admin.navigate.Navigator;
import com.solvd.carservice.domain.parse.Parser;
import com.solvd.carservice.domain.parse.entity.ParseCompany;
import com.solvd.carservice.domain.view.admin.ViewCompany;
import com.solvd.carservice.service.impl.CompanyServiceImpl;

public class CompanyFactory {
    public Navigator<?> create() {
        return new Navigator<>(
                new CompanyController(new ViewCompany(), new CompanyServiceImpl()),
                new Parser<>(new ViewCompany(), new ParseCompany(), new CompanyServiceImpl()));
    }
}
