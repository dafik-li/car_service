package com.solvd.carservice.domain.controller.admin.navigate.objectManager;

import com.solvd.carservice.domain.controller.admin.CostController;
import com.solvd.carservice.domain.controller.admin.navigate.Navigator;
import com.solvd.carservice.domain.parse.Parser;
import com.solvd.carservice.domain.parse.entity.ParseCost;
import com.solvd.carservice.domain.view.admin.ViewCost;
import com.solvd.carservice.service.impl.CostServiceImpl;

public class CostFactory {
    public Navigator<?> create() {
        return new Navigator<>(
                new CostController(new ViewCost(), new CostServiceImpl()),
                new Parser<>(new ViewCost(), new ParseCost(), new CostServiceImpl()));
    }
}
