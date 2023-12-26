package com.solvd.carservice;

import com.solvd.carservice.domain.controller.ActionController;

public class Main {

    public static void main(String[] args) {
        ActionController actionController = new ActionController();
        actionController.authorization();
    }
}
