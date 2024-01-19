package com.solvd.carservice.domain.controller;

import com.solvd.carservice.domain.exception.AuthorizationException;
import com.solvd.carservice.domain.exception.ModerateException;
import com.solvd.carservice.domain.exception.TableException;
import java.util.List;

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
    public void validateIsNumber(String menu) throws AuthorizationException {
        if (menu.equals("[0-9]+")) {
            throw new AuthorizationException("What do you want, dude?");
        }
    }
    public void validateIsDouble(String menu) throws AuthorizationException {
        if (menu.equals("\\d+(\\.\\d+)?")) {
            throw new AuthorizationException("What do you want, dude?");
        }
    }
    public void validateIsBoolean(String menu) throws AuthorizationException {
        if (menu.equals("1")) {
            return;
        }
        if (menu.equals("0")) {
            return;
        }
        throw new AuthorizationException("What do you want, dude?");
    }
    public void validateIsString(String menu) throws AuthorizationException {
        if (menu.equals("[a-zA-Z]+")) {
            throw new AuthorizationException("What do you want, dude?");
        }
    }
    public void validateId(List<?> list, Long carId) throws AuthorizationException {
        for (int i =0; i < list.size(); i++) {
            if (list.indexOf(i) == carId) {
                throw new AuthorizationException("What do you want, dude?");
            }
        }
    }
}
