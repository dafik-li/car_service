package com.solvd.carservice.domain.controller.admin.navigate.objectManager;

import com.solvd.carservice.domain.controller.admin.navigate.Navigator;
import com.solvd.carservice.domain.controller.admin.OrderController;
import com.solvd.carservice.domain.parse.Parser;
import com.solvd.carservice.domain.parse.entity.ParseOrder;
import com.solvd.carservice.domain.view.admin.ViewOrder;
import com.solvd.carservice.service.impl.OrderServiceImpl;

public class OrderFactory {
    public Navigator<?> create() {
        return new Navigator<>(
                new OrderController(new ViewOrder(), new OrderServiceImpl()),
                new Parser<>(new ViewOrder(), new ParseOrder(), new OrderServiceImpl()));
    }
}
