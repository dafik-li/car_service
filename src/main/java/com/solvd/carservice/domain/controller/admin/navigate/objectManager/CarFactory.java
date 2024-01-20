package com.solvd.carservice.domain.controller.admin.navigate.objectManager;

import com.solvd.carservice.domain.controller.admin.CarController;
import com.solvd.carservice.domain.controller.admin.navigate.Navigator;
import com.solvd.carservice.domain.parse.Parser;
import com.solvd.carservice.domain.parse.entity.ParseCar;
import com.solvd.carservice.domain.view.admin.ViewCar;
import com.solvd.carservice.service.impl.CarServiceImpl;

public class CarFactory {
    public Navigator<?> create() {
        return new Navigator<>(
                new CarController(new ViewCar(), new CarServiceImpl()),
                new Parser<>(new ViewCar(), new ParseCar(), new CarServiceImpl()));
    }
}
