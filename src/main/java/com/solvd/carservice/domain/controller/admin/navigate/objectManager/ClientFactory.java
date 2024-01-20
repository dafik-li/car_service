package com.solvd.carservice.domain.controller.admin.navigate.objectManager;

import com.solvd.carservice.domain.controller.admin.ClientController;
import com.solvd.carservice.domain.controller.admin.navigate.Navigator;
import com.solvd.carservice.domain.parse.Parser;
import com.solvd.carservice.domain.parse.entity.ParseClient;
import com.solvd.carservice.domain.view.admin.ViewClient;
import com.solvd.carservice.service.impl.ClientServiceImpl;

public class ClientFactory {
    public Navigator<?> create() {
        return new Navigator<>(new ClientController(new ViewClient(), new ClientServiceImpl()),
                new Parser<>(new ViewClient(), new ParseClient(), new ClientServiceImpl()));
    }
}
