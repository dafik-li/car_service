package com.solvd.carservice.domain.controller.admin.navigate.objectManager;

import com.solvd.carservice.domain.controller.admin.DetailController;
import com.solvd.carservice.domain.controller.admin.navigate.Navigator;
import com.solvd.carservice.domain.parse.Parser;
import com.solvd.carservice.domain.parse.entity.ParseDetail;
import com.solvd.carservice.domain.view.admin.ViewDetail;
import com.solvd.carservice.service.impl.DetailServiceImpl;

public class DetailFactory {
    public Navigator<?> create() {
        return new Navigator<>(
                new DetailController(new ViewDetail(), new DetailServiceImpl()),
                new Parser<>(new ViewDetail(), new ParseDetail(), new DetailServiceImpl()));
    }
}
