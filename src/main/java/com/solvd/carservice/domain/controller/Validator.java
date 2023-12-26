package com.solvd.carservice.domain.controller;

import com.solvd.carservice.exception.MenuException;

public class Validator {

    public void validateAuthorization(String menu) throws MenuException {
        if (!menu.equals("1") && !menu.equals("2") && !menu.equals("0")) {
            throw new MenuException("What do you want, dude?");
        }
    }
    public void validateModeration(String menu) throws MenuException {
        if (!menu.equals("\\d")) {
            throw new MenuException("Choose a table or roll back");
        }
    }
    public void validateTable(String menu) throws MenuException {
        if (!menu.equals("[0-5]")) {
            throw new MenuException("Choose a desire action or roll back");
        }
    }
}
