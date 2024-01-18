package com.solvd.carservice;

import com.solvd.carservice.domain.controller.admin.navigate.MainMenuNavigate;

public class Main {
    public static void main(String[] args) {
        MainMenuNavigate mainMenuNavigate = new MainMenuNavigate();
        mainMenuNavigate.selectDBRepository();
    }
}
