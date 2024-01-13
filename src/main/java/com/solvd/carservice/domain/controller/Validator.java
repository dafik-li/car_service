package com.solvd.carservice.domain.controller;

import com.solvd.carservice.domain.entity.Client;
import com.solvd.carservice.domain.exception.AuthorizationException;
import com.solvd.carservice.domain.exception.ModerateException;
import com.solvd.carservice.domain.exception.TableException;

public class Validator {

    public void validateStartPageMenu(String menu) throws AuthorizationException {
        if (!menu.equals("1") && !menu.equals("2") && !menu.equals("0")) {
            throw new AuthorizationException("What do you want, dude?");
        }
    }
    public void validateControllerMenu(String menu) throws ModerateException {
        if (!menu.equals("\\d")) {
            throw new ModerateException("Choose a table or roll back");
        }
    }
    public void validateActionMenu(String menu) throws TableException {
        if (!menu.equals("[0-5]")) {
            throw new TableException("Choose a desire action or roll back");
        }
    }
    public void validateAuthorization(String menu) throws AuthorizationException {
        if (!menu.equals("1") && !menu.equals("2") && !menu.equals("3") && !menu.equals("0")) {
            throw new AuthorizationException("What do you want, dude?");
        }
    }
    public void validateClient(String menu, Client client) throws AuthorizationException {
        if (!menu.equals(client.getId())) {
            throw new AuthorizationException("What do you want, dude?");
        }
    }
}
