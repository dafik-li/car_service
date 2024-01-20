package com.solvd.carservice;

import com.solvd.carservice.domain.controller.admin.navigate.MainMenuNavigator;

public class Main {
    public static void main(String[] args) {
        MainMenuNavigator mainMenuNavigator = new MainMenuNavigator();
        mainMenuNavigator.selectDBRepository();
    }
}
