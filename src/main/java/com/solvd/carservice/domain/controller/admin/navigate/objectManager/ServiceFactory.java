package com.solvd.carservice.domain.controller.admin.navigate.objectManager;

import com.solvd.carservice.domain.controller.admin.ServiceController;
import com.solvd.carservice.domain.entity.Service;
import com.solvd.carservice.domain.parse.Parser;
import com.solvd.carservice.domain.parse.entity.ParseService;
import com.solvd.carservice.domain.view.admin.ViewService;
import com.solvd.carservice.service.impl.ServiceServiceImpl;

public class ServiceFactory {
    public ServiceController createController() {
        return new ServiceController(new ViewService(), new ServiceServiceImpl());
    }
    public Parser<Service> createParser() {
        return new Parser<>(new ViewService(), new ParseService(), new ServiceServiceImpl());
    }
}
